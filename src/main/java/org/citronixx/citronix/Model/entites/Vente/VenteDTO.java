package org.citronixx.citronix.Model.entites.Vente;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.citronixx.citronix.Model.entites.Recolte.RecolteDTO;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenteDTO {
    private Long id;


    private LocalDate date;

    @DecimalMin(value = "0.1", message = "Le prix unitaire doit être supérieur à 0.")
    private double prixUnitaire;

    @NotBlank(message = "Le nom du client est obligatoire.")
    private String client;

}
