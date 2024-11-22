package org.citronixx.citronix.Service;


import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Exception.ValidationException;
import org.citronixx.citronix.Model.MapStruct.ArbreMapper;
import org.citronixx.citronix.Model.MapStruct.ChampMapper;
import org.citronixx.citronix.Model.entites.Arbre.Arbre;
import org.citronixx.citronix.Model.entites.Arbre.ArbreDTO;
import org.citronixx.citronix.Model.entites.Arbre.Response.ResponseArbreDTO;
import org.citronixx.citronix.Model.entites.Champ.Champ;
import org.citronixx.citronix.Repository.ArbreRepository;
import org.citronixx.citronix.Repository.ChampRepository;
import org.citronixx.citronix.Service.Interface.ArbreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArbreServiceImplTest {

    @Mock
    private ArbreRepository arbreRepository;

    @Mock
    private ChampRepository champRepository;

    @Mock
    private ArbreMapper arbreMapper;

    @Mock
    private ChampMapper champMapper;

    @InjectMocks
    private ArbreService arbreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddArbreToChamp_Success() {
        Long champId = 1L;
        ArbreDTO arbreDTO = new ArbreDTO();
        arbreDTO.setDatePlantation(LocalDate.of(2024, 3, 15));
        Champ champ = new Champ();
        champ.setArbres(new ArrayList<>());
        champ.setSuperficie(1.0); // 1 hectare

        Arbre arbre = new Arbre();
        arbre.setChamp(champ);

        Arbre savedArbre = new Arbre();
        savedArbre.setDatePlantation(arbreDTO.getDatePlantation());
        savedArbre.setChamp(champ);

        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));
        when(arbreMapper.arbreDTOToArbre(arbreDTO)).thenReturn(arbre);
        when(arbreRepository.save(arbre)).thenReturn(savedArbre);
        when(champMapper.champToResponseChamp(champ)).thenReturn(null);

        ResponseArbreDTO response = arbreService.addArbreToChamp(champId, arbreDTO);

        assertNotNull(response);
        assertEquals(arbreDTO.getDatePlantation(), response.getDatePlantation());
        verify(arbreRepository, times(1)).save(arbre);
    }

    @Test
    void testAddArbreToChamp_ChampNotFound() {
        Long champId = 1L;
        ArbreDTO arbreDTO = new ArbreDTO();

        when(champRepository.findById(champId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () ->
                arbreService.addArbreToChamp(champId, arbreDTO));

        assertEquals("Champ with ID 1 not found", exception.getMessage());
    }

    @Test
    void testAddArbreToChamp_InvalidTreeSpacing() {
        Long champId = 1L;
        ArbreDTO arbreDTO = new ArbreDTO();
        arbreDTO.setDatePlantation(LocalDate.of(2024, 1, 15)); // Invalid month

        Champ champ = new Champ();

        when(champRepository.findById(champId)).thenReturn(Optional.of(champ));

        Exception exception = assertThrows(ValidationException.class, () ->
                arbreService.addArbreToChamp(champId, arbreDTO));

        assertTrue(exception.getMessage().contains("Trees can only be planted between March and May."));
    }

    @Test
    void testGetArbreById_Success() {
        Long arbreId = 1L;
        Arbre arbre = new Arbre();

        when(arbreRepository.findById(arbreId)).thenReturn(Optional.of(arbre));
        when(arbreMapper.arbreToResponseArbreDTO(arbre)).thenReturn(new ResponseArbreDTO());

        ResponseArbreDTO response = arbreService.getArbreById(arbreId);

        assertNotNull(response);
        verify(arbreRepository, times(1)).findById(arbreId);
    }

    @Test
    void testGetArbreById_ArbreNotFound() {
        Long arbreId = 1L;

        when(arbreRepository.findById(arbreId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () ->
                arbreService.getArbreById(arbreId));

        assertEquals("Arbre with ID 1 not found", exception.getMessage());
    }

    @Test
    void testDeleteArbre() {
        Long arbreId = 1L;

        doNothing().when(arbreRepository).deleteById(arbreId);

        String result = arbreService.deleteArbre(arbreId);

        assertEquals("deleted", result);
        verify(arbreRepository, times(1)).deleteById(arbreId);
    }
}
