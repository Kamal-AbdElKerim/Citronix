package org.citronixx.citronix.Repository;

import org.citronixx.citronix.Model.Enum.Saison;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {
    @Query("SELECT COUNT(r) > 0 FROM Recolte r WHERE r.ferme.id = :fermeId AND r.saison = :saison AND FUNCTION('YEAR', r.dateRecolte) = :year")
    boolean existsByFermeIdAndSaisonAndYear(@Param("fermeId") Long fermeId, @Param("saison") Saison saison, @Param("year") int year);

}
