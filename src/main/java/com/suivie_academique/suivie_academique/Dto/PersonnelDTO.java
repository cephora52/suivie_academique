package com.suivie_academique.suivie_academique.Dto;

import com.suivie_academique.suivie_academique.utils.RolePersonnel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



public class PersonnelDTO {
    private String codePersonnel;

    private String nomPersonnel;

    private String loginPersonnel;

    private String pwdPersonnel;

    private String sexePersonnel;

    private String phonePersonnel;

    private RolePersonnel rolePersonnel;

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

    public PersonnelDTO(String codePersonnel, String nomPersonnel, String loginPersonnel, String pwdPersonnel, String sexePersonnel, String phonePersonnel, RolePersonnel rolePersonnel) {
        this.codePersonnel = codePersonnel;
        this.nomPersonnel = nomPersonnel;
        this.loginPersonnel = loginPersonnel;
        this.pwdPersonnel = pwdPersonnel;
        this.sexePersonnel = sexePersonnel;
        this.phonePersonnel = phonePersonnel;
        this.rolePersonnel = rolePersonnel;
    }

    public PersonnelDTO() {
    }
}
