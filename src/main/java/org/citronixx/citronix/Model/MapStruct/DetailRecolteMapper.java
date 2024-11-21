package org.citronixx.citronix.Model.MapStruct;



import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolteDTO;
import org.citronixx.citronix.Model.entites.DetailRecolte.Response.ResponseDetailRecolteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetailRecolteMapper {
    DetailRecolteMapper INSTANCE = Mappers.getMapper(DetailRecolteMapper.class);

    // Map from DetailRecolte entity to DetailRecolteDTO
    DetailRecolteDTO detailRecolteToDetailRecolteDTO(DetailRecolte detailRecolte);

    // Map from DetailRecolteDTO to DetailRecolte entity
    DetailRecolte detailRecolteDTOToDetailRecolte(DetailRecolteDTO detailRecolteDTO);

    // Map from DetailRecolte entity to ResponseDetailRecolteDTO
    ResponseDetailRecolteDTO detailRecolteToResponseDetailRecolteDTO(DetailRecolte detailRecolte);

    List<ResponseDetailRecolteDTO> detailRecolteToResponseDetailRecolteDTO(List<DetailRecolte> detailRecoltes);

    // Map from ResponseDetailRecolteDTO to DetailRecolte entity
    DetailRecolte ResponseDetailRecolteDTOToDetailRecolte(ResponseDetailRecolteDTO ResponseDetailRecolteDTO);
}
