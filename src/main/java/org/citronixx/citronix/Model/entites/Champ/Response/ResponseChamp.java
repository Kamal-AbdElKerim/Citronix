package org.citronixx.citronix.Model.entites.Champ.Response;

import lombok.*;
import org.citronixx.citronix.Model.entites.Arbre.Response.ResponseArbreDTO;
import org.citronixx.citronix.Model.entites.Ferme.FermeDTO;
import org.citronixx.citronix.Model.entites.Ferme.Response.ResponseFerme;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseChamp {
    private Long id;


    private String nom;


    private double superficie;

    private ResponseFerme ferme;

  //  private List<ResponseArbreDTO> arbres;
}
