package com.suivie_academique.suivie_academique.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "Cours")


public class Cours {

    @Id
    @Basic(optional = false)
    @Column(unique = true)
    @Length(min = 5)
    private String codeCours;

    @Basic(optional = false)
    private String labelCours;

    @Basic(optional = false)
    private String descCours;

    @Basic(optional = false)
    private String nbCreditCours;

    @Basic(optional = false)
    private String nbHeureCours;

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL)
    private List<Programmation> programmations;

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

    public List<Programmation> getProgrammations() {
        return programmations;
    }

    public void setProgrammations(List<Programmation> programmations) {
        this.programmations = programmations;
    }

    public Cours(String codeCours, String labelCours, String descCours, String nbCreditCours, String nbHeureCours, List<Programmation> programmations) {
        this.codeCours = codeCours;
        this.labelCours = labelCours;
        this.descCours = descCours;
        this.nbCreditCours = nbCreditCours;
        this.nbHeureCours = nbHeureCours;
        this.programmations = programmations;
    }

    public Cours() {
    }
}
