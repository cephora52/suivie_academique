package com.suivie_academique.suivie_academique.services.interfaces;

import com.suivie_academique.suivie_academique.Dto.SalleDTO;

import java.util.List;

public interface SalleInterface {

    SalleDTO save(SalleDTO salleDTO);

    List<SalleDTO> getAll();

    SalleDTO getById(String codeSalle);

    SalleDTO update(String codeSalle, SalleDTO salleSTO);

    void delete(String codeSalle);
}
