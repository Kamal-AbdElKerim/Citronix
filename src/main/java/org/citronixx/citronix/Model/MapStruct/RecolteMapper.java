package org.citronixx.citronix.Model.MapStruct;

import org.citronixx.citronix.Model.DTO.RecolteDTO;
import org.citronixx.citronix.Model.Entity.Recolte;
import org.citronixx.citronix.Model.ViewModel.RecolteViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecolteMapper {

    RecolteMapper INSTANCE = Mappers.getMapper(RecolteMapper.class);

    // Map from Recolte entity to RecolteDTO
    RecolteDTO recolteToRecolteDTO(Recolte recolte);

    // Map from RecolteDTO to Recolte entity
    Recolte recolteDTOToRecolte(RecolteDTO recolteDTO);

    // Map from Recolte entity to RecolteViewModel
    RecolteViewModel recolteToRecolteViewModel(Recolte recolte);

    // Map from RecolteViewModel to Recolte entity
    Recolte recolteViewModelToRecolte(RecolteViewModel recolteViewModel);
}
