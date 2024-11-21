package org.citronixx.citronix.Model.entites.Vente;

import jakarta.persistence.*;
import lombok.*;
import org.citronixx.citronix.Model.Entity.Recolte.Recolte;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private double prixUnitaire;
    private String client;

    @ManyToOne
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;


}

