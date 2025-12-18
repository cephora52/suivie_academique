package com.suivie_academique.suivie_academique.utils;

import com.suivie_academique.suivie_academique.entities.Personnel;
import com.suivie_academique.suivie_academique.repositories.PersonnelRepos;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
@Service
public class CodeGenerator {

    private PersonnelRepos personnelRepos;

    public CodeGenerator(PersonnelRepos personnelRepos) {
        this.personnelRepos = personnelRepos;
    }

    public String generate(String roleString) {
        String prefix = switch (roleString) {
            case "ENSEIGANT" -> "ENS";
            case "RESPONSABLE_ACADEMIQUE" -> "RA";
            case "RESPONSABLE_DISCIPLINE" -> "RD";
            default -> null;
        };

        long randomNumber = ThreadLocalRandom.current().nextInt(1000, 10000);
        int year = LocalDate.now().getYear();
        String code = prefix + year + randomNumber;
        if (personnelRepos.existsById(code))
            return generate(roleString);
        else
            return code;
    }
}
