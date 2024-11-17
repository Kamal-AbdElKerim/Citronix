package org.citronixx.citronix.Service.Interface;

import org.citronixx.citronix.Model.Entity.Champ;
import org.citronixx.citronix.Model.Entity.Ferme;

import java.util.List;
import java.util.Optional;

public interface ChampService {

    Champ saveChamp(Champ champ);

    List<Champ> getAllChamps();

    Optional<Champ> getChampById(Long id);

    void deleteChamp(Long id);

   // List<Champ> findByFerme(Ferme ferme);
}
