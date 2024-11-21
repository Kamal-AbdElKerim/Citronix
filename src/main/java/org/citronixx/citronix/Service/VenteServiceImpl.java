package org.citronixx.citronix.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Model.MapStruct.RecolteMapper;
import org.citronixx.citronix.Model.entites.Recolte.Recolte;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolteDTO;
import org.citronixx.citronix.Model.entites.Vente.VenteDTO;
import org.citronixx.citronix.Model.entites.Vente.Vente;
import org.citronixx.citronix.Model.entites.Vente.Response.ResponseVenteDTO;
import org.citronixx.citronix.Model.MapStruct.VenteMapper;
import org.citronixx.citronix.Repository.RecolteRepository;
import org.citronixx.citronix.Repository.VenteRepository;
import org.citronixx.citronix.Service.Interface.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VenteServiceImpl implements VenteService {

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private VenteMapper venteMapper;

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private RecolteMapper recolteMapper;


    @Override
    public ResponseVenteDTO saveVente(Long recolteId ,VenteDTO venteDTO) {
      Recolte recolte = recolteRepository.findById(recolteId)
                .orElseThrow(() -> new EntityNotFoundException("recolte not found with ID: " + recolteId));

        ResponseRecolteDTO responseRecolteDTO =  recolteMapper.recolteToResponseRecolteDTO(recolte) ;
        double revenue = responseRecolteDTO.getQuantiteTotale() * venteDTO.getPrixUnitaire();

        venteDTO.setDate(LocalDate.now());
        Vente vente = venteMapper.venteDTOToVente(venteDTO);
        vente.setRecolte(recolte);
        vente.setRevenu(revenue);
        Vente savedVente = venteRepository.save(vente);
        return venteMapper.venteToResponseVenteDTO(savedVente);
    }



    @Override
    public List<ResponseVenteDTO> getAllVentes() {
        List<Vente> ventes = venteRepository.findAll();
        return venteMapper.venteToResponseVenteDTO(ventes);
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
            venteRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Vente with ID " + id + " not found");
        }
    }

    @Override
    public ResponseVenteDTO updateVente(Long id, VenteDTO venteDTO) {
        Vente existingVente = venteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vente with ID " + id + " not found"));

        Vente updatedVente = existingVente.toBuilder()
                .client(existingVente.getClient())
                .build();

        Vente savedVente = venteRepository.save(updatedVente);

        return venteMapper.venteToResponseVenteDTO(savedVente);
    }


}
