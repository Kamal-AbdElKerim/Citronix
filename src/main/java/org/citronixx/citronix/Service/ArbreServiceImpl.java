package org.citronixx.citronix.Service;

import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Exception.ValidationException;
import org.citronixx.citronix.Model.MapStruct.ArbreMapper;
import org.citronixx.citronix.Model.MapStruct.ChampMapper;
import org.citronixx.citronix.Model.entites.Arbre.Arbre;
import org.citronixx.citronix.Model.entites.Arbre.ArbreDTO;
import org.citronixx.citronix.Model.entites.Arbre.Response.ResponseArbreDTO;
import org.citronixx.citronix.Model.entites.Champ.Champ;
import org.citronixx.citronix.Model.entites.Ferme.Response.ResponseFermeDTO;
import org.citronixx.citronix.Repository.ArbreRepository;
import org.citronixx.citronix.Repository.ChampRepository;
import org.citronixx.citronix.Service.Interface.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ArbreServiceImpl implements ArbreService {

    @Autowired
    private ArbreRepository arbreRepository;

    @Autowired
    private ArbreMapper arbreMapper;

    @Autowired
    private ChampMapper champMapper;

    @Autowired
    private ChampRepository champRepository;

    @Override
    public ResponseArbreDTO addArbreToChamp(Long champId, ArbreDTO arbreDTO) {
        // Fetch the Champ entity
        Champ champ = champRepository.findById(champId)
                .orElseThrow(() -> new EntityNotFoundException("Champ with ID " + champId + " not found"));

        validateTreeSpacing( arbreDTO , champ) ;

        // Map the ArbreDTO to an Arbre entity
        Arbre arbre = arbreMapper.arbreDTOToArbre(arbreDTO);
        arbre.setChamp(champ);

        // Save the Arbre entity
        Arbre savedArbre = arbreRepository.save(arbre);

        // Build and return the response DTO
        ResponseArbreDTO responseArbreDTO = ResponseArbreDTO.builder()
                .datePlantation(savedArbre.getDatePlantation())
                .champ(champMapper.champToResponseChamp(savedArbre.getChamp()))
                .age(savedArbre.calculateAge())
                .productivity(savedArbre.calculateProductivity())
                .build();

        return responseArbreDTO;
    }

    public void validateTreeSpacing(ArbreDTO arbreDTO , Champ champ){
        // Validate the planting date
        LocalDate datePlantation = arbreDTO.getDatePlantation();
        if (datePlantation.getMonthValue() < 3 || datePlantation.getMonthValue() > 5) {
            throw new ValidationException("Trees","Trees can only be planted between March and May.");
        }

        // Validate the tree density
        int existingArbres = champ.getArbres().size();
        System.out.println("existingArbres: " + existingArbres);

// Assuming champ.getSuperficie() returns the area in hectares
        double superficieHectares = champ.getSuperficie(); // in hectares
        double superficieM2 = superficieHectares * 10000; // Convert hectares to m²

// Max density: 10 trees per 1,000 m²
        int maxArbres = (int) (superficieM2 / 100); // Max number of trees that can be planted
        System.out.println("maxArbres: " + maxArbres);

        if (existingArbres >= maxArbres) {
            throw new ValidationException("Trees","Maximum tree density exceeded for this Champ.");
        }
    }


    @Override
    public List<ResponseArbreDTO> getAllArbres() {

        return arbreMapper.arbreToResponseArbreDTO(arbreRepository.findAll()) ;
    }

    @Override
    public ResponseArbreDTO getArbreById(Long id) {
      Arbre arbre =  arbreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arbre with ID " + id + " not found"));;

        return arbreMapper.arbreToResponseArbreDTO(arbre) ;
    }

    @Override
    public String deleteArbre(Long id) {
        arbreRepository.deleteById(id);
        return "deleted" ;
    }

   @Override
   public ResponseArbreDTO updateArbre(Long arbreId, ArbreDTO arbreDTO) {
    // Fetch the existing Arbre entity
    Arbre existingArbre = arbreRepository.findById(arbreId)
            .orElseThrow(() -> new EntityNotFoundException("Arbre with ID " + arbreId + " not found"));

    // Validate the planting date if it is being updated
    LocalDate newDatePlantation = arbreDTO.getDatePlantation();
    if (newDatePlantation != null &&
            (newDatePlantation.getMonthValue() < 3 || newDatePlantation.getMonthValue() > 5)) {
        throw new IllegalArgumentException("Trees can only be planted between March and May.");
    }

    // Update the properties
    if (arbreDTO.getDatePlantation() != null) {
        existingArbre.setDatePlantation(arbreDTO.getDatePlantation());
    }


    // Save the updated Arbre entity
    Arbre updatedArbre = arbreRepository.save(existingArbre);

    // Build and return the response DTO
    ResponseArbreDTO responseArbreDTO = ResponseArbreDTO.builder()
            .datePlantation(updatedArbre.getDatePlantation())
            .champ(champMapper.champToResponseChamp(updatedArbre.getChamp()))
            .age(updatedArbre.calculateAge())
            .productivity(updatedArbre.calculateProductivity())
            .build();

    return responseArbreDTO;
}

//
//    @Override
//    public List<Arbre> findByAgeGreaterThan(int age) {
//        return arbreRepository.findByAgeGreaterThan(age);
//    }
}
