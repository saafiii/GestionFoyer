package tn.esprim.gestionfoyer.repositories;
import tn.esprim.gestionfoyer.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long> {
     Optional <Universite> findBynomUniversite(String nomUniversite);
}
