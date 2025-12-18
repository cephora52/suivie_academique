package com.suivie_academique.suivie_academique.security;

import com.suivie_academique.suivie_academique.services.implemenetations.PersonnelDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtre JWT pour l'authentification basée sur les tokens
 * Ce filtre intercepte chaque requête HTTP et valide le token JWT s'il est présent
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtUtils jwtUtils;
    private final PersonnelDetailService personnelDetailService;

    public JwtFilter(JwtUtils jwtUtils, PersonnelDetailService personnelDetailService) {
        this.jwtUtils = jwtUtils;
        this.personnelDetailService = personnelDetailService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Récupérer le header Authorization
        final String authorizationHeader = request.getHeader("Authorization");

        // Vérifier si le header existe et commence par "Bearer "
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Extraire le token (retirer "Bearer ")
            final String jwt = authorizationHeader.substring(7);
            final String username = jwtUtils.extractUsername(jwt);

            // Vérifier si l'utilisateur n'est pas déjà authentifié
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Charger les détails de l'utilisateur
                UserDetails userDetails = personnelDetailService.loadUserByUsername(username);

                // Valider le token
                if (jwtUtils.validateToken(jwt, userDetails)) {

                    // Créer l'objet d'authentification
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    // Ajouter les détails de la requête
                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    // Stocker l'authentification dans le SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    log.debug("Utilisateur '{}' authentifié avec succès", username);
                }
            }
        } catch (Exception e) {
            // Log de l'erreur sans bloquer la requête
            log.error("Erreur lors de l'authentification JWT: {}", e.getMessage());
            // On ne fait rien, la requête continuera sans authentification
        }

        // Passer au filtre suivant
        filterChain.doFilter(request, response);
    }

    /**
     * Permet d'exclure certains endpoints du filtrage JWT
     * Par exemple : /auth/login, /auth/register, /swagger-ui, etc.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        // Liste des chemins à exclure du filtrage JWT
        return path.startsWith("/auth/")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/actuator/health");
    }
}