package org.citronixx.citronix.Model.MapStruct;


import org.citronixx.citronix.Model.entites.Ferme.FermeDTO;
import org.citronixx.citronix.Model.entites.Ferme.Ferme;
import org.citronixx.citronix.Model.entites.Ferme.Response.ResponseFermeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface FermeMapper {

    FermeMapper INSTANCE = Mappers.getMapper(FermeMapper.class);

    // Map from Ferme entity to FermeDTO
    FermeDTO fermeToFermeDTO(Ferme ferme);

    // Map from FermeDTO to Ferme entity
    Ferme fermeDTOToFerme(FermeDTO fermeDTO);

    // Map from Ferme entity to ResponseFermeDTO


    ResponseFermeDTO fermeToResponseFermeDTO(Ferme ferme);

    // Map from ResponseFermeDTO to Ferme entity
    Ferme ResponseFermeDTOToFerme(ResponseFermeDTO ResponseFermeDTO);

    List<ResponseFermeDTO> fermeToResponseFermeDTO(List<Ferme> fermes);

    // Optionally, map a List<ResponseFermeDTO> to List<Ferme> (reverse mapping)
    List<Ferme> ResponseFermeDTOToFerme(List<ResponseFermeDTO> ResponseFermeDTOs);
}
