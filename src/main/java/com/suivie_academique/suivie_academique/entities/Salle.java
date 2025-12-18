package com.suivie_academique.suivie_academique.entities;

import com.suivie_academique.suivie_academique.utils.SalleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity()
@Table(name = "Salle")

public class Salle {

    @Id
    @Basic(optional = false)

    private String codeSalle ;

    private String descSalle ;

    @Basic(optional = false)

    private int contenance;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private SalleStatus statutSalle;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL)
    private List<Programmation> programmations;

    public Salle(String s001, int i, String salle_informatique, SalleStatus libre) {
    }


    public List<Programmation> getProgrammations() {
        return programmations;
    }

    public void setProgrammations(List<Programmation> programmations) {
        this.programmations = programmations;
    }

    public Salle(String codeSalle, String descSalle, int contenance, SalleStatus statutSalle, List<Programmation> programmations) {
        this.codeSalle = codeSalle;
        this.descSalle = descSalle;
        this.contenance = contenance;
        this.statutSalle = statutSalle;
        this.programmations = programmations;
    }

    public Salle() {
    }

    public String getCodeSalle() {
        return codeSalle;
    }

    public void setCodeSalle(String codeSalle) {
        this.codeSalle = codeSalle;
    }

    public String getDescSalle() {
        return descSalle;
    }

    public void setDescSalle(String descSalle) {
        this.descSalle = descSalle;
    }

    public int getContenance() {
        return contenance;
    }

    public void setContenance(int contenance) {
        this.contenance = contenance;
    }

    public SalleStatus getStatutSalle() {
        return statutSalle;
    }

    public void setStatutSalle(SalleStatus statutSalle) {
        this.statutSalle = statutSalle;
    }


}
