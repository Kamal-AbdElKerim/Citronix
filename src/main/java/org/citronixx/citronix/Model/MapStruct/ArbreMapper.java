package org.citronixx.citronix.Model.MapStruct;


import org.citronixx.citronix.Model.entites.Arbre.Arbre;
import org.citronixx.citronix.Model.entites.Arbre.ArbreDTO;
import org.citronixx.citronix.Model.entites.Arbre.Response.ResponseArbreDTO;
import org.citronixx.citronix.Model.entites.Champ.Response.ResponseChamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ChampMapper.class})
public interface ArbreMapper {

    ArbreMapper INSTANCE = Mappers.getMapper(ArbreMapper.class);

    ArbreDTO arbreToArbreDTO(Arbre arbre);

    // Map from ArbreDTO to Arbre entity
    Arbre arbreDTOToArbre(ArbreDTO arbreDTO);

    // Map from Arbre entity to ResponseArbreDTO


    @Mapping(target = "datePlantation", source = "datePlantation")
    @Mapping(target = "champ", source = "champ") // Uses ChampMapper implicitly
    @Mapping(target = "age", expression = "java(arbre.calculateAge())")
    @Mapping(target = "productivity", expression = "java(arbre.calculateProductivity())")
    ResponseArbreDTO arbreToResponseArbreDTO(Arbre arbre);
    List<ResponseArbreDTO> arbreToResponseArbreDTO(List<Arbre> arbres);
    // Map from ResponseArbreDTO to Arbre entity
    Arbre ResponseArbreDTOToArbre(ResponseArbreDTO ResponseArbreDTO);
}
