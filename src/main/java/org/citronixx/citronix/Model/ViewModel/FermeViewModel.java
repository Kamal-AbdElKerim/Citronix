package org.citronixx.citronix.Model.ViewModel;

import lombok.*;
import org.citronixx.citronix.Model.Entity.Champ;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FermeViewModel {
    private Long id;
    private String nom;
    private String localisation;
    private double superficie;
    private int nombreChamps; // Calculé à partir de la liste des champs
    private List<ChampViewModel> champs;
}
