package org.citronixx.citronix.Service.Interface;

import org.citronixx.citronix.Model.Entity.Arbre;
import org.citronixx.citronix.Model.Entity.DetailRecolte;

import java.util.List;
import java.util.Optional;

public interface DetailRecolteService {

    DetailRecolte saveDetailRecolte(DetailRecolte detailRecolte);

    List<DetailRecolte> getAllDetailRecoltes();

    Optional<DetailRecolte> getDetailRecolteById(Long id);

    void deleteDetailRecolte(Long id);

   // List<DetailRecolte> findByArbre(Arbre arbre);
}
