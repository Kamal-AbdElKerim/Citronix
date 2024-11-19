package org.citronixx.citronix.Service;

import org.citronixx.citronix.Exception.EntityNotFoundException;
import org.citronixx.citronix.Exception.ValidationException;
import org.citronixx.citronix.Model.DTO.ChampDTO;
import org.citronixx.citronix.Model.Entity.Champ;
import org.citronixx.citronix.Model.Entity.Ferme;
import org.citronixx.citronix.Model.MapStruct.ChampMapper;
import org.citronixx.citronix.Model.ViewModel.ChampViewModel;
import org.citronixx.citronix.Repository.ChampRepository;
import org.citronixx.citronix.Repository.FermeRepository;
import org.citronixx.citronix.Service.Interface.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChampServiceImpl implements ChampService {

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private ChampMapper champMapper;

    @Override
    public ChampViewModel addChampToFerme(Long fermeId, ChampDTO champDTO) {

        Ferme ferme = fermeRepository.findById(fermeId)
                .orElseThrow(() -> new EntityNotFoundException("Ferme not found with ID: " + fermeId));


        System.out.println(ferme.getChamps());

        ValidationChamp( champDTO , ferme) ;

        // Map DTO to Entity
        Champ champ = champMapper.champDTOToChamp(champDTO);
        champ.setFerme(ferme);

        // Save the Champ
        Champ savedChamp = champRepository.save(champ);


        return  ChampsToChampViewModels(savedChamp);
    }

    public void ValidationChamp(ChampDTO champDTO , Ferme ferme) {

        double totalChampsSuperficie = ferme.getChamps().stream()
                .mapToDouble(Champ::getSuperficie)
                .sum();

        if (champDTO.getSuperficie() < 0.1) {
            throw new ValidationException("superficie","Champ superficie must be at least 0.1 hectares (1,000 m²)");
        }
        System.out.println(ferme.getSuperficie() * 0.5);
        System.out.println(champDTO.getSuperficie());
        if (champDTO.getSuperficie() > (ferme.getSuperficie() * 0.5)) {
            throw new ValidationException("superficie", "La superficie d'un champ ne peut pas dépasser 50% de celle de la ferme.");
        }

        if (ferme.getChamps().size() >= 10) {
            throw new ValidationException("ferme", "La ferme ne peut pas contenir plus de 10 champs.");
        }


        if ((totalChampsSuperficie + champDTO.getSuperficie()) >= ferme.getSuperficie()) {
            throw new ValidationException("ferme","The sum of the champs' superficies must be strictly less than the Ferme's superficie");
        }

    }


    @Override
    public List<ChampViewModel> getAllChamps() {
        List<Champ> champs =  champRepository.findAll();
        System.out.println(champs);

      return  ListChampsToChampViewModels(champs);
    }

    public  List<ChampViewModel> ListChampsToChampViewModels(List<Champ> champs) {
        List<ChampViewModel> champViewModels = new ArrayList<>();
        for (Champ champ : champs) {
            ChampViewModel champViewModel = ChampViewModel.builder()
                    .id(champ.getId())
                    .nom(champ.getNom())
                    .fermeNom(champ.getFerme().getNom())
                    .nombreArbres(champ.getArbres() != null ? champ.getArbres().size() : 0)
                    .superficie(champ.getSuperficie())
                    .build();
            champViewModels.add(champViewModel);
        }

        return champViewModels;

    }

    public  ChampViewModel ChampsToChampViewModels(Champ champ){

        return ChampViewModel.builder()
                .id(champ.getId())
                .nom(champ.getNom())
                .fermeNom(champ.getFerme().getNom())
                .nombreArbres(champ.getArbres() != null ? champ.getArbres().size() : 0)
                .superficie(champ.getSuperficie())
                .build();
    }

    @Override
    public Optional<ChampViewModel> getChampById(Long id) {
        Optional<Champ> champ = champRepository.findById(id);
        return champ.map(this::ChampsToChampViewModels);
    }


    @Override
    public String deleteChamp(Long id) {
        Champ existingChamp = champRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Champ with ID " + id + " not found"));

        champRepository.deleteById(id);

        return "Champ " + existingChamp.getNom() + " is deleted";
    }



    @Override
    public ChampViewModel updateChamp (Long champId, ChampDTO champDTO){
        Champ existingChamp = champRepository.findById(champId)
                .orElseThrow(() -> new EntityNotFoundException("Champ with ID " + champId + " not found"));
        Champ updatedChamp = existingChamp.toBuilder()
                .nom(champDTO.getNom() != null ? champDTO.getNom() : existingChamp.getNom())
                .superficie(champDTO.getSuperficie() != 0 ? champDTO.getSuperficie() :existingChamp.getSuperficie() )
                .build();

        ValidationChamp( champDTO , existingChamp.getFerme()) ;

        Champ savedChamp = champRepository.save(updatedChamp);
     //  return champMapper.champToChampViewModel(savedChamp);
        return ChampViewModel.builder()
                .id(savedChamp.getId())
                .nom(savedChamp.getNom())
                .fermeNom(savedChamp.getFerme().getNom())
                .nombreArbres(savedChamp.getArbres() != null ? savedChamp.getArbres().size() : 0)
                .superficie(savedChamp.getSuperficie())
                .build();

    }


}
