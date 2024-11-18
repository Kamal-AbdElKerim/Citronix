package org.citronixx.citronix.Model.MapStruct;

import org.citronixx.citronix.Model.DTO.VenteDTO;
import org.citronixx.citronix.Model.Entity.Vente;
import org.citronixx.citronix.Model.ViewModel.VenteViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VenteMapper {


    // Map from Vente entity to VenteDTO
    VenteDTO venteToVenteDTO(Vente vente);

    // Map from VenteDTO to Vente entity
    Vente venteDTOToVente(VenteDTO venteDTO);

    // Map from Vente entity to VenteViewModel
    VenteViewModel venteToVenteViewModel(Vente vente);

    // Map from VenteViewModel to Vente entity
    Vente venteViewModelToVente(VenteViewModel venteViewModel);
}
