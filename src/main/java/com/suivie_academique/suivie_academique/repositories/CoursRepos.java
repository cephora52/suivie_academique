package com.suivie_academique.suivie_academique.repositories;

import com.suivie_academique.suivie_academique.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CoursRepos extends JpaRepository<Cours, String> {

//    @Query("SELECT c FROM Cours c WHERE c.labelCours LIKE %:token%")
//    List<Cours> searchByName (@Param("token") String token);

}

