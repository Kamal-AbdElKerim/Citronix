package org.citronixx.citronix.Model.entites.Recolte.Response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.citronixx.citronix.Model.Enum.Saison;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolteDTO;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRecolte {
    private Long id;

    @Enumerated(EnumType.STRING)
    private Saison saison;

    private LocalDate dateRecolte;

    private double quantiteTotale;

}
