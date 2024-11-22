package org.citronixx.citronix.Service;

import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Exception.ValidationException;
import org.citronixx.citronix.Model.MapStruct.DetailRecolteMapper;
import org.citronixx.citronix.Model.entites.Arbre.Arbre;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolteDTO;
import org.citronixx.citronix.Model.entites.DetailRecolte.Response.ResponseDetailRecolteDTO;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.citronixx.citronix.Repository.ArbreRepository;
import org.citronixx.citronix.Repository.DetailRecolteRepository;
import org.citronixx.citronix.Repository.RecolteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DetailRecolteServiceImplTest {

    @Mock
    private DetailRecolteRepository detailRecolteRepository;

    @Mock
    private DetailRecolteMapper detailRecolteMapper;

    @Mock
    private ArbreRepository arbreRepository;

    @Mock
    private RecolteRepository recolteRepository;

    @InjectMocks
    private DetailRecolteServiceImpl detailRecolteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDetailRecolteToArbre_Success() {
        Long recolteID = 1L;
        Long arbreID = 2L;

        DetailRecolteDTO detailRecolteDTO = new DetailRecolteDTO();
        detailRecolteDTO.setQuantiteParArbre(10.0);

        Arbre arbre = mock(Arbre.class);
        Recolte recolte = new Recolte();
        DetailRecolte detailRecolte = new DetailRecolte();

        when(arbreRepository.findById(arbreID)).thenReturn(Optional.of(arbre));
        when(recolteRepository.findById(recolteID)).thenReturn(Optional.of(recolte));
        when(detailRecolteRepository.existsByRecolteIdAndArbreId(recolteID, arbreID)).thenReturn(false);
        when(arbre.calculateProductivity()).thenReturn(15.0);
        when(detailRecolteMapper.detailRecolteDTOToDetailRecolte(detailRecolteDTO)).thenReturn(detailRecolte);
        when(detailRecolteRepository.save(detailRecolte)).thenReturn(detailRecolte);
        when(detailRecolteMapper.detailRecolteToResponseDetailRecolteDTO(detailRecolte)).thenReturn(new ResponseDetailRecolteDTO());

        ResponseDetailRecolteDTO response = detailRecolteService.addDetailRecolteToArbre(recolteID, arbreID, detailRecolteDTO);

        assertNotNull(response);
        verify(detailRecolteRepository).save(detailRecolte);
    }

    @Test
    void testAddDetailRecolteToArbre_QuantityExceedsProductivity() {
        Long recolteID = 1L;
        Long arbreID = 2L;

        DetailRecolteDTO detailRecolteDTO = new DetailRecolteDTO();
        detailRecolteDTO.setQuantiteParArbre(20.0);

        Arbre arbre = mock(Arbre.class);
        when(arbre.calculateProductivity()).thenReturn(15.0);

        when(arbreRepository.findById(arbreID)).thenReturn(Optional.of(arbre));
        when(recolteRepository.findById(recolteID)).thenReturn(Optional.of(new Recolte()));

        ValidationException exception = assertThrows(ValidationException.class, () ->
                detailRecolteService.addDetailRecolteToArbre(recolteID, arbreID, detailRecolteDTO));

        assertTrue(exception.getMessage().contains("The quantity per tree (20.0 kg) exceeds the maximum productivity"));
    }



    @Test
    void testUpdateDetailRecolte_Success() {
        Long detailRecolteId = 1L;

        DetailRecolte existingDetailRecolte = new DetailRecolte();
        Arbre arbre = mock(Arbre.class);
        when(arbre.calculateProductivity()).thenReturn(20.0);
        existingDetailRecolte.setArbre(arbre);

        DetailRecolteDTO detailRecolteDTO = new DetailRecolteDTO();
        detailRecolteDTO.setQuantiteParArbre(15.0);

        when(detailRecolteRepository.findById(detailRecolteId)).thenReturn(Optional.of(existingDetailRecolte));
        when(detailRecolteRepository.save(existingDetailRecolte)).thenReturn(existingDetailRecolte);
        when(detailRecolteMapper.detailRecolteToResponseDetailRecolteDTO(existingDetailRecolte)).thenReturn(new ResponseDetailRecolteDTO());

        ResponseDetailRecolteDTO response = detailRecolteService.updateDetailRecolte(detailRecolteId, detailRecolteDTO);

        assertNotNull(response);
        verify(detailRecolteRepository).save(existingDetailRecolte);
    }

    @Test
    void testUpdateDetailRecolte_QuantityExceedsProductivity() {
        Long detailRecolteId = 1L;

        DetailRecolte existingDetailRecolte = new DetailRecolte();
        Arbre arbre = mock(Arbre.class);
        when(arbre.calculateProductivity()).thenReturn(15.0);
        existingDetailRecolte.setArbre(arbre);

        DetailRecolteDTO detailRecolteDTO = new DetailRecolteDTO();
        detailRecolteDTO.setQuantiteParArbre(20.0);

        when(detailRecolteRepository.findById(detailRecolteId)).thenReturn(Optional.of(existingDetailRecolte));

        ValidationException exception = assertThrows(ValidationException.class, () ->
                detailRecolteService.updateDetailRecolte(detailRecolteId, detailRecolteDTO));

        assertTrue(exception.getMessage().contains("The quantity per tree (20.0 kg) exceeds the maximum productivity"));
    }
}
