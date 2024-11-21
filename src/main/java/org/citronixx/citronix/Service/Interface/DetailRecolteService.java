package org.citronixx.citronix.Service.Interface;


import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolteDTO;
import org.citronixx.citronix.Model.entites.DetailRecolte.Response.ResponseDetailRecolteDTO;

import java.util.List;
import java.util.Optional;

public interface DetailRecolteService {


    ResponseDetailRecolteDTO addDetailRecolteToArbre(Long recolteID, Long arbreId, DetailRecolteDTO detailRecolteDTO);

    List<ResponseDetailRecolteDTO> getAllDetailRecoltes();

    ResponseDetailRecolteDTO getDetailRecolteById(Long id);

    String deleteDetailRecolte(Long id);

    ResponseDetailRecolteDTO updateDetailRecolte(Long detailRecolteId, DetailRecolteDTO detailRecolteDTO);
}