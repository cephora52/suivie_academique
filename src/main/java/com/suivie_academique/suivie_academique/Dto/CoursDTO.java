package com.suivie_academique.suivie_academique.Dto;

import lombok.Getter;
import lombok.Setter;



public class CoursDTO {

    private String codeCours;
    private String labelCours;
    private String descCours;
    private String nbCreditCours;
    private String nbHeureCours;

    public String getCodeCours() {
        return codeCours;
    }

    public void setCodeCours(String codeCours) {
        this.codeCours = codeCours;
    }

    public String getLabelCours() {
        return labelCours;
    }

    public void setLabelCours(String labelCours) {
        this.labelCours = labelCours;
    }

    public String getDescCours() {
        return descCours;
    }

    public void setDescCours(String descCours) {
        this.descCours = descCours;
    }

    public String getNbCreditCours() {
        return nbCreditCours;
    }

    public void setNbCreditCours(String nbCreditCours) {
        this.nbCreditCours = nbCreditCours;
    }

    public String getNbHeureCours() {
        return nbHeureCours;
    }

    public void setNbHeureCours(String nbHeureCours) {
        this.nbHeureCours = nbHeureCours;
    }
}
