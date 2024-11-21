package org.citronixx.citronix.Repository;

import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {
}
