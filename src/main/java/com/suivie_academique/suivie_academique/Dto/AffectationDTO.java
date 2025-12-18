package com.suivie_academique.suivie_academique.Dto;

import com.suivie_academique.suivie_academique.entities.AffectationId;
import com.suivie_academique.suivie_academique.entities.Cours;
import com.suivie_academique.suivie_academique.entities.Personnel;

public class AffectationDTO {

    private AffectationId codeAffectation;

    private PersonnelDTO personnel;

    private CoursDTO cours;

    public AffectationId getCodeAffectation() {
        return codeAffectation;
    }

    public void setCodeAffectation(AffectationId codeAffectation) {
        this.codeAffectation = codeAffectation;
    }

    public PersonnelDTO getPersonnel() {
        return personnel;
    }

    public void setPersonnel(PersonnelDTO personnel) {
        this.personnel = personnel;
    }

    public CoursDTO getCours() {
        return cours;
    }

    public void setCours(CoursDTO cours) {
        this.cours = cours;
    }
}
