package org.citronixx.citronix.Model.ViewModel;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecolteViewModel {
    private Long id;
    private String saison; // Hiver, Printemps, Été, Automne
    private String dateRecolte; // Formatée pour affichage
    private double quantiteTotale; // En kilogrammes
    private int nombreDetails; // Nombre de détails associés
    private List<DetailRecolteViewModel> detailsRecolte; // Pour afficher les détails si nécessaire
}
