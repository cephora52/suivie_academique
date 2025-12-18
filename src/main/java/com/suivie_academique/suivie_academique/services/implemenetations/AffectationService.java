package com.suivie_academique.suivie_academique.services.implemenetations;

import com.suivie_academique.suivie_academique.Dto.AffectationDTO;
import com.suivie_academique.suivie_academique.entities.Affectation;
import com.suivie_academique.suivie_academique.entities.AffectationId;
import com.suivie_academique.suivie_academique.mappers.AffectationMapper;
import com.suivie_academique.suivie_academique.mappers.CoursMapper;
import com.suivie_academique.suivie_academique.mappers.PersonnelMapper;
import com.suivie_academique.suivie_academique.repositories.AffectationRepos;
import com.suivie_academique.suivie_academique.repositories.CoursRepos;
import com.suivie_academique.suivie_academique.repositories.PersonnelRepos;
import com.suivie_academique.suivie_academique.services.interfaces.AffectationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AffectationService implements AffectationInterface {

    private AffectationRepos affectationRepos;

    private CoursRepos coursRepos;

    private PersonnelRepos personnelRepos;

    private PersonnelMapper personnelMapper;

    private CoursMapper coursMapper;

    private AffectationMapper affectationMapper;

    public AffectationService(AffectationRepos affectationRepos, CoursRepos coursRepos, PersonnelRepos personnelRepos, PersonnelMapper personnelMapper, CoursMapper coursMapper, AffectationMapper affectationMapper) {
        this.affectationRepos = affectationRepos;
        this.coursRepos = coursRepos;
        this.personnelRepos = personnelRepos;
        this.personnelMapper = personnelMapper;
        this.coursMapper = coursMapper;
        this.affectationMapper = affectationMapper;
    }

    public AffectationService(){}
    @Override
    public AffectationDTO save(AffectationDTO affectationDTO) {

      //  if (coursRepos.existsById(affectationDTO.getCodeAffectation().getCodeCours())) {

      //  } else {

//            return affectationMapper.toDTO(affectionRepos.save(affectationMapper.toEntity(affectationDTO)));
//        }
        return null;
    }

    @Override
    public List<AffectationDTO> getAll() {
        return affectationRepos.findAll().stream().map(affectationMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AffectationDTO getById(AffectationId codeAffectation) {
        Affectation affectation = affectationRepos.findById(codeAffectation).orElse(null);
        if(affectation == null){
            throw new RuntimeException("Code Affectation non trouve");
        } else {
            return affectationMapper.toDTO(affectation);
        }
    }



    @Override
    public void delete(AffectationId codeAffectation) {
            boolean exist = affectationRepos.existsById(codeAffectation);
            if(!exist){
                throw new RuntimeException("Cours non trouvee");
            }else{
                affectationRepos.deleteById(codeAffectation);
            }
        }


    @Override
    public AffectationDTO update(AffectationId codeAffectation, AffectationDTO affectationDTO) {
        Affectation affectation = affectationRepos.findById(codeAffectation).orElse(null);
        if (affectation == null){
            throw new RuntimeException("code affection non trouve");
        }else{
            affectation.setPersonnel(personnelMapper.toEntity(affectationDTO.getPersonnel()));
            affectation.setCours(coursMapper.toEntity(affectationDTO.getCours()));
            affectationRepos.save(affectation);
            return affectationMapper.toDTO(affectation);
        }
    }


}
