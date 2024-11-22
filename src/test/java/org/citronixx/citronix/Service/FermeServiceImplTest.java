package org.citronixx.citronix.Service;

import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Model.MapStruct.FermeMapper;
import org.citronixx.citronix.Model.entites.Ferme.Ferme;
import org.citronixx.citronix.Model.entites.Ferme.FermeDTO;
import org.citronixx.citronix.Model.entites.Ferme.Response.ResponseFermeDTO;
import org.citronixx.citronix.Repository.FermeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FermeServiceImplTest {

    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private FermeMapper fermeMapper;

    @InjectMocks
    private FermeServiceImpl fermeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFerme_Success() {
        FermeDTO fermeDTO = new FermeDTO();
        fermeDTO.setNom("Ferme Test");
        fermeDTO.setLocalisation("Location Test");
        fermeDTO.setSuperficie(100.0);

        Ferme ferme = new Ferme();
        ferme.setNom("Ferme Test");

        Ferme savedFerme = new Ferme();
        savedFerme.setNom("Ferme Test");
        savedFerme.setDateCreation(LocalDate.now());

        ResponseFermeDTO responseFermeDTO = new ResponseFermeDTO();
        responseFermeDTO.setNom("Ferme Test");

        when(fermeMapper.fermeDTOToFerme(fermeDTO)).thenReturn(ferme);
        when(fermeRepository.save(ferme)).thenReturn(savedFerme);
        when(fermeMapper.fermeToResponseFermeDTO(savedFerme)).thenReturn(responseFermeDTO);

        ResponseFermeDTO result = fermeService.saveFerme(fermeDTO);

        assertNotNull(result);
        assertEquals("Ferme Test", result.getNom());
        verify(fermeRepository, times(1)).save(ferme);
    }

    @Test
    void testGetFermeById_Success() {
        Long id = 1L;
        Ferme ferme = new Ferme();
        ferme.setId(id);
        ferme.setNom("Ferme Test");

        ResponseFermeDTO responseFermeDTO = new ResponseFermeDTO();
        responseFermeDTO.setNom("Ferme Test");

        when(fermeRepository.findById(id)).thenReturn(Optional.of(ferme));
        when(fermeMapper.fermeToResponseFermeDTO(ferme)).thenReturn(responseFermeDTO);

        ResponseFermeDTO result = fermeService.getFermeById(id);

        assertNotNull(result);
        assertEquals("Ferme Test", result.getNom());
        verify(fermeRepository, times(1)).findById(id);
    }

    @Test
    void testGetFermeById_NotFound() {
        Long id = 1L;

        when(fermeRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                fermeService.getFermeById(id));

        assertEquals("Ferme with ID 1 not found", exception.getMessage());
        verify(fermeRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteFerme_Success() {
        Long id = 1L;

        when(fermeRepository.existsById(id)).thenReturn(true);

        boolean result = fermeService.deleteFerme(id);

        assertTrue(result);
        verify(fermeRepository, times(1)).existsById(id);
        verify(fermeRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteFerme_NotFound() {
        Long id = 1L;

        when(fermeRepository.existsById(id)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                fermeService.deleteFerme(id));

        assertEquals("Ferme with ID 1 not found", exception.getMessage());
        verify(fermeRepository, times(1)).existsById(id);
        verify(fermeRepository, never()).deleteById(id);
    }
}
