package com.suivie_academique.suivie_academique.Dto;

import com.suivie_academique.suivie_academique.utils.SalleStatus;

public class SalleDTO {
    private  String codeSalle;
    private String DescSalle;
    private int Contenance;
    private SalleStatus statutSalle;

    public SalleDTO(String codeSalle, String descSalle, int contenance, SalleStatus statutSalle) {
        this.codeSalle = codeSalle;
        DescSalle = descSalle;
        Contenance = contenance;
        this.statutSalle = statutSalle;
    }

    public SalleDTO() {
    }

    public SalleDTO(String s001, int i, String salle_informatique, SalleStatus libre) {
    }

    public String getCodeSalle() {
        return codeSalle;
    }

    public void setCodeSalle(String codeSalle) {
        this.codeSalle = codeSalle;
    }

    public String getDescSalle() {
        return DescSalle;
    }

    public void setDescSalle(String descSalle) {
        DescSalle = descSalle;
    }

    public int getContenance() {
        return Contenance;
    }

    public void setContenance(int contenance) {
        Contenance = contenance;
    }

    public SalleStatus getStatutSalle() {
        return statutSalle;
    }

    public void setStatutSalle(SalleStatus statutSalle) {
        this.statutSalle = statutSalle;
    }
}
