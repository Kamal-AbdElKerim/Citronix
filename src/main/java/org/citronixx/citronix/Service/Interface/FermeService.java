package org.citronixx.citronix.Service.Interface;

import org.citronixx.citronix.Model.Entity.Ferme;

import java.util.List;
import java.util.Optional;

public interface FermeService {

    Ferme saveFerme(Ferme ferme);

    List<Ferme> getAllFermes();

    Optional<Ferme> getFermeById(Long id);

    void deleteFerme(Long id);

   // List<Ferme> findByNom(String nom);
}
