package org.citronixx.citronix.Model.entites.Recolte;

import jakarta.persistence.*;
import lombok.*;
import org.citronixx.citronix.Model.Enum.Saison;
import org.citronixx.citronix.Model.entites.Champ.Champ;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.citronixx.citronix.Model.entites.Ferme.Ferme;
import org.citronixx.citronix.Model.entites.Vente.Vente;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recolte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Saison saison;

    private LocalDate dateRecolte;

    private double quantiteTotale;



    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailRecolte> detailsRecolte;

    @OneToOne(mappedBy = "recolte", cascade = CascadeType.ALL, orphanRemoval = true)
    private Vente vente;


    @ManyToOne
    @JoinColumn(name = "Champ_id", nullable = false)
    private Champ champ;

}
