package org.citronixx.citronix.Model.entites.Champ;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.citronixx.citronix.Model.entites.Arbre.ArbreDTO;
import org.citronixx.citronix.Model.entites.Arbre.Response.ResponseArbre;
import org.citronixx.citronix.Model.entites.Ferme.FermeDTO;

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

   private List<ResponseArbre> arbres;
}
