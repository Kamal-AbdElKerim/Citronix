package org.citronixx.citronix.Service.Interface;


import org.citronixx.citronix.Model.entites.Vente.Response.ResponseVenteDTO;
import org.citronixx.citronix.Model.entites.Vente.Vente;
import org.citronixx.citronix.Model.entites.Vente.VenteDTO;

import java.util.List;
import java.util.Optional;

public interface VenteService {

    ResponseVenteDTO saveVente(Long recolteId ,VenteDTO venteDTO);

    List<ResponseVenteDTO> getAllVentes();


    ResponseVenteDTO getVenteById(Long id);


    boolean deleteVente(Long id);


    ResponseVenteDTO updateVente(Long id, VenteDTO venteDTO);

}
