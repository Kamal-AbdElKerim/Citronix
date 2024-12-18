package org.citronixx.citronix.Controller;


import jakarta.validation.Valid;

import org.citronixx.citronix.Model.entites.Champ.ChampDTO;
import org.citronixx.citronix.Model.entites.Champ.Response.ResponseChampDTO;
import org.citronixx.citronix.Service.ChampServiceImpl;
import org.citronixx.citronix.Service.Interface.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/champs")
@RestController
public class ChampController {

    @Autowired
    private ChampService champService;

    // Add a Champ to a Ferme
    @PostMapping("/ferme/{fermeId}")
    public ResponseEntity<ChampDTO> addChampToFerme(
            @PathVariable Long fermeId,
            @RequestBody @Valid ChampDTO champDTO) {
        ChampDTO createdChamp = champService.addChampToFerme(fermeId, champDTO);
        return ResponseEntity.ok(createdChamp);
    }

    // Get all Champs for a specific Ferme
    @GetMapping("/{ChampId}")
    public ResponseEntity<ChampDTO> getChampsById(@PathVariable Long ChampId) {
        ChampDTO champs = champService.getChampById(ChampId);
        return ResponseEntity.ok(champs);
    }


    @GetMapping()
    public ResponseEntity<List<ChampDTO>> getAllChamps() {
        List<ChampDTO> champs = champService.getAllChamps();
        return ResponseEntity.ok(champs);
    }

  // Update a Champ
    @PutMapping("/{champId}")
    public ResponseEntity<ResponseChampDTO> updateChamp(
            @PathVariable Long champId,
            @RequestBody @Valid ChampDTO champDTO) {
        ResponseChampDTO updatedChamp = champService.updateChamp(champId, champDTO);
        return ResponseEntity.ok(updatedChamp);
    }

   // Delete a Champ
    @DeleteMapping("/{champId}")
    public ResponseEntity<String> deleteChamp(@PathVariable Long champId) {
        String message = champService.deleteChamp(champId);
        return ResponseEntity.ok(message);
    }

}

