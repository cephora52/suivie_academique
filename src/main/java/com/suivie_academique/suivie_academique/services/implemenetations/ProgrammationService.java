package com.suivie_academique.suivie_academique.services.implemenetations;

import com.suivie_academique.suivie_academique.Dto.CoursDTO;
import com.suivie_academique.suivie_academique.Dto.ProgrammationDTO;
import com.suivie_academique.suivie_academique.entities.Cours;
import com.suivie_academique.suivie_academique.entities.Programmation;
import com.suivie_academique.suivie_academique.mappers.ProgrammationMapper;
import com.suivie_academique.suivie_academique.repositories.ProgrammationRepos;
import com.suivie_academique.suivie_academique.services.interfaces.ProgrammationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service


public class ProgrammationService implements ProgrammationInterface {
    public ProgrammationService() {
    }

    private ProgrammationRepos programmationRepos;
    private ProgrammationMapper programmationMapper;

    public ProgrammationService(ProgrammationRepos programmationRepos, ProgrammationMapper programmationMapper) {
        this.programmationRepos = programmationRepos;
        this.programmationMapper = programmationMapper;
    }

    @Override
    public ProgrammationDTO save(ProgrammationDTO programmationDTO){
        if (programmationDTO.getDateProgrammation() == null || programmationDTO.getDateProgrammation() == null) {
            throw new RuntimeException("Donnée incorrecte");
        } else{
            Programmation programmation = programmationRepos.save(programmationMapper.toEntity(programmationDTO));
            return programmationMapper.toDTO(programmation);
        }
    }


    @Override
    public List<ProgrammationDTO> getAll() {
        return programmationRepos.findAll().stream().map(programmationMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public ProgrammationDTO getById(int id) {
        Programmation programmation = programmationRepos.findById(id).get();
        if (programmation == null) {
            throw new RuntimeException("cours non trouve");
        } else {
            return programmationMapper.toDTO(programmation);
        }
    }
    @Override
    public void delete(int id) {
        boolean exist = programmationRepos.existsById(id);
        if (!exist) {
            throw new RuntimeException("Programmation inexistant");
        } else {
            programmationRepos.deleteById(id);
        }
    }

    @Override
    public ProgrammationDTO update(int id, ProgrammationDTO programmationDTO) {
        Programmation programmation = programmationRepos.findById(id).get();
        if(programmation == null) {
            throw new RuntimeException("Programmation non trouve");
        } else{
            programmation.setNbHeure(programmationDTO.getNbHeure());
            programmation.setDateProgrammation(programmationDTO.getDateProgrammation());
            programmation.setFinProgrammation(programmationDTO.getFinProgrammation());
            programmationRepos.save(programmation);
            return programmationMapper.toDTO(programmation);
        }

    }
}
