package org.citronixx.citronix.Repository;

import org.citronixx.citronix.Model.Entity.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, Long> {
}
