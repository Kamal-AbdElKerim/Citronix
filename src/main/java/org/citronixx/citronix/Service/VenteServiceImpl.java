package org.citronixx.citronix.Service;

import org.citronixx.citronix.Model.entites.Vente.Vente;
import org.citronixx.citronix.Repository.VenteRepository;
import org.citronixx.citronix.Service.Interface.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenteServiceImpl implements VenteService {

    @Autowired
    private VenteRepository venteRepository;

    @Override
    public Vente saveVente(Vente vente) {
        return venteRepository.save(vente);
    }

    @Override
    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    @Override
    public Optional<Vente> getVenteById(Long id) {
        return venteRepository.findById(id);
    }

    @Override
    public void deleteVente(Long id) {
        venteRepository.deleteById(id);
    }

//    @Override
//    public List<Vente> findByDateVente(LocalDate date) {
//        return venteRepository.findByDateVente(date);
//    }
//
//    @Override
//    public double calculateRevenue(Vente vente) {
//        return vente.getQuantity() * vente.getPrice();
//    }
}
