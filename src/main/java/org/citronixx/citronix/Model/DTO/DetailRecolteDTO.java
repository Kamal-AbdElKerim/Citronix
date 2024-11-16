package org.citronixx.citronix.Model.DTO;

import lombok.*;
import org.citronixx.citronix.Model.Entity.Arbre;
import org.citronixx.citronix.Model.Entity.Recolte;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailRecolteDTO {
    private Long id;
    private double quantiteParArbre;
    private RecolteDTO recolte;
    private ArbreDTO arbre;
}
