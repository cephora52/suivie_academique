package com.suivie_academique.suivie_academique.Dto;

import java.util.Date;

public class ProgrammationDTO {

    private int id;

    private int nbHeure;

    private Date dateProgrammation;

    private Date finProgrammation;

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
}
