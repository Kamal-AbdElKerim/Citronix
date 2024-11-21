package org.citronixx.citronix.Repository;

import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRecolteRepository extends JpaRepository<DetailRecolte, Long> {

}
