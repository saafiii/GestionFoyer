package tn.esprim.gestionfoyer.controllers;


import tn.esprim.gestionfoyer.entities.Etudiant;
import tn.esprim.gestionfoyer.services.IEtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Etudiant")
@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {

    @Autowired
    private IEtudiantService etudiantService;

    //http://localhost:8088/foyer/etudiant/retrieve-all-etudiants
    @Operation(description = "récupérer tous les étudiants de la base de données")
    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants() {
        List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiants();
        return listEtudiants;
    }

    //http://localhost:8088/foyer/etudiant/retrieve-etudiant/8
    @Operation(description = "récupérer un étudiant par son identifiant")
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long id) {
        Etudiant etudiant = etudiantService.retrieveEtudiant(id);
        return etudiant;
    }

    //http://localhost:8088/foyer/etudiant/add-etudiant
    @Operation(description = "ajouter un nouvel étudiant")
    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant = etudiantService.addEtudiant(e);
        return etudiant;
    }

    //http://localhost:8088/foyer/etudiant/remove-etudiant/{etudiant-id}
    @Operation(description = "supprimer un étudiant par son identifiant")
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long id) {
        etudiantService.removeEtudiant(id);
    }

    //http://localhost:8088/foyer/etudiant/modify-etudiant
    @Operation(description = "modifier un étudiant existant")
    @PutMapping("/modify-etudiant")
    public Etudiant modifyEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant = etudiantService.modifyEtudiant(e);
        return etudiant;
    }
}
