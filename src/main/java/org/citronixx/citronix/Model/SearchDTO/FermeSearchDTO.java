package org.citronixx.citronix.Model.SearchDTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class FermeSearchDTO {
    private String nom;
    private String localisation;
    private Double superficieMin;
    private Double superficieMax;
    private LocalDate dateCreationMin;
    private LocalDate dateCreationMax;

    // Getters and Setters
}
