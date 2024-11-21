package org.citronixx.citronix.Model.entites.Ferme.Response;

import lombok.*;
import org.citronixx.citronix.Model.entites.Champ.Response.ResponseChampDTO;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseFermeDTO {
    private Long id;

    private String nom;


    private String localisation;

    private double superficie;


    private LocalDate dateCreation;

    private List<ResponseChampDTO> champs;

}
