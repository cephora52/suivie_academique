package com.suivie_academique.suivie_academique.services.implemenetations;

import com.suivie_academique.suivie_academique.Dto.SalleDTO;
import com.suivie_academique.suivie_academique.entities.Salle;
import com.suivie_academique.suivie_academique.mappers.SalleMapper;
import com.suivie_academique.suivie_academique.repositories.SalleRepos;
import com.suivie_academique.suivie_academique.services.interfaces.SalleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalleService implements SalleInterface {

    private final Logger logger = LoggerFactory.getLogger(SalleService.class);

    private SalleRepos salleRepos;
    private SalleMapper salleMapper;

    public SalleService(SalleRepos salleRepos, SalleMapper salleMapper) {
        this.salleRepos = salleRepos;
        this.salleMapper = salleMapper;
    }

    @Override
    public SalleDTO save(SalleDTO salleDTO) {
        if (salleDTO.getCodeSalle().isEmpty() || salleDTO.getContenance() < 10) {
            throw new RuntimeException("Donnees incorrecte");
        } else {
            Salle salle = salleRepos.save(salleMapper.toSalle(salleDTO));
            return salleMapper.toDTO(salle);
        }

    }

    @Override
    public List<SalleDTO> getAll() {
        return salleRepos.findAll().stream().map(salleMapper::toDTO).collect(Collectors.toList());
    }

    @Override

    public SalleDTO getById(String codeSalle) {
        Salle salle = salleRepos.findById(codeSalle).get();
        if (salle == null) {
            throw new RuntimeException("salle non trouvee");
        } else {
            return salleMapper.toDTO(salle);
        }
    }


    @Override
    public void delete(String codeSalle) {

        logger.info("Recherche de la sale avec code"+codeSalle);
        boolean exist = salleRepos.existsById(codeSalle);
        if (!exist) {
            logger.error("Salle introuvable donc modificaion impossible");
            throw new RuntimeException("Salle inexistante" + codeSalle);
        } else {
            salleRepos.deleteById(codeSalle);
            logger.info("Salle suprimer avec succes"+codeSalle);
        }
    }

    @Override
    public SalleDTO update(String codeSalle, SalleDTO salleDTO) {
        Salle salle = salleRepos.findById(codeSalle).get();
        if (salle == null) {
            throw new RuntimeException("Salle non trouve");
        } else {
            salle.setStatutSalle(salleDTO.getStatutSalle());
            salle.setDescSalle(salleDTO.getDescSalle());
            salle.setContenance(salleDTO.getContenance());
            salleRepos.save(salle);
            return salleMapper.toDTO(salle);
        }
    }
}