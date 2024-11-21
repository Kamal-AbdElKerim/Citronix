package org.citronixx.citronix.Model.entites.Arbre;

import jakarta.persistence.*;
import lombok.*;
import org.citronixx.citronix.Model.entites.Champ.Champ;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;


import java.time.LocalDate;
import java.util.List;


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

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

    @OneToMany(mappedBy = "arbre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailRecolte> detailRecoltes ;

    public int calculateAge() {
        return LocalDate.now().getYear() - datePlantation.getYear();
    }

    // Utility method to determine if the tree is productive
    public boolean isProductive() {
        return calculateAge() <= 20;
    }

    // Utility method to calculate productivity based on age
    public double calculateProductivity() {
        int age = calculateAge();
        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12.0;
        } else if (age <= 20) {
            return 20.0;
        } else {
            return 0.0;
        }
    }
}


