package com.suivie_academique.suivie_academique.services.interfaces;


import com.suivie_academique.suivie_academique.Dto.ProgrammationDTO;

import java.util.List;

public interface ProgrammationInterface {

    ProgrammationDTO save(ProgrammationDTO programmationDTO);

    List<ProgrammationDTO> getAll();

    ProgrammationDTO getById(int id);

    ProgrammationDTO update(int id, ProgrammationDTO programmationDTO);

    void delete(int id);
}
