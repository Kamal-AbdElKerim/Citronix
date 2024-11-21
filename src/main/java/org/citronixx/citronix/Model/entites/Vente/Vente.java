package org.citronixx.citronix.Model.entites.Vente;

import jakarta.persistence.*;
import lombok.*;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;

import java.time.LocalDate;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private double prixUnitaire;
    private String client;
    private double revenu;
    @ManyToOne
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;


}

