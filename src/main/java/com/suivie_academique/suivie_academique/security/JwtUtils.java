package com.suivie_academique.suivie_academique.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    // ✅ Bonne pratique : Externaliser la configuration
    @Value("${jwt.secret:votre_cle_secrete_tres_longue_et_complexe}")
    private String SECRET_KEY;

    @Value("${jwt.expiration:36000000}") // 10 heures par défaut
    private long JWT_TOKEN_VALIDITY;

    // Méthode pour obtenir la clé de signature sécurisée
    private SecretKey getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);

        // ✅ Validation de la longueur de la clé
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException(
                    "JWT Secret key must be at least 32 characters (256 bits) for HS256. " +
                            "Current length: " + keyBytes.length + " characters."
            );
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }

    // --- 1. Extraction des informations ---

    /**
     * Extrait le nom d'utilisateur (Subject) du token JWT
     * @param token Le token JWT
     * @return Le nom d'utilisateur
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait la date d'expiration du token
     * @param token Le token JWT
     * @return La date d'expiration
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Fonction générique pour extraire n'importe quel claim
     * @param token Le token JWT
     * @param claimsResolver Fonction pour extraire le claim spécifique
     * @return La valeur du claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Récupère l'ensemble des claims (le Payload) à partir du token
     * @param token Le token JWT
     * @return Les claims du token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Vérifie si le token est expiré
     * @param token Le token JWT
     * @return true si expiré, false sinon
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // --- 2. Génération de Token ---

    /**
     * Génère un token JWT pour un utilisateur (UserDetails)
     * @param userDetails Les détails de l'utilisateur
     * @return Le token JWT généré
     */
    public String generateToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername());
    }

    /**
     * Génère un token JWT pour un nom d'utilisateur simple
     * @param username Le nom d'utilisateur
     * @return Le token JWT généré
     */
    public String generateToken(String username) {
        return createToken(username);
    }

    /**
     * Logique de création réelle du token
     * @param subject Le sujet (généralement le username)
     * @return Le token JWT créé
     */
    private String createToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // --- 3. Validation de Token ---

    /**
     * Valide le token par rapport aux UserDetails
     * @param token Le token JWT à valider
     * @param userDetails Les détails de l'utilisateur
     * @return true si valide, false sinon
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Valide le token sans UserDetails (vérifie uniquement l'expiration et la signature)
     * @param token Le token JWT à valider
     * @return true si valide, false sinon
     */
    public Boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}