package org.citronixx.citronix.Model.MapStruct;


import org.citronixx.citronix.Model.entites.Vente.Response.ResponseVente;
import org.citronixx.citronix.Model.entites.Vente.Response.ResponseVenteDTO;
import org.citronixx.citronix.Model.entites.Vente.Vente;
import org.citronixx.citronix.Model.entites.Vente.VenteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RecolteMapper.class})
public interface VenteMapper {

    VenteMapper INSTANCE = Mappers.getMapper(VenteMapper.class);
    // Map from Vente entity to VenteDTO
    VenteDTO venteToVenteDTO(Vente vente);

    ResponseVente venteToResponseVente(Vente vente);

    // Map from VenteDTO to Vente entity
    Vente venteDTOToVente(VenteDTO venteDTO);

    // Map from Vente entity to ResponseVenteDTO
    List<ResponseVenteDTO> venteToResponseVenteDTO(List<Vente> vente);

    ResponseVenteDTO venteToResponseVenteDTO(Vente vente);

    // Map from ResponseVenteDTO to Vente entity
    Vente ResponseVenteDTOToVente(ResponseVenteDTO ResponseVenteDTO);
}
