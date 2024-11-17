package org.citronixx.citronix.Service;

import org.citronixx.citronix.Model.Entity.DetailRecolte;
import org.citronixx.citronix.Repository.DetailRecolteRepository;
import org.citronixx.citronix.Service.Interface.DetailRecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailRecolteServiceImpl implements DetailRecolteService {

    @Autowired
    private DetailRecolteRepository detailRecolteRepository;

    @Override
    public DetailRecolte saveDetailRecolte(DetailRecolte detailRecolte) {
        return detailRecolteRepository.save(detailRecolte);
    }

    @Override
    public List<DetailRecolte> getAllDetailRecoltes() {
        return detailRecolteRepository.findAll();
    }

    @Override
    public Optional<DetailRecolte> getDetailRecolteById(Long id) {
        return detailRecolteRepository.findById(id);
    }

    @Override
    public void deleteDetailRecolte(Long id) {
        detailRecolteRepository.deleteById(id);
    }

//    @Override
//    public List<DetailRecolte> findByArbre(Arbre arbre) {
//        return detailRecolteRepository.findByArbre(arbre);
//    }
}
