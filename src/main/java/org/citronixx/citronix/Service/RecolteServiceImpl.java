package org.citronixx.citronix.Service;

import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Exception.ValidationException;
import org.citronixx.citronix.Model.MapStruct.RecolteMapper;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.citronixx.citronix.Model.entites.Ferme.Ferme;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.citronixx.citronix.Model.entites.Recolte.RecolteDTO;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolteDTO;
import org.citronixx.citronix.Repository.FermeRepository;
import org.citronixx.citronix.Repository.RecolteRepository;
import org.citronixx.citronix.Service.Interface.RecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecolteServiceImpl implements RecolteService {

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private RecolteMapper recolteMapper;

    @Autowired
    private FermeRepository fermeRepository;

    @Override
    public ResponseRecolteDTO addRecolte(Long fermeId, RecolteDTO recolteDTO) {
        // Check if the farm exists
        Ferme ferme = fermeRepository.findById(fermeId)
                .orElseThrow(() -> new EntityNotFoundException("Ferme not found with id: " + fermeId));

        // Check if a recolte for the same farm, season, and year already exists
        if (recolteRepository.existsByFermeIdAndSaisonAndYear(
                fermeId,
                recolteDTO.getSaison(),
                recolteDTO.getDateRecolte().getYear())) {
            throw new ValidationException("recolte", "A recolte with the same season and year already exists for this farm.");
        }

        // Map DTO to entity and set the farm
        Recolte recolte = recolteMapper.recolteDTOToRecolte(recolteDTO);

        recolte.setFerme(ferme);

        // Save and map back to DTO
        Recolte savedRecolte = recolteRepository.save(recolte);
        return recolteMapper.recolteToResponseRecolteDTO(savedRecolte);
    }


    @Override
    public ResponseRecolteDTO getRecolteById(Long recolteId) {
        Recolte recolte = recolteRepository.findById(recolteId)
                .orElseThrow(() -> new EntityNotFoundException("Recolte not found with id: " + recolteId));
        return recolteMapper.recolteToResponseRecolteDTO(recolte);
    }

    @Override
    public List<ResponseRecolteDTO> getAllRecoltes() {
        List<Recolte> recoltes = recolteRepository.findAll();

        // Return the list of ResponseRecolteDTO after mapping
        return recolteMapper.recolteToResponseRecolteDTO(recoltes);
    }


    @Override
    public ResponseRecolteDTO updateRecolte(Long recolteId, RecolteDTO recolteDTO) {
        // Retrieve the existing Recolte entity
        Recolte existingRecolte = recolteRepository.findById(recolteId)
                .orElseThrow(() -> new EntityNotFoundException("Recolte not found with id: " + recolteId));

        // Update fields only if provided in RecolteDTO
        if (recolteDTO.getSaison() != null) {
            existingRecolte.setSaison(recolteDTO.getSaison());
        }
        if (recolteDTO.getDateRecolte() != null) {
            existingRecolte.setDateRecolte(recolteDTO.getDateRecolte());
        }
        if (recolteDTO.getQuantiteTotale() != 0.0) {
            existingRecolte.setQuantiteTotale(recolteDTO.getQuantiteTotale());
        }

        // Save the updated Recolte entity
        Recolte updatedRecolte = recolteRepository.save(existingRecolte);

        // Map to Response DTO and return
        return recolteMapper.recolteToResponseRecolteDTO(updatedRecolte);
    }


    @Override
    public String deleteRecolte(Long recolteId) {
        if (!recolteRepository.existsById(recolteId)) {
            throw new EntityNotFoundException("Recolte not found with id: " + recolteId);
        }
        recolteRepository.deleteById(recolteId);
        return "Recolte deleted successfully!";
    }


}
