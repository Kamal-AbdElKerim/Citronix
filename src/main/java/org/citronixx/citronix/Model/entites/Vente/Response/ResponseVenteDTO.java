package org.citronixx.citronix.Model.entites.Vente.Response;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.citronixx.citronix.Model.entites.Recolte.RecolteDTO;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolte;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVenteDTO {

    private Long id;

    private LocalDate date;

    private double prixUnitaire;

    private double revenu;

    private String client;

    private ResponseRecolte recolte;
}
