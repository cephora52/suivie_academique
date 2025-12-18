package com.suivie_academique.suivie_academique.mappers;

import com.suivie_academique.suivie_academique.Dto.AffectationDTO;
import com.suivie_academique.suivie_academique.Dto.PersonnelDTO;
import com.suivie_academique.suivie_academique.entities.Affectation;
import com.suivie_academique.suivie_academique.entities.Personnel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="Spring", uses = {CoursMapper.class,PersonnelMapper.class})

public interface AffectationMapper {

    AffectationDTO toDTO(Affectation affectation);

    Affectation toEntity(AffectationDTO affectationDTO);



}
