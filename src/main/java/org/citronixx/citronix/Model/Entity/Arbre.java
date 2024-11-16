package org.citronixx.citronix.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datePlantation;
    private int age;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;
}


