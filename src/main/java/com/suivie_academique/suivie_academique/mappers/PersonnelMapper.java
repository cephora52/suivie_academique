package com.suivie_academique.suivie_academique.mappers;

import com.suivie_academique.suivie_academique.Dto.PersonnelDTO;
import com.suivie_academique.suivie_academique.entities.Personnel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "Spring")

public interface PersonnelMapper {
    PersonnelDTO toDTO(Personnel personnel);

    Personnel toEntity(PersonnelDTO personnelDTO);
}
