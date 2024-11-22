package org.citronixx.citronix.Controller;

import jakarta.validation.Valid;
import org.citronixx.citronix.Model.entites.Recolte.RecolteDTO;
import org.citronixx.citronix.Model.entites.Recolte.Response.ResponseRecolteDTO;
import org.citronixx.citronix.Service.Interface.RecolteService;
import org.citronixx.citronix.Service.RecolteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/recoltes")
@RestController
public class RecolteController {

    @Autowired
    private RecolteService recolteService;

    // Add a new Recolte
    @PostMapping("/Champ/{ChampId}")
    public ResponseEntity<ResponseRecolteDTO> addRecolte(
            @RequestBody @Valid RecolteDTO recolteDTO ,
                  @PathVariable Long ChampId) {
        ResponseRecolteDTO createdRecolte = recolteService.addRecolte(ChampId , recolteDTO);
        return ResponseEntity.ok(createdRecolte);
    }

    // Get a specific Recolte by its ID
    @GetMapping("/{recolteId}")
    public ResponseEntity<ResponseRecolteDTO> getRecolteById(@PathVariable Long recolteId) {
        ResponseRecolteDTO recolte = recolteService.getRecolteById(recolteId);
        return ResponseEntity.ok(recolte);
    }

    // Get all Recoltes
    @GetMapping
    public ResponseEntity<List<ResponseRecolteDTO>> getAllRecoltes() {
        List<ResponseRecolteDTO> recoltes = recolteService.getAllRecoltes();
        return ResponseEntity.ok(recoltes);
    }

    // Update a specific Recolte
    @PutMapping("/{recolteId}")
    public ResponseEntity<ResponseRecolteDTO> updateRecolte(
            @PathVariable Long recolteId,
            @RequestBody @Valid RecolteDTO recolteDTO) {
        ResponseRecolteDTO updatedRecolte = recolteService.updateRecolte(recolteId, recolteDTO);
        return ResponseEntity.ok(updatedRecolte);
    }

    // Delete a specific Recolte
    @DeleteMapping("/{recolteId}")
    public ResponseEntity<String> deleteRecolte(@PathVariable Long recolteId) {
        String message = recolteService.deleteRecolte(recolteId);
        return ResponseEntity.ok(message);
    }
}
