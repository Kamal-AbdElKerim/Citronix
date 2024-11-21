package org.citronixx.citronix.Repository;

import org.citronixx.citronix.Model.entites.Arbre.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbreRepository extends JpaRepository<Arbre, Long> {
}
