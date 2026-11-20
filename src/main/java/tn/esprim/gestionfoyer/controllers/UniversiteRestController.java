package tn.esprim.gestionfoyer.controllers;

import tn.esprim.gestionfoyer.entities.Universite;
import tn.esprim.gestionfoyer.services.IUniversiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Universite")
@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {

    @Autowired
    private IUniversiteService universiteService;

    //http://localhost:8088/foyer/universite/retrieve-all-universites
    @Operation(description = "récupérer toutes les universités de la base de données")
    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        List<Universite> listUniversites = universiteService.retrieveAllUniversites();
        return listUniversites;
    }

    //http://localhost:8088/foyer/universite/retrieve-universite/8
    @Operation(description = "récupérer une université par son identifiant")
    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long id) {
        Universite universite = universiteService.retrieveUniversite(id);
        return universite;
    }

    //http://localhost:8088/foyer/universite/add-universite
    @Operation(description = "ajouter une nouvelle université")
    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.addUniversite(u);
        return universite;
    }

    //http://localhost:8088/foyer/universite/remove-universite/{universite-id}
    @Operation(description = "supprimer une université par son identifiant")
    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long id) {
        universiteService.removeUniversite(id);
    }

    //http://localhost:8088/foyer/universite/modify-universite
    @Operation(description = "modifier une université existante")
    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.modifyUniversite(u);
        return universite;
    }

    //http://localhost:8088/foyer/universite/affecterFoyer/id/Nom-uni
    @Operation(description = "affecter un foyer à une université par son nom")
    @PostMapping("/affecterFoyer/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerUniversite(
            @PathVariable("idFoyer") Long idFoyer,
            @PathVariable("nomUniversite") String nomUniversite) {
        return universiteService.affecterFoyerUniversite(idFoyer, nomUniversite);
    }
    //http://localhost:8088/foyer/universite/desaffecterFoyer/id
    @Operation(description = "désaffecter le foyer d'une université par l'id de l'université")
    @PutMapping("/desaffecterFoyer/{idUniversite}")
    public Universite desaffecterFoyerUniversite(
            @PathVariable("idUniversite") Long idUniversite) {
        return universiteService.desaffecterFoyerUniversite(idUniversite);
    }
}


