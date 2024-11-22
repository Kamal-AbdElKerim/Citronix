package org.citronixx.citronix.Model.MapStruct;

import org.citronixx.citronix.Model.entites.Champ.ChampDTO;
import org.citronixx.citronix.Model.entites.Champ.Champ;
import org.citronixx.citronix.Model.entites.Champ.Response.ResponseChamp;
import org.citronixx.citronix.Model.entites.Champ.Response.ResponseChampDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {FermeMapper.class})
public interface ChampMapper {

    ChampMapper INSTANCE = Mappers.getMapper(ChampMapper.class);
    // Map from Champ entity to ChampDTO
    ChampDTO champToChampDTO(Champ champ);
    List<ChampDTO> champToChampDTO(List<Champ> champ);

    // Map from ChampDTO to Champ entity
    Champ champDTOToChamp(ChampDTO champDTO);

    // Map from Champ entity to ResponseChampDTO
    ResponseChamp champToResponseChamp(Champ champ);

  //  @Mapping(target = "arbres", ignore = true) // To avoid circular dependencies
    ResponseChampDTO champToResponseChampDTO(Champ champ);

   List<ResponseChampDTO> champToResponseChampDTO(List<Champ> champs);
    // Map from ResponseChampDTO to Champ entity
    Champ ResponseChampDTOToChamp(ResponseChampDTO ResponseChampDTO);
}

