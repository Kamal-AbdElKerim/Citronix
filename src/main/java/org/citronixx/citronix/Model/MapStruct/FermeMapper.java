package org.citronixx.citronix.Model.MapStruct;


import org.citronixx.citronix.Model.DTO.FermeDTO;
import org.citronixx.citronix.Model.Entity.Ferme;
import org.citronixx.citronix.Model.ViewModel.FermeViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {ChampMapper.class})
public interface FermeMapper {

    FermeMapper INSTANCE = Mappers.getMapper(FermeMapper.class);

    // Map from Ferme entity to FermeDTO
    FermeDTO fermeToFermeDTO(Ferme ferme);

    // Map from FermeDTO to Ferme entity
    Ferme fermeDTOToFerme(FermeDTO fermeDTO);

    // Map from Ferme entity to FermeViewModel


    FermeViewModel fermeToFermeViewModel(Ferme ferme);

    // Map from FermeViewModel to Ferme entity
    Ferme fermeViewModelToFerme(FermeViewModel fermeViewModel);

    List<FermeViewModel> fermeToFermeViewModel(List<Ferme> fermes);

    // Optionally, map a List<FermeViewModel> to List<Ferme> (reverse mapping)
    List<Ferme> fermeViewModelToFerme(List<FermeViewModel> fermeViewModels);
}
