package org.citronixx.citronix.Model.MapStruct;


import org.citronixx.citronix.Model.DTO.DetailRecolteDTO;
import org.citronixx.citronix.Model.Entity.DetailRecolte;
import org.citronixx.citronix.Model.ViewModel.DetailRecolteViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DetailRecolteMapper {
    DetailRecolteMapper INSTANCE = Mappers.getMapper(DetailRecolteMapper.class);

    // Map from DetailRecolte entity to DetailRecolteDTO
    DetailRecolteDTO detailRecolteToDetailRecolteDTO(DetailRecolte detailRecolte);

    // Map from DetailRecolteDTO to DetailRecolte entity
    DetailRecolte detailRecolteDTOToDetailRecolte(DetailRecolteDTO detailRecolteDTO);

    // Map from DetailRecolte entity to DetailRecolteViewModel
    DetailRecolteViewModel detailRecolteToDetailRecolteViewModel(DetailRecolte detailRecolte);

    // Map from DetailRecolteViewModel to DetailRecolte entity
    DetailRecolte detailRecolteViewModelToDetailRecolte(DetailRecolteViewModel detailRecolteViewModel);
}
