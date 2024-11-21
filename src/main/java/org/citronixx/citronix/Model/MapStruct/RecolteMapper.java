package org.citronixx.citronix.Model.MapStruct;


import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.citronixx.citronix.Model.entites.Recolte.RecolteDTO;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecolteMapper {

    RecolteMapper INSTANCE = Mappers.getMapper(RecolteMapper.class);

    // Map from Recolte entity to RecolteDTO
    RecolteDTO recolteToRecolteDTO(Recolte recolte);

    // Map from RecolteDTO to Recolte entity
    Recolte recolteDTOToRecolte(RecolteDTO recolteDTO);

    // Map from Recolte entity to ResponseRecolteDTO
    ResponseRecolteDTO recolteToResponseRecolteDTO(Recolte recolte);

    // Map from ResponseRecolteDTO to Recolte entity
    Recolte ResponseRecolteDTOToRecolte(ResponseRecolteDTO ResponseRecolteDTO);
}