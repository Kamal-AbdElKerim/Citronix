package org.citronixx.citronix.Model.MapStruct;

import org.citronixx.citronix.Model.DTO.ChampDTO;
import org.citronixx.citronix.Model.Entity.Champ;
import org.citronixx.citronix.Model.ViewModel.ChampViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChampMapper {


    // Map from Champ entity to ChampDTO
    ChampDTO champToChampDTO(Champ champ);

    // Map from ChampDTO to Champ entity
    Champ champDTOToChamp(ChampDTO champDTO);

    // Map from Champ entity to ChampViewModel
    ChampViewModel champToChampViewModel(Champ champ);

    // Map from ChampViewModel to Champ entity
    Champ champViewModelToChamp(ChampViewModel champViewModel);
}

