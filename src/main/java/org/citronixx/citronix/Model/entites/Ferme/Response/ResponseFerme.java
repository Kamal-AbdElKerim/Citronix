package org.citronixx.citronix.Model.entites.Ferme.Response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseFerme {
    private Long id;

    private String nom;


    private String localisation;

    private double superficie;


    private LocalDate dateCreation;


}
