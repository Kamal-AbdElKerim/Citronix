package org.citronixx.citronix.Service;

import org.citronixx.citronix.Model.Entity.Recolte;
import org.citronixx.citronix.Repository.RecolteRepository;
import org.citronixx.citronix.Service.Interface.RecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecolteServiceImpl implements RecolteService {

    @Autowired
    private RecolteRepository recolteRepository;

    @Override
    public Recolte saveRecolte(Recolte recolte) {
        return recolteRepository.save(recolte);
    }

    @Override
    public List<Recolte> getAllRecoltes() {
        return recolteRepository.findAll();
    }

    @Override
    public Optional<Recolte> getRecolteById(Long id) {
        return recolteRepository.findById(id);
    }

    @Override
    public void deleteRecolte(Long id) {
        recolteRepository.deleteById(id);
    }


}

