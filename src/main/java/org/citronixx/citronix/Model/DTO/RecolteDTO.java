package org.citronixx.citronix.Model.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.citronixx.citronix.Model.Enum.Saison;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecolteDTO {
    private Long id;

    @Enumerated(EnumType.STRING)
    private Saison saison;


    @NotNull(message = "La date de récolte est obligatoire.")
    @PastOrPresent(message = "La date de récolte doit être passée ou aujourd'hui.")
    private LocalDate dateRecolte;

    @DecimalMin(value = "0.0", message = "La quantité totale récoltée doit être positive.")
    private double quantiteTotale;
    private List<DetailRecolteDTO> detailsRecolte;
}
