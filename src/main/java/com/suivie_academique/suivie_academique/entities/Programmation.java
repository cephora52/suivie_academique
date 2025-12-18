package com.suivie_academique.suivie_academique.entities;


import com.suivie_academique.suivie_academique.utils.StatutProgrammation;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity



public class Programmation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic(optional = false)
    private int nbHeure;

    @Basic(optional = false)
    private Date dateProgrammation;

    @Basic(optional = false)
    private Date finProgrammation;

    @Basic(optional = false)
    private StatutProgrammation statutProgrammation;


    @ManyToOne
    @JoinColumn(name = "code_salle", referencedColumnName = "codeSalle" )
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "code_cours", referencedColumnName = "codeCours" )
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "code_personnel_prog", referencedColumnName = "codePersonnel" )
    private Personnel personnelProg;


    @ManyToOne(optional = true)
    @JoinColumn(name = "code_personnel_val", referencedColumnName = "codePersonnel" )
    private Personnel personnelVal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbHeure() {
        return nbHeure;
    }

    public void setNbHeure(int nbHeure) {
        this.nbHeure = nbHeure;
    }

    public Date getDateProgrammation() {
        return dateProgrammation;
    }

    public void setDateProgrammation(Date dateProgrammation) {
        this.dateProgrammation = dateProgrammation;
    }

    public Date getFinProgrammation() {
        return finProgrammation;
    }

    public void setFinProgrammation(Date finProgrammation) {
        this.finProgrammation = finProgrammation;
    }

    public StatutProgrammation getStatutProgrammation() {
        return statutProgrammation;
    }

    public void setStatutProgrammation(StatutProgrammation statutProgrammation) {
        this.statutProgrammation = statutProgrammation;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Personnel getPersonnelProg() {
        return personnelProg;
    }

    public void setPersonnelProg(Personnel personnelProg) {
        this.personnelProg = personnelProg;
    }

    public Personnel getPersonnelVal() {
        return personnelVal;
    }

    public void setPersonnelVal(Personnel personnelVal) {
        this.personnelVal = personnelVal;
    }

    public Programmation() {

    }

    public Programmation(int id, int nbHeure, Date dateProgrammation, Date finProgrammation, StatutProgrammation statutProgrammation, Salle salle, Cours cours, Personnel personnelProg, Personnel personnelVal) {
        this.id = id;
        this.nbHeure = nbHeure;
        this.dateProgrammation = dateProgrammation;
        this.finProgrammation = finProgrammation;
        this.statutProgrammation = statutProgrammation;
        this.salle = salle;
        this.cours = cours;
        this.personnelProg = personnelProg;
        this.personnelVal = personnelVal;
    }


}
