package com.suivie_academique.suivie_academique.mappers;

import ch.qos.logback.core.model.ComponentModel;
import com.suivie_academique.suivie_academique.Dto.SalleDTO;
import com.suivie_academique.suivie_academique.entities.Salle;
import com.suivie_academique.suivie_academique.utils.SalleStatus;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SalleMapper {
    public SalleDTO toDTO(Salle salle);

    public Salle toSalle(SalleDTO salleDTO);


}
