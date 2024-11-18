package org.citronixx.citronix.Service.Interface;

import org.citronixx.citronix.Model.DTO.FermeDTO;
import org.citronixx.citronix.Model.Entity.Ferme;
import org.citronixx.citronix.Model.ViewModel.FermeViewModel;

import java.util.List;
import java.util.Optional;

public interface FermeService {

    FermeViewModel saveFerme(FermeDTO fermeDTO);

    List<FermeViewModel> getAllFermes();

    FermeViewModel getFermeById(Long id);

    boolean deleteFerme(Long id);

   // List<Ferme> findByNom(String nom);

    FermeViewModel updateFerme(Long id, FermeDTO fermeDTO);
}
