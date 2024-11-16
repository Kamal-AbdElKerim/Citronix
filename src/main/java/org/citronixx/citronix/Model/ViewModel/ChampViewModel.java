package org.citronixx.citronix.Model.ViewModel;

import lombok.*;
import org.citronixx.citronix.Model.Entity.Arbre;
import org.citronixx.citronix.Model.Entity.Ferme;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChampViewModel {
    private Long id;
    private String nom;
    private double superficie; // en hectares
    private String fermeNom; // Nom de la ferme associée
    private int nombreArbres; // Nombre total d'arbres dans le champ
    private boolean estSuperficieValide; // Validation côté affichage
    private List<ArbreViewModel> arbres;
    private FermeViewModel ferme;
}
