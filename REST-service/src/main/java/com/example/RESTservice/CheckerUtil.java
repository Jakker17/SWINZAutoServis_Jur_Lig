package com.example.RESTservice;



import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CheckerUtil {

    public LocalDateTime convertorDateAndTimeToLocalDateTime(LocalDate date, String stringTime){
        Time time = java.sql.Time.valueOf(stringTime+":00");
        return LocalDateTime.of(date,time.toLocalTime());
    }

    public boolean checkDate(LocalDateTime localDateTime){
        if (localDateTime.isAfter(LocalDateTime.now())) return true;
        else return false;
    }

    public boolean checkAvailability(LocalDate date, String stringTime,  ArrayList<Reservation> reservations){
        int counter=0;
        for (Reservation reservation:reservations) {
            if (reservation.getDateOfReservation().equals(Date.valueOf(date))){
                if (reservation.getTimeOfReservation().toString().equals(stringTime+":00")) counter++;
            }
        }

        return counter < 2;
    }
}