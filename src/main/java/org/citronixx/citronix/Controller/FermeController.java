package org.citronixx.citronix.Controller;

import jakarta.validation.Valid;
import org.citronixx.citronix.Model.DTO.FermeDTO;
import org.citronixx.citronix.Model.Entity.Ferme;
import org.citronixx.citronix.Model.SearchDTO.FermeSearchDTO;
import org.citronixx.citronix.Model.ViewModel.FermeViewModel;
import org.citronixx.citronix.Service.FermeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Api/Ferme")
@RestController
public class FermeController {

    @Autowired
    FermeServiceImpl fermeService;


    @GetMapping("/fermes")
    public ResponseEntity<List<FermeViewModel>> getAllFermes() {
        List<FermeViewModel> fermeViewModels = fermeService.getAllFermes();
        return ResponseEntity.ok(fermeViewModels);
    }

    @PostMapping()
    public ResponseEntity<FermeViewModel> createFerme(@RequestBody @Valid FermeDTO fermeDTO) {
        FermeViewModel saveFerme = fermeService.saveFerme(fermeDTO);
        return ResponseEntity.ok(saveFerme);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FermeViewModel> getFermeById(@PathVariable Long id) {
        FermeViewModel fermeViewModel = fermeService.getFermeById(id);
        if (fermeViewModel != null) {
            return ResponseEntity.ok(fermeViewModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<FermeViewModel> updateFerme(@PathVariable Long id, @RequestBody @Valid FermeDTO fermeDTO) {
        FermeViewModel updatedFerme = fermeService.updateFerme(id, fermeDTO);
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
    public List<Ferme> searchFermes(@RequestBody FermeSearchDTO searchDTO) {
        return fermeService.searchFermes(searchDTO);
    }

}
