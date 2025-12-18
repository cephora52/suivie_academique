package com.suivie_academique.suivie_academique.mappers;

import com.suivie_academique.suivie_academique.Dto.ProgrammationDTO;
import com.suivie_academique.suivie_academique.entities.Programmation;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "Spring")
public interface ProgrammationMapper {
     ProgrammationDTO toDTO(Programmation programmation);

    Programmation toEntity(ProgrammationDTO programmationDTO);

}
