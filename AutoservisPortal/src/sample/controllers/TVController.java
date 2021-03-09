package sample.controllers;

import javafx.collections.ObservableList;
import sample.models.Reservation;

import java.sql.Date;

public class TVController{

    public ObservableList<Reservation> getReservationsByDate(ObservableList<Reservation> reservations, Date dateOfReservations){
        reservations.removeIf(reservation -> !reservation.getDateOfReservation().equals(dateOfReservations));
        return reservations;
    }

}
