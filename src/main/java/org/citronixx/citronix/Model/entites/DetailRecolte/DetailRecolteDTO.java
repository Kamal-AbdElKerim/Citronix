package org.citronixx.citronix.Model.entites.DetailRecolte;

import lombok.*;
import org.citronixx.citronix.Model.entites.Arbre.ArbreDTO;
import org.citronixx.citronix.Model.entites.Arbre.Response.ResponseArbre;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolte;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailRecolteDTO {
    private Long id;


    private double quantiteParArbre;

    private ArbreDTO arbre;

  //  private ResponseRecolte recolte;
}
