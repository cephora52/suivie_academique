package com.suivie_academique.suivie_academique.services.interfaces;

import com.suivie_academique.suivie_academique.Dto.PersonnelDTO;
import com.suivie_academique.suivie_academique.utils.RolePersonnel;

import java.util.List;

public interface PersonnelInterface {
    PersonnelDTO save(PersonnelDTO personnelDTO);

    List<PersonnelDTO> getAll();

    PersonnelDTO getById(String codePersonnel);

    PersonnelDTO update(String codePersonnel, PersonnelDTO personnelDTO);
    void delete(String codePersonnel);

//    List<PersonnelDTO> findByRolePersonnel(String roleString);
//
//    List<PersonnelDTO> search(String roleString);
}
