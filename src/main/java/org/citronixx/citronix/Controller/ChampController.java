package org.citronixx.citronix.Controller;


import jakarta.validation.Valid;

import org.citronixx.citronix.Model.entites.Champ.ChampDTO;
import org.citronixx.citronix.Model.entites.Champ.Response.ResponseChampDTO;
import org.citronixx.citronix.Service.ChampServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/champs")
@RestController
public class ChampController {

    @Autowired
    private ChampServiceImpl champService;

    // Add a Champ to a Ferme
    @PostMapping("/ferme/{fermeId}")
    public ResponseEntity<ResponseChampDTO> addChampToFerme(
            @PathVariable Long fermeId,
            @RequestBody @Valid ChampDTO champDTO) {
        ResponseChampDTO createdChamp = champService.addChampToFerme(fermeId, champDTO);
        return ResponseEntity.ok(createdChamp);
    }

    // Get all Champs for a specific Ferme
    @GetMapping("/{ChampId}")
    public ResponseEntity<ResponseChampDTO> getChampsById(@PathVariable Long ChampId) {
        ResponseChampDTO champs = champService.getChampById(ChampId);
        return ResponseEntity.ok(champs);
    }


    @GetMapping()
    public ResponseEntity<List<ResponseChampDTO>> getAllChamps() {
        List<ResponseChampDTO> champs = champService.getAllChamps();
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

