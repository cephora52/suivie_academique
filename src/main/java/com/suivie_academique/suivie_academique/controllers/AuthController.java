package com.suivie_academique.suivie_academique.controllers;


import com.suivie_academique.suivie_academique.Dto.LoginRequest;
import com.suivie_academique.suivie_academique.Dto.LoginResponse;
import com.suivie_academique.suivie_academique.entities.Personnel;
import com.suivie_academique.suivie_academique.repositories.PersonnelRepos;
import com.suivie_academique.suivie_academique.security.JwtUtils;
import com.suivie_academique.suivie_academique.services.implemenetations.PersonnelDetailService;
import com.suivie_academique.suivie_academique.utils.RolePersonnel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {


    private AuthenticationManager authenticationManager;

    private PersonnelDetailService personnelDetailService;
    private JwtUtils jwtUtils;
    private PersonnelRepos personnelRepos;

    private PasswordEncoder encoder;

    public AuthController(AuthenticationManager authenticationManager, PersonnelDetailService personnelDetailService, JwtUtils jwtUtils, PersonnelRepos personnelRepos, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.personnelDetailService = personnelDetailService;
        this.jwtUtils = jwtUtils;
        this.personnelRepos = personnelRepos;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String jwt = jwtUtils.generateToken(userDetails);

            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("ROLE_USER");

            LoginResponse loginResponse = new LoginResponse(jwt, userDetails.getUsername(), role);

            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody LoginRequest request) {
        Personnel p = new Personnel();
        p.setCodePersonnel(request.getLogin());
        p.setLoginPersonnel(request.getLogin());
        p.setPwdPersonnel(encoder.encode(request.getPassword()));
        p.setNomPersonnel("Nouvel utilisateur");
        p.setPhonePersonnel("0000");
        p.setSexePersonnel("Homme");
        p.setRolePersonnel(RolePersonnel.RESPONSABLE_ACADEMIQUE);

        personnelRepos.save(p);

        return "Compte créé";
    }
}
