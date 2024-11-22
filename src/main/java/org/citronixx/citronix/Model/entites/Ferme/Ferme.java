package org.citronixx.citronix.Model.entites.Ferme;

import jakarta.persistence.*;
import lombok.*;
import org.citronixx.citronix.Model.entites.Champ.Champ;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

public class Ferme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String localisation;
    private double superficie;
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Champ> champs;

    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recolte> Recoltes;
}



