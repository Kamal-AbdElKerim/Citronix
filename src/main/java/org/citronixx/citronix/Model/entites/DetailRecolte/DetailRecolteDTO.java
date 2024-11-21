package org.citronixx.citronix.Model.entites.DetailRecolte;

import lombok.*;
import org.citronixx.citronix.Model.entites.Arbre.ArbreDTO;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailRecolteDTO {
    private Long id;


    private double quantiteParArbre;

}
