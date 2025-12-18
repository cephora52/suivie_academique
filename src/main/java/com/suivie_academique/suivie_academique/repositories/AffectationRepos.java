package com.suivie_academique.suivie_academique.repositories;

import com.suivie_academique.suivie_academique.entities.Affectation;
import com.suivie_academique.suivie_academique.entities.AffectationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AffectationRepos extends JpaRepository<Affectation, AffectationId>{

    // Trouver toute les affectations d'un personnel

//@Query("SELECT a FROM Affectation a where a.personnel.codePersonnel = :id ")
//List<Affectation> findByPersonnel(@Param("id")String idPersonnel);
//
//    // Trouver les affectation d'une salle
//
//@Query("SELECT a FROM Affectation a WHERE a.salle.codeSalle = :codeSalle")
//    List<Affectation> findBySalle(@Param("codeSalle") String codeSalle);
}

