package org.citronixx.citronix.Service.Interface;


import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.citronixx.citronix.Model.entites.Recolte.RecolteDTO;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolteDTO;

import java.util.List;
import java.util.Optional;

public interface RecolteService {

    ResponseRecolteDTO addRecolte(RecolteDTO recolteDTO);

    ResponseRecolteDTO getRecolteById(Long recolteId);

    List<ResponseRecolteDTO> getAllRecoltes();

    ResponseRecolteDTO updateRecolte(Long recolteId, RecolteDTO recolteDTO);

    String deleteRecolte(Long recolteId);

}
