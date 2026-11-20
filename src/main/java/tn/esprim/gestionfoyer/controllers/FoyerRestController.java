package tn.esprim.gestionfoyer.controllers;

import tn.esprim.gestionfoyer.entities.Foyer;
import tn.esprim.gestionfoyer.services.IFoyerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Foyer")
@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerRestController {

    @Autowired
    private IFoyerService foyerService;

    //http://localhost:8088/foyer/foyer/retrieve-all-foyers
    @Operation(description = "récupérer tous les foyers de la base de données")
    @GetMapping("/retrieve-all-foyers")
    public List<Foyer> getFoyers() {
        List<Foyer> listFoyers = foyerService.retrieveAllFoyers();
        return listFoyers;
    }

    //http://localhost:8088/foyer/foyer/retrieve-foyer/8
    @Operation(description = "récupérer un foyer par son identifiant")
    @GetMapping("/retrieve-foyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") Long id) {
        Foyer foyer = foyerService.retrieveFoyer(id);
        return foyer;
    }

    //http://localhost:8088/foyer/foyer/add-foyer
    @Operation(description = "ajouter un nouveau foyer")
    @PostMapping("/add-foyer")
    public Foyer addFoyer(@RequestBody Foyer f) {
        Foyer foyer = foyerService.addFoyer(f);
        return foyer;
    }

    //http://localhost:8088/foyer/foyer/remove-foyer/{foyer-id}
    @Operation(description = "supprimer un foyer par son identifiant")
    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") Long id) {
        foyerService.removeFoyer(id);
    }

    //http://localhost:8088/foyer/foyer/modify-foyer
    @Operation(description = "modifier un foyer existant")
    @PutMapping("/modify-foyer")
    public Foyer modifyFoyer(@RequestBody Foyer f) {
        Foyer foyer = foyerService.modifyFoyer(f);
        return foyer;
    }
}

