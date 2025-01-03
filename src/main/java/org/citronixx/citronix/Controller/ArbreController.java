package org.citronixx.citronix.Controller;

import jakarta.validation.Valid;
import org.citronixx.citronix.Model.entites.Arbre.ArbreDTO;
import org.citronixx.citronix.Model.entites.Arbre.Response.ResponseArbreDTO;
import org.citronixx.citronix.Service.ArbreServiceImpl;
import org.citronixx.citronix.Service.Interface.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/arbres")
@RestController
public class ArbreController {

    @Autowired
    private ArbreService arbreService;

    // Add an Arbre to a Champ
    @PostMapping("/champ/{champId}")
    public ResponseEntity<ResponseArbreDTO> addArbreToChamp(
            @PathVariable long champId,
            @RequestBody @Valid ArbreDTO arbreDTO) {
        ResponseArbreDTO createdArbre = arbreService.addArbreToChamp(champId, arbreDTO);
        return ResponseEntity.ok(createdArbre);
    }


    // Get Arbre details by ID
    @GetMapping("/{arbreId}")
    public ResponseEntity<ResponseArbreDTO> getArbreById(@PathVariable Long arbreId) {
        ResponseArbreDTO arbre = arbreService.getArbreById(arbreId);
        return ResponseEntity.ok(arbre);
    }

    // Get all Arbres
    @GetMapping
    public ResponseEntity<List<ResponseArbreDTO>> getAllArbres() {
        List<ResponseArbreDTO> arbres = arbreService.getAllArbres();
        return ResponseEntity.ok(arbres);
    }

    // Update an Arbre
    @PutMapping("/{arbreId}")
    public ResponseEntity<ResponseArbreDTO> updateArbre(
            @PathVariable Long arbreId,
            @RequestBody @Valid ArbreDTO arbreDTO) {
        ResponseArbreDTO updatedArbre = arbreService.updateArbre(arbreId, arbreDTO);
        return ResponseEntity.ok(updatedArbre);
    }

    // Delete an Arbre
    @DeleteMapping("/{arbreId}")
    public ResponseEntity<String> deleteArbre(@PathVariable Long arbreId) {
        String message = arbreService.deleteArbre(arbreId);
        return ResponseEntity.ok(message);
    }

   //  Remove all non-productive Arbres
//    @DeleteMapping("/non-productive")
//    public ResponseEntity<String> removeNonProductiveArbres() {
//        int removedCount = arbreService.removeNonProductiveArbres();
//        return ResponseEntity.ok(removedCount + " arbres non productifs supprimés.");
//    }
}
