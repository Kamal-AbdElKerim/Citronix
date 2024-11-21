package org.citronixx.citronix.Controller;

import jakarta.validation.Valid;
import org.citronixx.citronix.Model.SearchDTO.FermeSearchDTO;
import org.citronixx.citronix.Model.entites.Ferme.FermeDTO;
import org.citronixx.citronix.Model.entites.Ferme.Response.ResponseFermeDTO;
import org.citronixx.citronix.Service.FermeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RequestMapping("/api/Ferme")
@RestController
public class FermeController {

    @Autowired
    FermeServiceImpl fermeService;


    @GetMapping("/fermes")
    public ResponseEntity<List<ResponseFermeDTO>> getAllFermes() {
        List<ResponseFermeDTO> ResponseFermeDTOs = fermeService.getAllFermes();
        return ResponseEntity.ok(ResponseFermeDTOs);
    }

    @PostMapping()
    public ResponseEntity<ResponseFermeDTO> createFerme(@RequestBody @Valid FermeDTO fermeDTO) {
        ResponseFermeDTO saveFerme = fermeService.saveFerme(fermeDTO);
        return ResponseEntity.ok(saveFerme);
    }
//
//
    @GetMapping("/{id}")
    public ResponseEntity<ResponseFermeDTO> getFermeById(@PathVariable Long id) {
        ResponseFermeDTO ResponseFermeDTO = fermeService.getFermeById(id);
        if (ResponseFermeDTO != null) {
            return ResponseEntity.ok(ResponseFermeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseFermeDTO> updateFerme(@PathVariable Long id, @RequestBody @Valid FermeDTO fermeDTO) {
        ResponseFermeDTO updatedFerme = fermeService.updateFerme(id, fermeDTO);
        if (updatedFerme != null) {
            return ResponseEntity.ok(updatedFerme);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFerme(@PathVariable Long id) {
        boolean isDeleted = fermeService.deleteFerme(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/search")
    public List<ResponseFermeDTO> searchFermes(@RequestBody FermeSearchDTO searchDTO) {
        return fermeService.searchFermes(searchDTO);
    }

}
