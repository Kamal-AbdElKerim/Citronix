package org.citronixx.citronix.Service.Interface;

import org.citronixx.citronix.Model.Entity.Arbre;
import org.citronixx.citronix.Model.Entity.Champ;

import java.util.List;
import java.util.Optional;

public interface ArbreService {

    Arbre saveArbre(Arbre arbre);

    List<Arbre> getAllArbres();

    Optional<Arbre> getArbreById(Long id);

    void deleteArbre(Long id);
//
//    List<Arbre> findByChamp(Champ champ);
//
//    List<Arbre> findByAgeGreaterThan(int age);
}

