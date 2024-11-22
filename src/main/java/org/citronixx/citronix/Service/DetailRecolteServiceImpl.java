package org.citronixx.citronix.Service;


import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Exception.ValidationException;
import org.citronixx.citronix.Model.MapStruct.DetailRecolteMapper;
import org.citronixx.citronix.Model.entites.Arbre.Arbre;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolteDTO;
import org.citronixx.citronix.Model.entites.DetailRecolte.Response.ResponseDetailRecolteDTO;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.citronixx.citronix.Repository.ArbreRepository;
import org.citronixx.citronix.Repository.DetailRecolteRepository;
import org.citronixx.citronix.Repository.RecolteRepository;
import org.citronixx.citronix.Service.Interface.DetailRecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailRecolteServiceImpl implements DetailRecolteService {

    @Autowired
    private DetailRecolteRepository detailRecolteRepository;

    @Autowired
    private DetailRecolteMapper detailRecolteMapper;

    @Autowired
    private ArbreRepository arbreRepository;

    @Autowired
    private RecolteRepository recolteRepository;

    @Override
    public ResponseDetailRecolteDTO addDetailRecolteToArbre(Long recolteID ,Long arbreId , DetailRecolteDTO detailRecolteDTO) {
      Arbre arbre = arbreRepository.findById(arbreId)
                .orElseThrow(() -> new EntityNotFoundException("arbre not found with ID: " + arbreId));
        Recolte recolte = recolteRepository.findById(recolteID)
                .orElseThrow(() -> new EntityNotFoundException("recolte not found with ID: " + recolteID));



        if (detailRecolteRepository.existsByRecolteIdAndArbreId(recolteID, arbreId)) {
            throw new ValidationException(
                    "arbre",
                    "arbre has already been assigned to a Recolte"
            );
        }

        if (arbre.calculateProductivity() < detailRecolteDTO.getQuantiteParArbre()) {
            throw new ValidationException(
                    "arbre",
                    "The quantity per tree (" + detailRecolteDTO.getQuantiteParArbre() + " kg) exceeds the maximum productivity ("
                            + arbre.calculateProductivity() + " kg) for this tree."
            );
        }

        DetailRecolte detailRecolte =  detailRecolteMapper.detailRecolteDTOToDetailRecolte(detailRecolteDTO);
        detailRecolte.setArbre(arbre);
        detailRecolte.setRecolte(recolte);
        DetailRecolte detailRecoltes = detailRecolteRepository.save(detailRecolte);
        List<DetailRecolte> detailRecolteByRecolteId = detailRecolteRepository.findByRecolteId(recolte.getId());

        // Calculate the sum of QuantiteParArbre
        double sumQuantiteParArbre = detailRecolteByRecolteId.stream()
                .mapToDouble(DetailRecolte::getQuantiteParArbre)
                .sum();

        recolte.setQuantiteTotale(sumQuantiteParArbre);

        recolteRepository.save(recolte);
        return detailRecolteMapper.detailRecolteToResponseDetailRecolteDTO(detailRecoltes);
    }



    @Override
    public List<ResponseDetailRecolteDTO> getAllDetailRecoltes() {
        List<DetailRecolte> detailRecoltes = detailRecolteRepository.findAll();
        return detailRecolteMapper.detailRecolteToResponseDetailRecolteDTO(detailRecoltes) ;
    }

    @Override
    public ResponseDetailRecolteDTO getDetailRecolteById(Long detailrecolteId) {
        DetailRecolte detailRecolte = detailRecolteRepository.findById(detailrecolteId)
                .orElseThrow(() -> new EntityNotFoundException("Ferme not found with ID: " + detailrecolteId));
        return detailRecolteMapper.detailRecolteToResponseDetailRecolteDTO(detailRecolte) ;
    }

    @Override
    public String  deleteDetailRecolte(Long id) {
        detailRecolteRepository.deleteById(id);
        return "deletd";
    }

    @Override
public ResponseDetailRecolteDTO updateDetailRecolte(Long detailRecolteId, DetailRecolteDTO detailRecolteDTO) {
    // Retrieve the existing DetailRecolte
    DetailRecolte existingDetailRecolte = detailRecolteRepository.findById(detailRecolteId)
            .orElseThrow(() -> new EntityNotFoundException("DetailRecolte not found with ID: " + detailRecolteId));

    Arbre arbre = existingDetailRecolte.getArbre();


    if (detailRecolteDTO.getQuantiteParArbre() > arbre.calculateProductivity()) {
        throw new ValidationException(
                "arbre",
                "The quantity per tree (" + detailRecolteDTO.getQuantiteParArbre() + " kg) exceeds the maximum productivity ("
                        + arbre.calculateProductivity() + " kg) for this tree."
        );
    }

    if (detailRecolteDTO.getQuantiteParArbre() != 0.0) {
        existingDetailRecolte.setQuantiteParArbre(detailRecolteDTO.getQuantiteParArbre());
    }

    DetailRecolte updatedDetailRecolte = detailRecolteRepository.save(existingDetailRecolte);

    return detailRecolteMapper.detailRecolteToResponseDetailRecolteDTO(updatedDetailRecolte);
}

}
