package org.citronixx.citronix.Repository;

import org.citronixx.citronix.Model.entites.Champ.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampRepository extends JpaRepository<Champ, Long> {
}
