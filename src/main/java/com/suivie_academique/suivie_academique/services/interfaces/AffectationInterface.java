package com.suivie_academique.suivie_academique.services.interfaces;

import com.suivie_academique.suivie_academique.Dto.AffectationDTO;
import com.suivie_academique.suivie_academique.entities.AffectationId;

import java.util.List;

public interface AffectationInterface {

    AffectationDTO save(AffectationDTO affectationDTO);

    List<AffectationDTO> getAll();

    AffectationDTO getById(AffectationId codeAffectation);
    AffectationDTO update(AffectationId codeAffectation, AffectationDTO affectationDTO);

    void delete(AffectationId codeAffectation);
}
