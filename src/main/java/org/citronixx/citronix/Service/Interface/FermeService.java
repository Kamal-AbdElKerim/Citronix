package org.citronixx.citronix.Service.Interface;

import org.citronixx.citronix.Model.entites.Ferme.FermeDTO;
import org.citronixx.citronix.Model.entites.Ferme.Response.ResponseFermeDTO;

import java.util.List;

public interface FermeService {

    ResponseFermeDTO saveFerme(FermeDTO fermeDTO);

    List<ResponseFermeDTO> getAllFermes();

    ResponseFermeDTO getFermeById(Long id);

    boolean deleteFerme(Long id);

   // List<Ferme> findByNom(String nom);

    ResponseFermeDTO updateFerme(Long id, FermeDTO fermeDTO);
}
