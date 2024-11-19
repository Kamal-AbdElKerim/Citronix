package org.citronixx.citronix.Controller;


import jakarta.validation.Valid;
import org.citronixx.citronix.Model.DTO.ChampDTO;
import org.citronixx.citronix.Model.Entity.Champ;
import org.citronixx.citronix.Model.ViewModel.ChampViewModel;
import org.citronixx.citronix.Service.ChampServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/champs")
@RestController
public class ChampController {

    @Autowired
    private ChampServiceImpl champService;

    // Add a Champ to a Ferme
    @PostMapping("/{fermeId}")
    public ResponseEntity<ChampViewModel> addChampToFerme(
            @PathVariable Long fermeId,
            @RequestBody @Valid ChampDTO champDTO) {
        ChampViewModel createdChamp = champService.addChampToFerme(fermeId, champDTO);
        return ResponseEntity.ok(createdChamp);
    }

    // Get all Champs for a specific Ferme
    @GetMapping("/{ChampId}")
    public ResponseEntity<Optional<ChampViewModel>> getChampsById(@PathVariable Long ChampId) {
        Optional<ChampViewModel> champs = champService.getChampById(ChampId);
        return ResponseEntity.ok(champs);
    }


    @GetMapping()
    public ResponseEntity<List<ChampViewModel>> getAllChamps() {
        List<ChampViewModel> champs = champService.getAllChamps();
        return ResponseEntity.ok(champs);
    }

  // Update a Champ
    @PutMapping("/{champId}")
    public ResponseEntity<ChampViewModel> updateChamp(
            @PathVariable Long champId,
            @RequestBody @Valid ChampDTO champDTO) {
        ChampViewModel updatedChamp = champService.updateChamp(champId, champDTO);
        return ResponseEntity.ok(updatedChamp);
    }

   // Delete a Champ
    @DeleteMapping("/{champId}")
    public ResponseEntity<String> deleteChamp(@PathVariable Long champId) {
        String message = champService.deleteChamp(champId);
        return ResponseEntity.ok(message);
    }

}

