package org.citronixx.citronix.Service;

import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Exception.ValidationException;
import org.citronixx.citronix.Model.Enum.Saison;
import org.citronixx.citronix.Model.MapStruct.RecolteMapper;
import org.citronixx.citronix.Model.entites.Ferme.Ferme;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.citronixx.citronix.Model.entites.Recolte.RecolteDTO;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolteDTO;
import org.citronixx.citronix.Repository.FermeRepository;
import org.citronixx.citronix.Repository.RecolteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RecolteServiceImplTest {

    @Mock
    private RecolteRepository recolteRepository;

    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private RecolteMapper recolteMapper;

    @InjectMocks
    private RecolteServiceImpl recolteService;

    private Ferme ferme;
    private RecolteDTO recolteDTO;

    @BeforeEach
    void setUp() {
        ferme = new Ferme();
        ferme.setId(1L);

        recolteDTO = new RecolteDTO();
        recolteDTO.setFerme(ferme);
        recolteDTO.setSaison(Saison.ETE);  // Use valid Saison enum
        recolteDTO.setDateRecolte(LocalDate.of(2024, 6, 15));
        recolteDTO.setQuantiteTotale(150.0);
    }

    @Test
    void testAddRecolte_Success() {
        when(fermeRepository.findById(1L)).thenReturn(Optional.of(ferme));

        Recolte recolte = new Recolte();
        when(recolteMapper.recolteDTOToRecolte(recolteDTO)).thenReturn(recolte);
        ResponseRecolteDTO responseRecolteDTO = new ResponseRecolteDTO();
        when(recolteMapper.recolteToResponseRecolteDTO(recolte)).thenReturn(responseRecolteDTO);

        when(recolteRepository.existsByFermeIdAndSaisonAndYear(
                anyLong(), eq(Saison.ETE), eq(2024)
        )).thenReturn(false);
        when(recolteRepository.save(recolte)).thenReturn(recolte);

        ResponseRecolteDTO result = recolteService.addRecolte(1L, recolteDTO);

        verify(recolteRepository).save(recolte);
        assertNotNull(result);
    }

    @Test
    void testAddRecolte_FermeNotFound() {
        when(fermeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            recolteService.addRecolte(1L, recolteDTO);
        });
    }

    @Test
    void testAddRecolte_ValidationException() {
        when(fermeRepository.findById(1L)).thenReturn(Optional.of(ferme));

        when(recolteRepository.existsByFermeIdAndSaisonAndYear(
                anyLong(), eq(Saison.ETE), eq(2024)
        )).thenReturn(true);

        assertThrows(ValidationException.class, () -> {
            recolteService.addRecolte(1L, recolteDTO);
        });
    }

    @Test
    void testGetRecolteById() {
        Recolte recolte = new Recolte();
        recolte.setId(1L);
        when(recolteRepository.findById(1L)).thenReturn(Optional.of(recolte));
        ResponseRecolteDTO responseRecolteDTO = new ResponseRecolteDTO();
        when(recolteMapper.recolteToResponseRecolteDTO(recolte)).thenReturn(responseRecolteDTO);

        ResponseRecolteDTO result = recolteService.getRecolteById(1L);

        assertNotNull(result);
    }


    @Test
    void testDeleteRecolte() {

        when(recolteRepository.existsById(1L)).thenReturn(true);

        String result = recolteService.deleteRecolte(1L);

        verify(recolteRepository).deleteById(1L);
        assertEquals("Recolte deleted successfully!", result);
    }

    @Test
    void testDeleteRecolte_NotFound() {
        when(recolteRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            recolteService.deleteRecolte(1L);
        });
    }
}
