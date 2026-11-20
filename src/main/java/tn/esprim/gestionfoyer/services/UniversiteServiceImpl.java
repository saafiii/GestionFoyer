package tn.esprim.gestionfoyer.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprim.gestionfoyer.entities.Foyer;
import tn.esprim.gestionfoyer.entities.Universite;
import tn.esprim.gestionfoyer.repositories.FoyerRepository;
import tn.esprim.gestionfoyer.repositories.UniversiteRepository;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UniversiteServiceImpl implements IUniversiteService {
    @Autowired  // le rôle est l'injection
    private UniversiteRepository universiteRepository;
    @Autowired
    private FoyerRepository foyerRepository;
    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite retrieveUniversite(Long universiteId) {
        return universiteRepository.findById(universiteId).get();  // get() depuis Optional<Universite>
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public void removeUniversite(Long universiteId) {
        universiteRepository.deleteById(universiteId);
    }

    @Override
    public Universite modifyUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }
    @Transactional
    @Override
    public Universite affecterFoyerUniversite(Long idFoyer, String nomUniversite) {
        Foyer foyer =foyerRepository.findById(idFoyer).orElseThrow(()->
                new EntityNotFoundException("foyer introuvableavec l'Id:"+idFoyer));
        Universite universite = universiteRepository.findBynomUniversite(nomUniversite).orElseThrow(()->new EntityNotFoundException("Universite introuvable avec le nom:"+nomUniversite));
        //verification foyer et universite sont null
        if(foyer.getUniversite()!=null){
            throw new IllegalStateException(
                    "le foyer"+idFoyer+"est deja affecté a une université.");

        }
        if(universite.getFoyer()!=null){
            throw new IllegalStateException("luniversite"+nomUniversite+"a deja un foyer");
        }
        universite.setFoyer(foyer);
        foyer.setUniversite(universite);
        return universite;

    }
    @Transactional
    @Override
    public Universite desaffecterFoyerUniversite(Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new EntityNotFoundException("Université introuvable avec l'id : " + idUniversite));

        Foyer foyer = universite.getFoyer();
        if (foyer == null) {
            throw new IllegalStateException("L'université " + idUniversite + " n'a aucun foyer affecté.");
        }

        foyer.setUniversite(null);
        universite.setFoyer(null);
        return universite;
    }
}
