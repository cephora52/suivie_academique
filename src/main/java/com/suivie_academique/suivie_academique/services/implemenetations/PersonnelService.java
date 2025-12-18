package com.suivie_academique.suivie_academique.services.implemenetations;

import com.suivie_academique.suivie_academique.Dto.PersonnelDTO;
import com.suivie_academique.suivie_academique.entities.Personnel;
import com.suivie_academique.suivie_academique.mappers.PersonnelMapper;
import com.suivie_academique.suivie_academique.repositories.PersonnelRepos;
import com.suivie_academique.suivie_academique.services.interfaces.PersonnelInterface;
import com.suivie_academique.suivie_academique.utils.CodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonnelService implements PersonnelInterface {


    private PersonnelMapper personnelMapper;
    private PersonnelRepos personnelRepos;

    private CodeGenerator codeGenerator;

    public PersonnelService(PersonnelMapper personnelMapper, PersonnelRepos personnelRepos, CodeGenerator codeGenerator) {
        this.personnelMapper = personnelMapper;
        this.personnelRepos = personnelRepos;
        this.codeGenerator = codeGenerator;
    }

    @Override
    public PersonnelDTO save(PersonnelDTO personnelDTO) {
//         Validation simple
        if (personnelDTO.getNomPersonnel().isEmpty() || personnelDTO.getPhonePersonnel().isEmpty()) {
            throw new RuntimeException("Données incorrectes");
        } else {
            personnelDTO.setCodePersonnel(codeGenerator.generate(personnelDTO.getRolePersonnel().name()));
            return personnelMapper.toDTO(personnelRepos.save(personnelMapper.toEntity(personnelDTO)));
        }
    }

    @Override
    public List<PersonnelDTO> getAll() {
        return personnelRepos.findAll().stream()
                .map(personnelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonnelDTO getById(String codePersonnel) {
        Personnel personnel = personnelRepos.findById(codePersonnel).get();
        if (personnel == null) {
            throw new RuntimeException("Personnel non trouvé");
        }else{
            return personnelMapper.toDTO(personnel);
        }
    }

    @Override
    public PersonnelDTO update(String codePersonnel, PersonnelDTO personnelDTO) {
        Personnel personnel = personnelRepos.findById(codePersonnel).get();
        if (personnel == null) {
            throw new RuntimeException("Personnel non trouvé");
        } else {

            // Mise à jour des champs
            personnel.setNomPersonnel(personnelDTO.getNomPersonnel());
            personnel.setLoginPersonnel(personnelDTO.getLoginPersonnel());
            personnel.setPwdPersonnel(personnelDTO.getPwdPersonnel());
            personnel.setSexePersonnel(personnelDTO.getSexePersonnel());
            personnel.setPhonePersonnel(personnelDTO.getPhonePersonnel());

            personnelRepos.save(personnel);
            return personnelMapper.toDTO(personnel);
        }
    }

    @Override
    public void delete(String codePersonnel) {
        boolean exist = personnelRepos.existsById(codePersonnel);
        if (!exist) {
            throw new RuntimeException("Personnel inexistant");
        } else {
            personnelRepos.deleteById(codePersonnel);
        }
    }

//    @Override
//    public List<PersonnelDTO> findByRolePersonnel(String roleString) {
//        return personnelRepos.searchByName(roleString).stream().map(personnelMapper::toDTO).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<PersonnelDTO> search(String name) {
//        return personnelRepos.searchByName(name).stream().map(personnelMapper::toDTO).collect(Collectors.toList());
//    }

}