package org.citronixx.citronix.Service;

import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Exception.ValidationException;
import org.citronixx.citronix.Model.entites.Champ.Champ;
import org.citronixx.citronix.Model.entites.Champ.ChampDTO;
import org.citronixx.citronix.Model.entites.Champ.Response.ResponseChampDTO;
import org.citronixx.citronix.Model.entites.Ferme.Ferme;
import org.citronixx.citronix.Model.MapStruct.ChampMapper;
import org.citronixx.citronix.Repository.ChampRepository;
import org.citronixx.citronix.Repository.FermeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChampServiceImplTest {

    @Mock
    private ChampRepository champRepository;

    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private ChampMapper champMapper;

    @InjectMocks
    private ChampServiceImpl champService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddChampToFerme_Success() {
        Long fermeId = 1L;
        ChampDTO champDTO = new ChampDTO();
        champDTO.setSuperficie(1.0);
        Ferme ferme = new Ferme();
        ferme.setId(fermeId);
        ferme.setSuperficie(10.0);
        ferme.setChamps(new ArrayList<>());

        Champ champ = new Champ();
        when(fermeRepository.findById(fermeId)).thenReturn(Optional.of(ferme));
        when(champMapper.champDTOToChamp(champDTO)).thenReturn(champ);
        when(champRepository.save(champ)).thenReturn(champ);
        when(champMapper.champToChampDTO(champ)).thenReturn(champDTO);

        ChampDTO result = champService.addChampToFerme(fermeId, champDTO);

        assertNotNull(result);
        verify(fermeRepository, times(1)).findById(fermeId);
        verify(champRepository, times(1)).save(champ);
    }

    @Test
    void testAddChampToFerme_FermeNotFound() {
        Long fermeId = 1L;
        ChampDTO champDTO = new ChampDTO();
        when(fermeRepository.findById(fermeId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                champService.addChampToFerme(fermeId, champDTO));

        assertEquals("Ferme not found with ID: " + fermeId, exception.getMessage());
    }

    @Test
    void testAddChampToFerme_ValidationFails() {
        Long fermeId = 1L;
        ChampDTO champDTO = new ChampDTO();
        champDTO.setSuperficie(5.0);
        Ferme ferme = new Ferme();
        ferme.setId(fermeId);
        ferme.setSuperficie(10.0);
        ferme.setChamps(new ArrayList<>());

        when(fermeRepository.findById(fermeId)).thenReturn(Optional.of(ferme));

        champDTO.setSuperficie(6.0); // Exceeds 50% of Ferme's superficie
        ValidationException exception = assertThrows(ValidationException.class, () ->
                champService.addChampToFerme(fermeId, champDTO));

        assertEquals("superficie", exception.getField());
    }

    @Test
    void testGetAllChamps() {
        List<Champ> champs = new ArrayList<>();
        champs.add(new Champ());
        when(champRepository.findAll()).thenReturn(champs);
        when(champMapper.champToChampDTO(champs)).thenReturn(new ArrayList<>());

        List<ChampDTO> result = champService.getAllChamps();

        assertNotNull(result);
        verify(champRepository, times(1)).findAll();
    }

    @Test
    void testGetChampById_Success() {
        Long champId = 1L;
        Champ champ = new Champ();
        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
        when(champMapper.champToChampDTO(champ)).thenReturn(new ChampDTO());

        ChampDTO result = champService.getChampById(champId);

        assertNotNull(result);
        verify(champRepository, times(1)).findById(champId);
    }

    @Test
    void testGetChampById_NotFound() {
        Long champId = 1L;
        when(champRepository.findById(champId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                champService.getChampById(champId));

        assertEquals("Champ with ID " + champId + " not found", exception.getMessage());
    }

    @Test
    void testDeleteChamp_Success() {
        Long champId = 1L;
        Champ champ = new Champ();
        champ.setNom("Test Champ");
        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));

        String result = champService.deleteChamp(champId);

        assertEquals("Champ Test Champ is deleted", result);
        verify(champRepository, times(1)).deleteById(champId);
    }

    @Test
    void testDeleteChamp_NotFound() {
        Long champId = 1L;
        when(champRepository.findById(champId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                champService.deleteChamp(champId));

        assertEquals("Champ with ID " + champId + " not found", exception.getMessage());
    }



    @Test
    void testUpdateChamp_NotFound() {
        Long champId = 1L;
        ChampDTO champDTO = new ChampDTO();
        when(champRepository.findById(champId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                champService.updateChamp(champId, champDTO));

        assertEquals("Champ with ID " + champId + " not found", exception.getMessage());
    }
}
