package org.citronixx.citronix.Model.DTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.citronixx.citronix.Model.Entity.Arbre;
import org.citronixx.citronix.Model.Entity.Ferme;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ChampDTO {
    private Long id;

    @NotBlank(message = "Le nom du champ est obligatoire.")
    @Size(max = 100, message = "Le nom du champ ne peut pas dépasser 100 caractères.")
    private String nom;


    private double superficie;

    private FermeDTO ferme;

    private List<ArbreDTO> arbres;
}
