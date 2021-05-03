package com.example.RESTservice;

import java.util.ArrayList;
import java.util.List;

public class ReservationList {
    private List<Reservation> reservations;

    public ReservationList() {
        this.reservations = new ArrayList<>();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
