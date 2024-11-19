package org.citronixx.citronix.Model.MapStruct;

import org.citronixx.citronix.Model.DTO.ArbreDTO;
import org.citronixx.citronix.Model.Entity.Arbre;
import org.citronixx.citronix.Model.ViewModel.ArbreViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArbreMapper {

    ArbreMapper INSTANCE = Mappers.getMapper(ArbreMapper.class);

    ArbreDTO arbreToArbreDTO(Arbre arbre);

    // Map from ArbreDTO to Arbre entity
    Arbre arbreDTOToArbre(ArbreDTO arbreDTO);

    // Map from Arbre entity to ArbreViewModel
    ArbreViewModel arbreToArbreViewModel(Arbre arbre);

    // Map from ArbreViewModel to Arbre entity
    Arbre arbreViewModelToArbre(ArbreViewModel arbreViewModel);
}
