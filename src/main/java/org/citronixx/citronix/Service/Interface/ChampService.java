package org.citronixx.citronix.Service.Interface;

import org.citronixx.citronix.Model.entites.Champ.ChampDTO;
import org.citronixx.citronix.Model.entites.Champ.Response.ResponseChampDTO;

import java.util.List;

public interface ChampService {

    ResponseChampDTO addChampToFerme(Long fermeId, ChampDTO champDTO);

    List<ResponseChampDTO> getAllChamps();

    ResponseChampDTO getChampById(Long id);

    String deleteChamp(Long id);

    ResponseChampDTO updateChamp (Long champId, ChampDTO champDTO);

   // List<Champ> findByFerme(Ferme ferme);
}
