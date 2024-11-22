package org.citronixx.citronix.Service;

import jakarta.persistence.EntityNotFoundException;
import org.citronixx.citronix.Exception.ValidationException;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.citronixx.citronix.Model.entites.Recolte.RecolteDTO;
import org.citronixx.citronix.Model.entites.Vente.Response.ResponseVenteDTO;
import org.citronixx.citronix.Model.entites.Vente.Vente;
import org.citronixx.citronix.Model.entites.Vente.VenteDTO;
import org.citronixx.citronix.Repository.DetailRecolteRepository;
import org.citronixx.citronix.Repository.RecolteRepository;
import org.citronixx.citronix.Repository.VenteRepository;
import org.citronixx.citronix.Model.MapStruct.VenteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VenteServiceImplTest {

    @InjectMocks
    private VenteServiceImpl venteService;

    @Mock
    private VenteRepository venteRepository;

    @Mock
    private VenteMapper venteMapper;

    @Mock
    private RecolteRepository recolteRepository;



    private Recolte recolte;
    private VenteDTO venteDTO;
    private ResponseVenteDTO responseVenteDTO;

    @BeforeEach
    void setUp() {
        recolte = new Recolte();
        recolte.setId(1L);

        venteDTO = new VenteDTO();
        venteDTO.setPrixUnitaire(100.0);
        venteDTO.setDate(LocalDate.now());

        responseVenteDTO = new ResponseVenteDTO();
        responseVenteDTO.setRevenu(0.0);
    }



    @Test
    void saveVente_ShouldThrowValidationException_WhenVenteAlreadyExistsForRecolte() {
        Long recolteId = 1L;
        Recolte recolteWithVente = new Recolte();
        recolteWithVente.setVente(new Vente());
        Mockito.when(recolteRepository.findById(recolteId)).thenReturn(Optional.of(recolteWithVente));

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            venteService.saveVente(recolteId, venteDTO);
        });
        assertEquals("vente", exception.getField());
        assertEquals("A Vente already exists for this Recolte.", exception.getMessage());
    }

    @Test
    void getVenteById_ShouldReturnResponseVenteDTO_WhenVenteExists() {
        Long venteId = 1L;
        Vente vente = new Vente();
        Mockito.when(venteRepository.findById(venteId)).thenReturn(Optional.of(vente));
        Mockito.when(venteMapper.venteToResponseVenteDTO(vente)).thenReturn(responseVenteDTO);

        ResponseVenteDTO result = venteService.getVenteById(venteId);

        assertNotNull(result);
    }


    @Test
    void deleteVente_ShouldReturnTrue_WhenVenteExists() {
        Long venteId = 1L;
        Vente vente = new Vente();
        Mockito.when(venteRepository.findById(venteId)).thenReturn(Optional.of(vente));
        Mockito.when(venteRepository.existsById(venteId)).thenReturn(true);

        boolean result = venteService.deleteVente(venteId);

        assertTrue(result);
        Mockito.verify(venteRepository, Mockito.times(1)).delete(vente);
    }


}
