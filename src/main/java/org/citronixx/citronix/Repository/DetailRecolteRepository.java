package org.citronixx.citronix.Repository;

import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRecolteRepository extends JpaRepository<DetailRecolte, Long> {
    List<DetailRecolte> findByRecolteId(Long recolteId);
    List<DetailRecolte> findByArbreId(Long ArbreId);
    @Query("SELECT COUNT(dr) > 0 FROM DetailRecolte dr WHERE dr.recolte.id = :recolteId AND dr.arbre.id = :arbreId")
    boolean existsByRecolteIdAndArbreId(@Param("recolteId") Long recolteId, @Param("arbreId") Long arbreId);


}
