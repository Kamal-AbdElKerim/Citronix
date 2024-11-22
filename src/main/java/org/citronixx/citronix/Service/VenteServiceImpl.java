package org.citronixx.citronix.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Exception.ValidationException;
import org.citronixx.citronix.Model.MapStruct.RecolteMapper;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.citronixx.citronix.Model.entites.Recolte.RecolteDTO;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolteDTO;
import org.citronixx.citronix.Model.entites.Vente.VenteDTO;
import org.citronixx.citronix.Model.entites.Vente.Vente;
import org.citronixx.citronix.Model.entites.Vente.Response.ResponseVenteDTO;
import org.citronixx.citronix.Model.MapStruct.VenteMapper;
import org.citronixx.citronix.Repository.DetailRecolteRepository;
import org.citronixx.citronix.Repository.RecolteRepository;
import org.citronixx.citronix.Repository.VenteRepository;
import org.citronixx.citronix.Service.Interface.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenteServiceImpl implements VenteService {

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private VenteMapper venteMapper;

    @Autowired
    private RecolteRepository recolteRepository;



    @Autowired
    private DetailRecolteRepository detailRecolteRepository ;


    @Override
    public ResponseVenteDTO saveVente(Long recolteId, VenteDTO venteDTO) {

        Recolte recolte = recolteRepository.findById(recolteId)
                .orElseThrow(() -> new EntityNotFoundException("Recolte not found with id: " + recolteId));

        if (recolte.getVente() != null) {
            throw new ValidationException("vente", "A Vente already exists for this Recolte.");
        }

        List<DetailRecolte> detailRecolteByRecolteId = detailRecolteRepository.findByRecolteId(recolteId);
        double sumQuantiteParArbre = detailRecolteByRecolteId.stream()
                .mapToDouble(DetailRecolte::getQuantiteParArbre)
                .sum();
        double revenue = sumQuantiteParArbre * venteDTO.getPrixUnitaire();

        venteDTO.setDate(LocalDate.now());
        Vente vente = venteMapper.venteDTOToVente(venteDTO);
        vente.setRecolte(recolte);
        recolte.setVente(vente);

        Vente savedVente = venteRepository.save(vente);

        ResponseVenteDTO responseVenteDTO = venteMapper.venteToResponseVenteDTO(savedVente);
        responseVenteDTO.setRevenu(revenue);

        return responseVenteDTO;
    }




    @Override
    public List<ResponseVenteDTO> getAllVentes() {
        // Fetch all Vente entities
        List<Vente> ventes = venteRepository.findAll();

        // Map each Vente to a ResponseVenteDTO
        List<ResponseVenteDTO> responseVenteDTOs = ventes.stream().map(vente -> {
            Long recolteId = vente.getRecolte().getId(); // Ensure Vente has a reference to Recolte
            List<DetailRecolte> detailRecolteByRecolteId = detailRecolteRepository.findByRecolteId(recolteId);

            // Calculate the sum of QuantiteParArbre
            double sumQuantiteParArbre = detailRecolteByRecolteId.stream()
                    .mapToDouble(DetailRecolte::getQuantiteParArbre)
                    .sum();

            // Calculate revenue
            double revenue = sumQuantiteParArbre * vente.getPrixUnitaire();

            // Map Vente to ResponseVenteDTO and set the revenue
            ResponseVenteDTO responseVenteDTO = venteMapper.venteToResponseVenteDTO(vente);
            responseVenteDTO.setRevenu(revenue);

            return responseVenteDTO;
        }).collect(Collectors.toList());

        return responseVenteDTOs;
    }


    @Override
    public ResponseVenteDTO getVenteById(Long id) {
        Vente vente = venteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vente with ID " + id + " not found"));
        return venteMapper.venteToResponseVenteDTO(vente);
    }

    @Override
    public boolean deleteVente(Long id) {
        if (venteRepository.existsById(id)) {
            Vente vente = venteRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Vente with ID " + id + " not found"));
            venteRepository.delete(vente);
            return true;
        } else {
            throw new EntityNotFoundException("Vente with ID " + id + " not found");
        }
    }

    @Override
    public ResponseVenteDTO updateVente(Long venteId, VenteDTO venteDTO) {
        // Check if the Vente exists
        Vente existingVente = venteRepository.findById(venteId)
                .orElseThrow(() -> new EntityNotFoundException("Vente not found with id: " + venteId));

        // Update the existing Vente's fields
        existingVente.setPrixUnitaire(venteDTO.getPrixUnitaire());
        existingVente.setClient(venteDTO.getClient());
        existingVente.setDate(venteDTO.getDate() != null ? venteDTO.getDate() : LocalDate.now());

        // Recalculate revenue based on the associated Recolte
        Recolte recolte = existingVente.getRecolte();
        if (recolte == null) {
            throw new ValidationException("recolte", "The Vente is not associated with any Recolte.");
        }

        List<DetailRecolte> detailRecolteByRecolteId = detailRecolteRepository.findByRecolteId(recolte.getId());
        double sumQuantiteParArbre = detailRecolteByRecolteId.stream()
                .mapToDouble(DetailRecolte::getQuantiteParArbre)
                .sum();
        double revenue = sumQuantiteParArbre * venteDTO.getPrixUnitaire();

        // Save the updated Vente
        Vente updatedVente = venteRepository.save(existingVente);

        // Map to Response DTO
        ResponseVenteDTO responseVenteDTO = venteMapper.venteToResponseVenteDTO(updatedVente);
        responseVenteDTO.setRevenu(revenue);

        return responseVenteDTO;
    }


}
