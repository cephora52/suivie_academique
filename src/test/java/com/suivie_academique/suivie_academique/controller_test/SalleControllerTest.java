package com.suivie_academique.suivie_academique.controller_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suivie_academique.suivie_academique.Dto.SalleDTO;
import com.suivie_academique.suivie_academique.controllers.SalleController;
import com.suivie_academique.suivie_academique.services.implemenetations.SalleService;
import com.suivie_academique.suivie_academique.utils.SalleStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SalleController.class)
class SalleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalleService salleService;

    @Autowired
    private ObjectMapper objectMapper;

    // ================= POST /salle =================

    @Test
    void save_shouldReturn201_whenSalleIsValid() throws Exception {

        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setCodeSalle("S001");
        salleDTO.setDescSalle("Salle informatique");
        salleDTO.setContenance(30);
        salleDTO.setStatutSalle(SalleStatus.LIBRE);

        when(salleService.save(any(SalleDTO.class))).thenReturn(salleDTO);

        mockMvc.perform(post("/salle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salleDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.codeSalle").value("S001"))
                .andExpect(jsonPath("$.descSalle").value("Salle informatique"))
                .andExpect(jsonPath("$.contenance").value(30))
                .andExpect(jsonPath("$.statutSalle").value("LIBRE"));
    }

    @Test
    void save_shouldReturn400_whenInvalidData() throws Exception {

        SalleDTO salleDTO = new SalleDTO();

        when(salleService.save(any(SalleDTO.class)))
                .thenThrow(new RuntimeException("Donnees incorrecte"));

        mockMvc.perform(post("/salle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salleDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Donnees incorrecte"));
    }

    // ================= GET /salle =================

    @Test
    void getAll_shouldReturnList() throws Exception {

        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setCodeSalle("S002");
        salleDTO.setDescSalle("Salle amphi");
        salleDTO.setContenance(100);
        salleDTO.setStatutSalle(SalleStatus.OCCUPE);

        when(salleService.getAll()).thenReturn(List.of(salleDTO));

        mockMvc.perform(get("/salle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].codeSalle").value("S002"))
                .andExpect(jsonPath("$[0].statutSalle").value("OCCUPE"));
    }

    // ================= GET /salle/{codeSalle} =================

    @Test
    void show_shouldReturnSalle_whenExists() throws Exception {

        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setCodeSalle("S003");
        salleDTO.setDescSalle("Salle conférence");
        salleDTO.setContenance(50);
        salleDTO.setStatutSalle(SalleStatus.LIBRE);

        when(salleService.getById("S003")).thenReturn(salleDTO);

        mockMvc.perform(get("/salle/{codeSalle}", "S003"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codeSalle").value("S003"))
                .andExpect(jsonPath("$.statutSalle").value("LIBRE"));
    }

    @Test
    void show_shouldReturn400_whenNotFound() throws Exception {

        when(salleService.getById("S999"))
                .thenThrow(new RuntimeException("salle non trouvee"));

        mockMvc.perform(get("/salle/{codeSalle}", "S999"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("salle non trouvee"));
    }

    // ================= PUT /salle/{codeSalle} =================

    @Test
    void update_shouldReturnUpdatedSalle() throws Exception {

        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setCodeSalle("S001");
        salleDTO.setDescSalle("Salle mise à jour");
        salleDTO.setContenance(40);
        salleDTO.setStatutSalle(SalleStatus.FERMER);

        when(salleService.update(eq("S001"), any(SalleDTO.class)))
                .thenReturn(salleDTO);

        mockMvc.perform(put("/salle/{codeSalle}", "S001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salleDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statutSalle").value("FERMER"));
    }

    // ================= DELETE /salle/{codeSalle} =================

    @Test
    void delete_shouldReturn204_whenDeleted() throws Exception {

        doNothing().when(salleService).delete("S001");

        mockMvc.perform(delete("/salle/{codeSalle}", "S001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_shouldReturn400_whenNotFound() throws Exception {

        doThrow(new RuntimeException("Salle inexistante"))
                .when(salleService)
                .delete("S999");

        mockMvc.perform(delete("/salle/{codeSalle}", "S999"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Salle inexistante"));
    }
}