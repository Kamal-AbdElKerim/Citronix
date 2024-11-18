package org.citronixx.citronix.Model.MapStruct;


import org.citronixx.citronix.Model.DTO.FermeDTO;
import org.citronixx.citronix.Model.Entity.Ferme;
import org.citronixx.citronix.Model.ViewModel.FermeViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FermeMapper {


    // Map from Ferme entity to FermeDTO
    FermeDTO fermeToFermeDTO(Ferme ferme);

    // Map from FermeDTO to Ferme entity
    Ferme fermeDTOToFerme(FermeDTO fermeDTO);

    // Map from Ferme entity to FermeViewModel
    FermeViewModel fermeToFermeViewModel(Ferme ferme);

    // Map from FermeViewModel to Ferme entity
    Ferme fermeViewModelToFerme(FermeViewModel fermeViewModel);
}
