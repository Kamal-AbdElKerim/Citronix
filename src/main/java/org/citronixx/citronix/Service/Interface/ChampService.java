package org.citronixx.citronix.Service.Interface;

import org.citronixx.citronix.Model.DTO.ChampDTO;
import org.citronixx.citronix.Model.Entity.Champ;
import org.citronixx.citronix.Model.Entity.Ferme;
import org.citronixx.citronix.Model.ViewModel.ChampViewModel;

import java.util.List;
import java.util.Optional;

public interface ChampService {

    ChampViewModel addChampToFerme(Long fermeId, ChampDTO champDTO);

    List<ChampViewModel> getAllChamps();

    Optional<ChampViewModel> getChampById(Long id);

    String deleteChamp(Long id);

    ChampViewModel updateChamp (Long champId, ChampDTO champDTO);

   // List<Champ> findByFerme(Ferme ferme);
}
