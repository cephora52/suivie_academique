package com.suivie_academique.suivie_academique.services.implemenetations;

import com.suivie_academique.suivie_academique.Dto.CoursDTO;
import com.suivie_academique.suivie_academique.entities.Cours;
import com.suivie_academique.suivie_academique.mappers.CoursMapper;
import com.suivie_academique.suivie_academique.repositories.CoursRepos;
import com.suivie_academique.suivie_academique.services.interfaces.CoursInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service

public class CoursService implements CoursInterface {

    private CoursRepos coursRepos;
    private CoursMapper coursMapper;

    public CoursService(CoursRepos coursRepos, CoursMapper coursMapper) {
        this.coursRepos = coursRepos;
        this.coursMapper = coursMapper;
    }

    @Override
    public CoursDTO save(CoursDTO coursDTO){
        if (coursDTO.getCodeCours().isEmpty() ||
                coursDTO.getLabelCours().isEmpty()) {
            throw new RuntimeException("Donnée incorrecte");
        } else

            return coursMapper.toDTO(coursRepos.save(coursMapper.toEntity(coursDTO)));
        }


    @Override
    public List<CoursDTO> getAll() {
        return coursRepos.findAll().stream().map(coursMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CoursDTO getById(String codeCours) {
        Cours cours = coursRepos.findById(codeCours).get();
        if (cours == null) {
            throw new RuntimeException("cours non trouve");
        } else {
            return coursMapper.toDTO(cours);
        }
    }
    @Override
    public void delete(String codeCours) {
        boolean exist = coursRepos.existsById(codeCours);
        if (!exist) {
            throw new RuntimeException("Cours inexistant");
        } else {
            coursRepos.deleteById(codeCours);
        }
    }

    @Override
    public CoursDTO update(String codeCours, CoursDTO coursDTO) {
        Cours cours = coursRepos.findById(codeCours).get();
               if(cours == null) {
                   throw new RuntimeException("cours non trouve");
               } else{
        cours.setLabelCours(coursDTO.getLabelCours());
        cours.setDescCours(coursDTO.getDescCours());
        cours.setNbCreditCours(coursDTO.getNbCreditCours());
        cours.setNbHeureCours(coursDTO.getNbHeureCours());
        coursRepos.save(cours);
        return coursMapper.toDTO(cours);
    }

}
}
