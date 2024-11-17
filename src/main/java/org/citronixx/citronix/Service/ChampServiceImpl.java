package org.citronixx.citronix.Service;

import org.citronixx.citronix.Model.Entity.Champ;
import org.citronixx.citronix.Model.Entity.Ferme;
import org.citronixx.citronix.Repository.ChampRepository;
import org.citronixx.citronix.Service.Interface.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChampServiceImpl implements ChampService {

    @Autowired
    private ChampRepository champRepository;

    @Override
    public Champ saveChamp(Champ champ) {
        return champRepository.save(champ);
    }

    @Override
    public List<Champ> getAllChamps() {
        return champRepository.findAll();
    }

    @Override
    public Optional<Champ> getChampById(Long id) {
        return champRepository.findById(id);
    }

    @Override
    public void deleteChamp(Long id) {
        champRepository.deleteById(id);
    }

//    @Override
//    public List<Champ> findByFerme(Ferme ferme) {
//        return champRepository.findByFerme(ferme);
//    }
}
