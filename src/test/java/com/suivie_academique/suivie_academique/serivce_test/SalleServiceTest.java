package com.suivie_academique.suivie_academique.services.implemenetations;

import com.suivie_academique.suivie_academique.Dto.SalleDTO;
import com.suivie_academique.suivie_academique.entities.Salle;
import com.suivie_academique.suivie_academique.mappers.SalleMapper;
import com.suivie_academique.suivie_academique.repositories.SalleRepos;
import com.suivie_academique.suivie_academique.utils.SalleStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest

@ExtendWith(MockitoExtension.class)
class SalleServiceTest {

    @Mock
    private SalleRepos salleRepos;

    @Mock
    private SalleMapper salleMapper;

    @InjectMocks
    private SalleService salleService;

    private Salle salle;
    private SalleDTO salleDTO;

    @BeforeEach
    void setUp() {
        salle = new Salle(
                "S001",
                30,
                "Salle informatique",
                SalleStatus.LIBRE
        );

        salleDTO = new SalleDTO(
                "S001",
                30,
                "Salle informatique",
                SalleStatus.LIBRE
        );
    }

    // ===================== save =====================

    @Test
    void save_shouldSaveSalle_whenDataIsValid() {
        when(salleMapper.toSalle(salleDTO)).thenReturn(salle);
        when(salleRepos.save(salle)).thenReturn(salle);
        when(salleMapper.toDTO(salle)).thenReturn(salleDTO);

        SalleDTO result = salleService.save(salleDTO);

        assertNotNull(result);
        assertEquals("S001", result.getCodeSalle());
        assertEquals(SalleStatus.LIBRE, result.getStatutSalle());
        verify(salleRepos).save(salle);
    }

    @Test
    void save_shouldThrowException_whenCodeSalleIsEmpty() {
        salleDTO.setCodeSalle("");

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> salleService.save(salleDTO)
        );

        assertEquals("Donnees incorrecte", exception.getMessage());
        verify(salleRepos, never()).save(any());
    }

    @Test
    void save_shouldThrowException_whenContenanceIsInvalid() {
        salleDTO.setContenance(5);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> salleService.save(salleDTO)
        );

        assertEquals("Donnees incorrecte", exception.getMessage());
    }

    // ===================== getAll =====================

    @Test
    void getAll_shouldReturnListOfSalleDTO() {
        when(salleRepos.findAll()).thenReturn(List.of(salle));
        when(salleMapper.toDTO(salle)).thenReturn(salleDTO);

        List<SalleDTO> result = salleService.getAll();

        assertEquals(1, result.size());
        assertEquals(SalleStatus.LIBRE, result.get(0).getStatutSalle());
        verify(salleRepos).findAll();
    }

    // ===================== getById =====================

    @Test
    void getById_shouldReturnSalleDTO_whenSalleExists() {
        when(salleRepos.findById("S001")).thenReturn(Optional.of(salle));
        when(salleMapper.toDTO(salle)).thenReturn(salleDTO);

        SalleDTO result = salleService.getById("S001");

        assertEquals("S001", result.getCodeSalle());
        assertEquals(SalleStatus.LIBRE, result.getStatutSalle());
    }

    @Test
    void getById_shouldThrowException_whenSalleNotFound() {
        when(salleRepos.findById("S999")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> salleService.getById("S999")
        );

        assertEquals("Salle non trouvee", exception.getMessage());
    }

    // ===================== update =====================

    @Test
    void update_shouldUpdateSalle_whenSalleExists() {
        salleDTO.setStatutSalle(SalleStatus.OCCUPE);

        when(salleRepos.findById("S001")).thenReturn(Optional.of(salle));
        when(salleRepos.save(any(Salle.class))).thenReturn(salle);
        when(salleMapper.toDTO(salle)).thenReturn(salleDTO);

        SalleDTO result = salleService.update("S001", salleDTO);

        assertEquals(SalleStatus.OCCUPE, result.getStatutSalle());
        verify(salleRepos).save(salle);
    }

    @Test
    void update_shouldThrowException_whenSalleNotFound() {
        when(salleRepos.findById("S999")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> salleService.update("S999", salleDTO)
        );

        assertEquals("Salle non trouve", exception.getMessage());
    }

    // ===================== delete =====================

    @Test
    void delete_shouldDeleteSalle_whenSalleExists() {
        when(salleRepos.existsById("S001")).thenReturn(true);

        salleService.delete("S001");

        verify(salleRepos).deleteById("S001");
    }

    @Test
    void delete_shouldThrowException_whenSalleNotFound() {
        when(salleRepos.existsById("S999")).thenReturn(false);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> salleService.delete("S999")
        );

        assertEquals("Salle inexistante", exception.getMessage());
    }
}