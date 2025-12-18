package com.suivie_academique.suivie_academique.controllers;

import com.suivie_academique.suivie_academique.Dto.CoursDTO;
import com.suivie_academique.suivie_academique.Dto.ProgrammationDTO;
import com.suivie_academique.suivie_academique.services.implemenetations.CoursService;
import com.suivie_academique.suivie_academique.services.implemenetations.ProgrammationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programmation")
@AllArgsConstructor

public class ProgrammationController {

    private ProgrammationService programmationService;

    public ResponseEntity<?> save(ProgrammationDTO programmationDTO){
        try{
            ProgrammationDTO programmationDTO1= programmationService.save(programmationDTO);
            return new ResponseEntity<>(programmationDTO1, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity<List<ProgrammationDTO>> getAll(){
        return new ResponseEntity<>(programmationService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{codeCours}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ProgrammationDTO programmationDTO){
        try{
            return new ResponseEntity<>(programmationService.update(id, programmationDTO), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable int id){
        try {
            programmationService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable int id){
        try{
            return new ResponseEntity<>(programmationService.getById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
