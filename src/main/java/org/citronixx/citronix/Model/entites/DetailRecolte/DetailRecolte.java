package org.citronixx.citronix.Model.entites.DetailRecolte;

import jakarta.persistence.*;
import lombok.*;
import org.citronixx.citronix.Model.entites.Arbre.Arbre;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class  DetailRecolte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantiteParArbre; // en kilogrammes

    @ManyToOne
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;

    @ManyToOne
    @JoinColumn(name = "arbre_id", nullable = false)
    private Arbre arbre;
}

