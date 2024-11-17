package org.citronixx.citronix.Service.Interface;

import org.citronixx.citronix.Model.Entity.Recolte;

import java.util.List;
import java.util.Optional;

public interface RecolteService {

    Recolte saveRecolte(Recolte recolte);

    List<Recolte> getAllRecoltes();

    Optional<Recolte> getRecolteById(Long id);

    void deleteRecolte(Long id);

}
