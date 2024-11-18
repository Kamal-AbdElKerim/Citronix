package org.citronixx.citronix.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Model.DTO.FermeDTO;
import org.citronixx.citronix.Model.Entity.Ferme;
import org.citronixx.citronix.Model.MapStruct.FermeMapper;
import org.citronixx.citronix.Model.SearchDTO.FermeSearchDTO;
import org.citronixx.citronix.Model.ViewModel.FermeViewModel;
import org.citronixx.citronix.Repository.FermeRepository;
import org.citronixx.citronix.Service.Interface.FermeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FermeServiceImpl implements FermeService {

    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private FermeMapper fermeMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public FermeViewModel saveFerme(FermeDTO fermeDTO) {
         fermeDTO.setDateCreation(LocalDate.now());
         Ferme ferme = fermeMapper.fermeDTOToFerme(fermeDTO);
         Ferme saveferme  =  fermeRepository.save(ferme);
         return fermeMapper.fermeToFermeViewModel(saveferme);
    }

    @Override
    public List<FermeViewModel> getAllFermes() {
        List<Ferme> fermes = fermeRepository.findAll(); // Get Fermes from DB
        return fermes.stream()
                .map(fermeMapper::fermeToFermeViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public FermeViewModel getFermeById(Long id) {
        Ferme ferme = fermeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ferme with ID " + id + " not found"));

        return fermeMapper.fermeToFermeViewModel(ferme);
    }


    @Override
    public boolean deleteFerme(Long id) {
        // Check if the Ferme exists
        if (fermeRepository.existsById(id)) {
            // Delete the Ferme
            fermeRepository.deleteById(id);
            return true; // Deletion successful
        } else {
            // Throw an exception if Ferme is not found
            throw new EntityNotFoundException("Ferme with ID " + id + " not found");
        }
    }


//    @Override
//    public List<Ferme> findByNom(String nom) {
//        return fermeRepository.findByNom(nom);
//    }
   @Override
   public FermeViewModel updateFerme(Long id, FermeDTO fermeDTO) {
       // Check if the Ferme exists
       Ferme existingFerme = fermeRepository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Ferme with ID " + id + " not found"));

       // Build the updated Ferme using the existing Ferme and values from the FermeDTO
       Ferme updatedFerme = existingFerme.toBuilder()
               .nom(fermeDTO.getNom() != null ? fermeDTO.getNom() : existingFerme.getNom())
               .localisation(fermeDTO.getLocalisation() != null ? fermeDTO.getLocalisation() : existingFerme.getLocalisation())
               .superficie(fermeDTO.getSuperficie() != 0 ? fermeDTO.getSuperficie() : existingFerme.getSuperficie())
               .dateCreation(fermeDTO.getDateCreation() != null ? fermeDTO.getDateCreation() : existingFerme.getDateCreation())
               .build();

       // Save the updated Ferme
       Ferme savedFerme = fermeRepository.save(updatedFerme);

       // Return the updated Ferme as a FermeViewModel
       return fermeMapper.fermeToFermeViewModel(savedFerme);
   }


    public List<Ferme> searchFermes(FermeSearchDTO searchDTO) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ferme> criteriaQuery = criteriaBuilder.createQuery(Ferme.class);
        Root<Ferme> fermeRoot = criteriaQuery.from(Ferme.class);

        // List to hold the predicates (conditions)
        List<Predicate> predicates = new ArrayList<>();

        // Check and add the conditions dynamically
        if (searchDTO.getNom() != null) {
            predicates.add(criteriaBuilder.like(fermeRoot.get("nom"), "%" + searchDTO.getNom() + "%"));
        }
        if (searchDTO.getLocalisation() != null) {
            predicates.add(criteriaBuilder.like(fermeRoot.get("localisation"), "%" + searchDTO.getLocalisation() + "%"));
        }
        if (searchDTO.getSuperficieMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(fermeRoot.get("superficie"), searchDTO.getSuperficieMin()));
        }
        if (searchDTO.getSuperficieMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(fermeRoot.get("superficie"), searchDTO.getSuperficieMax()));
        }
        if (searchDTO.getDateCreationMin() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(fermeRoot.get("dateCreation"), searchDTO.getDateCreationMin()));
        }
        if (searchDTO.getDateCreationMax() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(fermeRoot.get("dateCreation"), searchDTO.getDateCreationMax()));
        }

        // Combine all predicates (conditions) using 'AND' logic
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        // Execute the query
        TypedQuery<Ferme> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
