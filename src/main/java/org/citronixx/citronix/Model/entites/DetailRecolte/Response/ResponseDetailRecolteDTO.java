package org.citronixx.citronix.Model.entites.DetailRecolte.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.citronixx.citronix.Model.entites.Arbre.ArbreDTO;
import org.citronixx.citronix.Model.entites.Arbre.Response.ResponseArbre;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolte;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDetailRecolteDTO {
    private Long id;
    private double quantiteParArbre;
  //  private RecolteDTO recolte;
    private ResponseArbre arbre;

    private ResponseRecolte recolte;
}
