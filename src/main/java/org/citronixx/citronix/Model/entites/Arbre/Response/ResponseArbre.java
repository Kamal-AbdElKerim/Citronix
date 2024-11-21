package org.citronixx.citronix.Model.entites.Arbre.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.citronixx.citronix.Model.entites.Champ.ChampDTO;
import org.citronixx.citronix.Model.entites.Champ.Response.ResponseChamp;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.citronixx.citronix.Model.entites.DetailRecolte.Response.ResponseDetailRecolte;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseArbre {
    private Long id;


    private LocalDate datePlantation;

    private Integer age;
   // private ResponseChamp champ;


    private List<ResponseDetailRecolte> detailRecoltes ;


}
