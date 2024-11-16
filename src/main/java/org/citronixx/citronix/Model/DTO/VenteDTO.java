package org.citronixx.citronix.Model.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.citronixx.citronix.Model.Entity.Recolte;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenteDTO {
    private Long id;

    @NotNull(message = "La date de la vente est obligatoire.")
    @PastOrPresent(message = "La date de la vente doit être passée ou aujourd'hui.")
    private LocalDate date;

    @DecimalMin(value = "0.1", message = "Le prix unitaire doit être supérieur à 0.")
    private double prixUnitaire;

    @NotBlank(message = "Le nom du client est obligatoire.")
    private String client;
    private RecolteDTO recolte;
}
