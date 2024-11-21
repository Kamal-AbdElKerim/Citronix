package org.citronixx.citronix.Model.entites.Arbre;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.citronixx.citronix.Model.entites.Champ.ChampDTO;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArbreDTO {
    private Long id;


    @Past(message = "La date de plantation doit être une date passée.")
    private LocalDate datePlantation;

    private ChampDTO champ;
}
