package org.citronixx.citronix.Model.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FermeDTO {
    private Long id;


    @NotBlank(message = "Le nom de la ferme est obligatoire.")
    @Size(max = 100, message = "Le nom de la ferme ne peut pas dépasser 100 caractères.")
    private String nom;

    @NotBlank(message = "La localisation de la ferme est obligatoire.")
    @Size(max = 200, message = "La localisation ne peut pas dépasser 200 caractères.")
    private String localisation;

    @DecimalMin(value = "0.1", message = "La superficie doit être au moins de 0.1 hectare.")
    private double superficie;


    private LocalDate dateCreation;

    private List<ChampDTO> champs;

}
