package org.citronixx.citronix.Controller;

import jakarta.validation.Valid;
import org.citronixx.citronix.Model.entites.Vente.VenteDTO;
import org.citronixx.citronix.Model.entites.Vente.Response.ResponseVenteDTO;
import org.citronixx.citronix.Service.VenteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/Vente")
@RestController
public class VenteController {

    @Autowired
    VenteServiceImpl venteService;

    @GetMapping()
        public ResponseEntity<List<ResponseVenteDTO>> getAllVentes() {
        List<ResponseVenteDTO> responseVenteDTOs = venteService.getAllVentes();
        return ResponseEntity.ok(responseVenteDTOs);
    }

    @PostMapping("/recolte/{recolteId}")
    public ResponseEntity<ResponseVenteDTO> createVenteToRecolte(
            @PathVariable Long recolteId,
            @RequestBody @Valid VenteDTO venteDTO) {
        ResponseVenteDTO savedVente = venteService.saveVente(recolteId , venteDTO);
        return ResponseEntity.ok(savedVente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVenteDTO> getVenteById(@PathVariable Long id) {
        ResponseVenteDTO responseVenteDTO = venteService.getVenteById(id);
        if (responseVenteDTO != null) {
            return ResponseEntity.ok(responseVenteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseVenteDTO> updateVente(@PathVariable Long id, @RequestBody @Valid VenteDTO venteDTO) {
        ResponseVenteDTO updatedVente = venteService.updateVente(id, venteDTO);
        if (updatedVente != null) {
            return ResponseEntity.ok(updatedVente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVente(@PathVariable Long id) {
        boolean isDeleted = venteService.deleteVente(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
