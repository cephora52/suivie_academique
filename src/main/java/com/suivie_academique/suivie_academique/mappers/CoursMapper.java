package com.suivie_academique.suivie_academique.mappers;

import com.suivie_academique.suivie_academique.Dto.CoursDTO;
import com.suivie_academique.suivie_academique.entities.Cours;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="Spring")
public interface CoursMapper {

    CoursDTO toDTO(Cours cours);

    Cours toEntity(CoursDTO cours);

}
