package org.citronixx.citronix.Model.ViewModel;

import lombok.*;
import org.citronixx.citronix.Model.Entity.Recolte;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenteViewModel {
    private Long id;
    private String date; // Formatée pour affichage
    private double prixUnitaire; // En monnaie locale (MAD, EUR, etc.)
    private String client; // Nom ou informations du client
    private double quantiteTotale; // Quantité totale vendue
    private double revenuTotal; // Calculé : quantiteTotale * prixUnitaire
    private RecolteViewModel recolte;

}
