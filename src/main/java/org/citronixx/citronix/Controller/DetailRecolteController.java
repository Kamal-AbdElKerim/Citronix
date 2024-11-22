package org.citronixx.citronix.Controller;

import jakarta.validation.Valid;

import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolte;
import org.citronixx.citronix.Model.entites.DetailRecolte.DetailRecolteDTO;
import org.citronixx.citronix.Model.entites.DetailRecolte.Response.ResponseDetailRecolteDTO;
import org.citronixx.citronix.Service.DetailRecolteServiceImpl;
import org.citronixx.citronix.Service.Interface.DetailRecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/DetailRecolte")
@RestController
public class DetailRecolteController {

    @Autowired
    private DetailRecolteService detailRecolteService;

    // Add a new DetailRecolte to a Champ
    @PostMapping("arbre/{arbreId}/Recolte/{recolteID}")
    public ResponseEntity<ResponseDetailRecolteDTO> addDetailRecolteToChamp(
            @PathVariable Long arbreId,
            @PathVariable Long recolteID,
            @RequestBody @Valid DetailRecolteDTO detailRecolteDTO) {
        ResponseDetailRecolteDTO createdRecolte = detailRecolteService.addDetailRecolteToArbre(recolteID , arbreId, detailRecolteDTO);
        return ResponseEntity.ok(createdRecolte);
    }


    // Get a specific DetailRecolte by its ID
    @GetMapping()
    public ResponseEntity<List<ResponseDetailRecolteDTO>> getAllDetailRecolte() {
        List<ResponseDetailRecolteDTO> recolte = detailRecolteService.getAllDetailRecoltes();
        return ResponseEntity.ok(recolte);
    }

    @GetMapping("/{detailrecolteId}")
    public ResponseEntity<ResponseDetailRecolteDTO> getDetailRecolteById(@PathVariable Long detailrecolteId) {
        ResponseDetailRecolteDTO recolte = detailRecolteService.getDetailRecolteById(detailrecolteId);
        return ResponseEntity.ok(recolte);
    }

    // Update a specific DetailRecolte
    @PutMapping("/{recolteId}")
    public ResponseEntity<ResponseDetailRecolteDTO> updateDetailRecolte(
            @PathVariable Long recolteId,
            @RequestBody @Valid DetailRecolteDTO detailRecolteDTO) {
        ResponseDetailRecolteDTO updatedRecolte = detailRecolteService.updateDetailRecolte(recolteId, detailRecolteDTO);
        return ResponseEntity.ok(updatedRecolte);
    }

    // Delete a specific DetailRecolte
    @DeleteMapping("/{recolteId}")
    public ResponseEntity<String> deleteDetailRecolte(@PathVariable Long recolteId) {
        String message = detailRecolteService.deleteDetailRecolte(recolteId);
        return ResponseEntity.ok(message);
    }
}
