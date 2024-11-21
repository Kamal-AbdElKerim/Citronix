package org.citronixx.citronix.Service.Interface;


import org.citronixx.citronix.Model.entites.Arbre.Arbre;
import org.citronixx.citronix.Model.entites.Arbre.ArbreDTO;
import org.citronixx.citronix.Model.entites.Arbre.Response.ResponseArbreDTO;

import java.util.List;
import java.util.Optional;

public interface ArbreService {

    ResponseArbreDTO addArbreToChamp(Long champId ,ArbreDTO arbreDTO);

    List<ResponseArbreDTO> getAllArbres();

    ResponseArbreDTO getArbreById(Long id);

    void deleteArbre(Long id);

      ResponseArbreDTO updateArbre(Long arbreId, ArbreDTO arbreDTO);
//
//    List<Arbre> findByAgeGreaterThan(int age);
}

