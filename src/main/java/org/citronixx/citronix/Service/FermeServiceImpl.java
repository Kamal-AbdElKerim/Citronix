package org.citronixx.citronix.Service;

import org.citronixx.citronix.Model.Entity.Ferme;
import org.citronixx.citronix.Repository.FermeRepository;
import org.citronixx.citronix.Service.Interface.FermeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FermeServiceImpl implements FermeService {

    @Autowired
    private FermeRepository fermeRepository;

    @Override
    public Ferme saveFerme(Ferme ferme) {
        return fermeRepository.save(ferme);
    }

    @Override
    public List<Ferme> getAllFermes() {
        return fermeRepository.findAll();
    }

    @Override
    public Optional<Ferme> getFermeById(Long id) {
        return fermeRepository.findById(id);
    }

    @Override
    public void deleteFerme(Long id) {
        fermeRepository.deleteById(id);
    }

//    @Override
//    public List<Ferme> findByNom(String nom) {
//        return fermeRepository.findByNom(nom);
//    }
}
