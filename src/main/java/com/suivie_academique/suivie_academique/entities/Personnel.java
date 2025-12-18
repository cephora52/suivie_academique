package com.suivie_academique.suivie_academique.entities;

import com.suivie_academique.suivie_academique.utils.RolePersonnel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Personnel")
public class Personnel {

    @Id
    @Basic(optional = false)
    @Column(unique = true)
    private String codePersonnel;

    @Basic(optional = false)
    private String nomPersonnel;

    @Basic(optional = false)
    private String loginPersonnel;

    @Basic(optional = false)
    private String pwdPersonnel;

    @Basic(optional = false)
    private String sexePersonnel;

    @Basic(optional = false)
    private String phonePersonnel;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private RolePersonnel rolePersonnel;

    @OneToMany(mappedBy = "personnelProg", cascade = CascadeType.ALL)
    private List<Programmation> programmations;

    @OneToMany(mappedBy = "personnelVal", cascade = CascadeType.ALL)
    private List<Programmation> validations;

    public String getCodePersonnel() {
        return codePersonnel;
    }

    public void setCodePersonnel(String codePersonnel) {
        this.codePersonnel = codePersonnel;
    }

    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }

    public String getLoginPersonnel() {
        return loginPersonnel;
    }

    public void setLoginPersonnel(String loginPersonnel) {
        this.loginPersonnel = loginPersonnel;
    }

    public String getPwdPersonnel() {
        return pwdPersonnel;
    }

    public void setPwdPersonnel(String pwdPersonnel) {
        this.pwdPersonnel = pwdPersonnel;
    }

    public String getSexePersonnel() {
        return sexePersonnel;
    }

    public void setSexePersonnel(String sexePersonnel) {
        this.sexePersonnel = sexePersonnel;
    }

    public String getPhonePersonnel() {
        return phonePersonnel;
    }

    public void setPhonePersonnel(String phonePersonnel) {
        this.phonePersonnel = phonePersonnel;
    }

    public RolePersonnel getRolePersonnel() {
        return rolePersonnel;
    }

    public void setRolePersonnel(RolePersonnel rolePersonnel) {
        this.rolePersonnel = rolePersonnel;
    }

    public List<Programmation> getProgrammations() {
        return programmations;
    }

    public void setProgrammations(List<Programmation> programmations) {
        this.programmations = programmations;
    }

    public List<Programmation> getValidations() {
        return validations;
    }

    public void setValidations(List<Programmation> validations) {
        this.validations = validations;
    }

    public Personnel(String codePersonnel, String nomPersonnel, String loginPersonnel, String pwdPersonnel, String sexePersonnel, String phonePersonnel, RolePersonnel rolePersonnel, List<Programmation> programmations, List<Programmation> validations) {
        this.codePersonnel = codePersonnel;
        this.nomPersonnel = nomPersonnel;
        this.loginPersonnel = loginPersonnel;
        this.pwdPersonnel = pwdPersonnel;
        this.sexePersonnel = sexePersonnel;
        this.phonePersonnel = phonePersonnel;
        this.rolePersonnel = rolePersonnel;
        this.programmations = programmations;
        this.validations = validations;
    }

    public Personnel() {
    }
}
