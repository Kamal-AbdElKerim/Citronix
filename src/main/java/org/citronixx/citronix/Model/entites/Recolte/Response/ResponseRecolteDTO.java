package org.citronixx.citronix.Model.entites.Recolte.Response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.citronixx.citronix.Model.Enum.Saison;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolteDTO;
import org.citronixx.citronix.Model.entites.DetailRecolte.Response.ResponseDetailRecolte;
import org.citronixx.citronix.Model.entites.Vente.Response.ResponseVente;
import org.citronixx.citronix.Model.entites.Vente.Response.ResponseVenteDTO;
import org.citronixx.citronix.Model.entites.Vente.Vente;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRecolteDTO {
    private Long id;

    @Enumerated(EnumType.STRING)
    private Saison saison;


    private LocalDate dateRecolte;

    private double quantiteTotale;

    private List<ResponseDetailRecolte> detailsRecolte;

  private List<ResponseVente> vente;

}
