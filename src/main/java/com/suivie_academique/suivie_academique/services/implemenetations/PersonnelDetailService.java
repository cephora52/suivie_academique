package com.suivie_academique.suivie_academique.services.implemenetations;

import com.suivie_academique.suivie_academique.entities.Personnel;
import com.suivie_academique.suivie_academique.repositories.PersonnelRepos;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class PersonnelDetailService implements UserDetailsService {

    private PersonnelRepos personnelRepos;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Personnel personnel = personnelRepos.findByLoginPersonnel(username);
        if (personnel == null){
            throw new UsernameNotFoundException("user not found with username:" + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + personnel.getRolePersonnel().name()));

        return new User(personnel.getLoginPersonnel(), personnel.getPwdPersonnel(), authorities);
    }
}
