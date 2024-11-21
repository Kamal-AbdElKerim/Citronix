package org.citronixx.citronix.Model.MapStruct;


import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.citronixx.citronix.Model.entites.Recolte.RecolteDTO;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecolteMapper {

    RecolteMapper INSTANCE = Mappers.getMapper(RecolteMapper.class);

    // Map from Recolte entity to RecolteDTO
    RecolteDTO recolteToRecolteDTO(Recolte recolte);

    // Map from RecolteDTO to Recolte entity
    Recolte recolteDTOToRecolte(RecolteDTO recolteDTO);

    // Map from Recolte entity to ResponseRecolteDTO
    @Mappings({
            @Mapping(target = "quantiteTotale", expression = "java(calculateQuantiteTotale(recolte))")
    })
    List<ResponseRecolteDTO> recolteToResponseRecolteDTO(List<Recolte> recolte);

    // Map from Recolte entity to ResponseRecolteDTO (single)
    @Mapping(target = "quantiteTotale", expression = "java(calculateQuantiteTotale(recolte))")
    ResponseRecolteDTO recolteToResponseRecolteDTO(Recolte recolte);

    // Map from ResponseRecolteDTO to Recolte entity
    Recolte ResponseRecolteDTOToRecolte(ResponseRecolteDTO ResponseRecolteDTO);

    // Helper method to calculate quantiteTotale for Recolte
    default double calculateQuantiteTotale(Recolte recolte) {
        return (recolte.getDetailsRecolte() != null ? recolte.getDetailsRecolte().stream()
                .mapToDouble(DetailRecolte::getQuantiteParArbre)
                .sum() : 0);
    }
}