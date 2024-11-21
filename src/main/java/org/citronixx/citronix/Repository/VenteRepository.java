package org.citronixx.citronix.Repository;

import org.citronixx.citronix.Model.entites.Vente.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
}
