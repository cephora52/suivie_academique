package com.suivie_academique.suivie_academique.repositories;

import com.suivie_academique.suivie_academique.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalleRepos extends JpaRepository<Salle, String> {

    boolean existsByContenance(int contenance);

    List<Salle> findByContenanceGreaterThanEqual(int contenance);

    List<Salle> findByCodeSalleContaining(String codeSalle);

}
