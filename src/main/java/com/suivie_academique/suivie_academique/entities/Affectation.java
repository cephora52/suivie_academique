package com.suivie_academique.suivie_academique.entities;


import com.suivie_academique.suivie_academique.utils.RolePersonnel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity



public class Affectation {

    @EmbeddedId
    private AffectationId codeAffectation;

    @MapsId("codePersonnel") // lie à l'attribut codePersonnel dans AffectationId
    @JoinColumn(name = "code_personnel", referencedColumnName = "codePersonnel")
    @Basic(optional = false)
    @ManyToOne(optional = false)
    private Personnel personnel;

    @MapsId("codeCours") // lie à l'attribut codeCours dans AffectationId
    @JoinColumn(name = "code_cours", referencedColumnName = "codeCours")
    @Basic(optional = false)
    @ManyToOne(optional = false)
    private Cours cours;

    public AffectationId getCodeAffectation() {
        return codeAffectation;
    }

    public void setCodeAffectation(AffectationId codeAffectation) {
        this.codeAffectation = codeAffectation;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Affectation(AffectationId codeAffectation, Personnel personnel, Cours cours) {
        this.codeAffectation = codeAffectation;
        this.personnel = personnel;
        this.cours = cours;
    }

    public Affectation() {
    }
}
