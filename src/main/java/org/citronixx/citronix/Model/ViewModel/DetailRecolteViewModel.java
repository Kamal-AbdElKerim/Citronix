package org.citronixx.citronix.Model.ViewModel;

import lombok.*;
import org.citronixx.citronix.Model.Entity.Arbre;
import org.citronixx.citronix.Model.Entity.Recolte;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailRecolteViewModel {
    private Long id;
    private double quantiteParArbre; // En kilogrammes
    private ArbreViewModel arbre;
    private RecolteViewModel recolte;
}

