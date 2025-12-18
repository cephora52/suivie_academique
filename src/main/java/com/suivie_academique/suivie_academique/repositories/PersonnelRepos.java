package com.suivie_academique.suivie_academique.repositories;

import com.suivie_academique.suivie_academique.entities.Personnel;
import com.suivie_academique.suivie_academique.utils.RolePersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelRepos extends JpaRepository<Personnel, String> {

//    @Query("Select p from Personnel p WHERE p.nomPersonnel LIKE '%:token%'")
//    List<Personnel> searchByName (@Param("token") String token);
//
//    @Query(value = "SELECT COUNT(*) from personnel WHERE sexePersonnel =:sexe", nativeQuery = true)
//    int countBySexe(@Param("sexe") char sexe);
//
//    List<Personnel> findByRole(RolePersonnel rolePersonnel);
//
//    @Query("SELECT p FROM Personnel p WHERE p.sexePersonnel = :Sexe")
//    List<Personnel> findBySexe(@Param("sexe")char sexe);
//
//    @Query("SELECT  COUNT (p) FROM Personnel p WHERE p.rolePersonnel = :role")
//    long countByRole(@Param("role") RolePersonnel role);
//
//    @Query(value = "SELECT ROLE FROM Personnel WHERE Rolepersonnel", nativeQuery = true)
//    List<Personnel> searchByRole(@Param("role") RolePersonnel rolepersonnel);

Personnel findByLoginPersonnel(String loginPersonnel);

}
