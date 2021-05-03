package com.example.RESTservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ReservationController {

    @RequestMapping(value = "/getReservations", method =  RequestMethod.GET)
    public ResponseEntity<ReservationList> getReservations(){
        ArrayList<Reservation> tmp = ReservationRepository.getAllReservations();
        ReservationList reservationList= new ReservationList();
        reservationList.setReservations(ReservationRepository.getAllReservations());
        if (reservationList.getReservations()==null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservationList, HttpStatus.OK);
    }

@GetMapping("/users")
public List< Reservation > getUsers() {
    return ReservationRepository.getAllReservations();
}


    @RequestMapping(value = "/getReservation", method =  RequestMethod.GET)
    public ResponseEntity<Reservation> getReservationById(@RequestParam(value = "id", defaultValue = "0") int id){
        if (ReservationRepository.getReservationById(id)==null) return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(ReservationRepository.getReservationById(id),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteReservation")
    public ResponseEntity deleteReservationById(@RequestParam(value = "id", defaultValue = "0") int id) {
        if (id==0) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        else if (ReservationRepository.deleteReservation(id))return new ResponseEntity(HttpStatus.OK);

    return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/addReservation")
    public ResponseEntity addReservation(@RequestBody Reservation newReservation){
        if (ReservationRepository.addReservation(newReservation)) return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
