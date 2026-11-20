package tn.esprim.gestionfoyer.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprim.gestionfoyer.entities.Bloc;
import tn.esprim.gestionfoyer.entities.Chambre;
import tn.esprim.gestionfoyer.repositories.BlocRepository;
import tn.esprim.gestionfoyer.repositories.ChambreRepository;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService {

    private BlocRepository blocRepository;
    private ChambreRepository chambreRepository;

    @Override
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc retrieveBloc(Long blocId) {
        return blocRepository.findById(blocId)
                .orElse(null); // ou lancer une exception personnalisée
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public void removeBloc(Long blocId) {
        blocRepository.deleteById(blocId);
    }

    @Override
    public Bloc modifyBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Transactional
    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new EntityNotFoundException("Bloc introuvable : " + idBloc));

        List<Chambre> chambres = chambreRepository.findAllByNumeroChambreIn(numChambre);

        if (chambres.size() != new HashSet<>(numChambre).size()) {
            throw new IllegalStateException("une ou plusieurs chambres sont introuvables");
        }
        for (Chambre chambre : chambres) {
            if (chambre.getBloc() !=null && ! chambre.getBloc().getIdBloc().equals(idBloc))
                throw new IllegalStateException("la chmabre"+ chambre.getNumeroChambre()+
                        "est déjà affectée à une autre bloc");
        }
        for (Chambre chambre : chambres) {
            chambre.setBloc(bloc);
        }
        return bloc;
    }}