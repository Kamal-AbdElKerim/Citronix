package org.citronixx.citronix.Model.ViewModel;

import lombok.*;
import org.citronixx.citronix.Model.Entity.Champ;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArbreViewModel {
    private Long id;
    private String datePlantation; // Formatée pour affichage
    private int age; // Calculé dynamiquement
    private String champNom; // Nom du champ associé
    private String etatProductivite; // Jeune, Mature, Vieux
    private double productiviteParSaison; // En kg
    private Champ champ;
}
