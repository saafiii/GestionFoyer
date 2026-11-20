package tn.esprim.gestionfoyer.controllers;
import tn.esprim.gestionfoyer.entities.Bloc;
import  tn.esprim.gestionfoyer.services.IBlocService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Bloc")
@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocRestController {

    @Autowired
    private IBlocService blocService;

    //http://localhost:8088/foyer/bloc/retrieve-all-blocs
    @Operation(description = "récupérer tous les blocs de la base de données")
    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getBlocs() {
        List<Bloc> listBlocs = blocService.retrieveAllBlocs();
        return listBlocs;
    }

    //http://localhost:8088/foyer/bloc/retrieve-bloc/8
    @Operation(description = "récupérer un bloc par son identifiant")
    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long id) {
        Bloc bloc = blocService.retrieveBloc(id);
        return bloc;
    }

    //http://localhost:8088/foyer/bloc/add-bloc
    @Operation(description = "ajouter un nouveau bloc")
    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc b) {
        Bloc bloc = blocService.addBloc(b);
        return bloc;
    }

    //http://localhost:8088/foyer/bloc/remove-bloc/{bloc-id}
    @Operation(description = "supprimer un bloc par son identifiant")
    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long id) {
        blocService.removeBloc(id);
    }

    //http://localhost:8088/foyer/bloc/modify-bloc
    @Operation(description = "modifier un bloc existant")
    @PutMapping("/modify-bloc")
    public Bloc modifyBloc(@RequestBody Bloc b) {
        Bloc bloc = blocService.modifyBloc(b);
        return bloc;
    }
    //http://localhost:8088/foyer/bloc/affecterChambres/1
    @Operation(description = "affecter une liste de chambres (par numéro) à un bloc")
    @PostMapping("/affecterChambres/{idBloc}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambre,
                                      @PathVariable("idBloc") long idBloc) {
        return blocService.affecterChambresABloc(numChambre, idBloc);
    }
}
