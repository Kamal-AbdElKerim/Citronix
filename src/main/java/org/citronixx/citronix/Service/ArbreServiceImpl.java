package org.citronixx.citronix.Service;

import org.citronixx.citronix.Model.Entity.Arbre;
import org.citronixx.citronix.Model.Entity.Champ;
import org.citronixx.citronix.Repository.ArbreRepository;
import org.citronixx.citronix.Service.Interface.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArbreServiceImpl implements ArbreService {

    @Autowired
    private ArbreRepository arbreRepository;

    @Override
    public Arbre saveArbre(Arbre arbre) {
        return arbreRepository.save(arbre);
    }

    @Override
    public List<Arbre> getAllArbres() {
        return arbreRepository.findAll();
    }

    @Override
    public Optional<Arbre> getArbreById(Long id) {
        return arbreRepository.findById(id);
    }

    @Override
    public void deleteArbre(Long id) {
        arbreRepository.deleteById(id);
    }

//    @Override
//    public List<Arbre> findByChamp(Champ champ) {
//        return arbreRepository.findByChamp(champ);
//    }
//
//    @Override
//    public List<Arbre> findByAgeGreaterThan(int age) {
//        return arbreRepository.findByAgeGreaterThan(age);
//    }
}
