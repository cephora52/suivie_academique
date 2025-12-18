package com.suivie_academique.suivie_academique.controllers;

import com.suivie_academique.suivie_academique.Dto.AffectationDTO;
import com.suivie_academique.suivie_academique.Dto.PersonnelDTO;
import com.suivie_academique.suivie_academique.Dto.SalleDTO;
import com.suivie_academique.suivie_academique.entities.AffectationId;
import com.suivie_academique.suivie_academique.services.implemenetations.AffectationService;
import com.suivie_academique.suivie_academique.services.implemenetations.SalleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/affectation")
@AllArgsConstructor

public class AffectationController {

    private AffectationService affectationService;

    public ResponseEntity<?> save(AffectationDTO affectationDTO){
        try{
            AffectationDTO affectationDTO1= affectationService.save(affectationDTO);
            return new ResponseEntity<>(affectationDTO1, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity<List<AffectationDTO>> getAll(){
        return new ResponseEntity<>(affectationService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{codeAffectation}")
    public ResponseEntity<?> update(@PathVariable AffectationId codeAffectation, @RequestBody AffectationDTO affectationDTO){
        try{
            return new ResponseEntity<>(affectationService.update(codeAffectation, affectationDTO), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{codeAffectation}")
    public ResponseEntity<?>delete(@PathVariable AffectationId codeAffectation){
        try {
            affectationService.delete( codeAffectation);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{codeAffectation}")
    public ResponseEntity<?> show(@PathVariable AffectationId codeAffectation){
        try{
            return new ResponseEntity<>(affectationService.getById( codeAffectation), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
