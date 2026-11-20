package tn.esprim.gestionfoyer.controllers;
import tn.esprim.gestionfoyer.entities.Reservation;
import tn.esprim.gestionfoyer.services.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Reservation")
@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {

    @Autowired
    private IReservationService reservationService;

    //http://localhost:8088/foyer/reservation/retrieve-all-reservations
    @Operation(description = "récupérer toutes les réservations de la base de données")
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        List<Reservation> listReservations = reservationService.retrieveAllReservations();
        return listReservations;
    }

    //http://localhost:8088/foyer/reservation/retrieve-reservation/8
    @Operation(description = "récupérer une réservation par son identifiant")
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") String id) {
        Reservation reservation = reservationService.retrieveReservation(id);
        return reservation;
    }

    //http://localhost:8088/foyer/reservation/add-reservation
    @Operation(description = "ajouter une nouvelle réservation")
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.addReservation(r);
        return reservation;
    }

    //http://localhost:8088/foyer/reservation/remove-reservation/{reservation-id}
    @Operation(description = "supprimer une réservation par son identifiant")
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") String id) {
        reservationService.removeReservation(id);
    }

    //http://localhost:8088/foyer/reservation/modify-reservation
    @Operation(description = "modifier une réservation existante")
    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.modifyReservation(r);
        return reservation;
    }
}

