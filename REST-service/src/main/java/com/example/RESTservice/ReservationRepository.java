package com.example.RESTservice;

import java.util.ArrayList;

public class ReservationRepository {
   public static ArrayList<Reservation> getAllReservations(){
        return DatabaseUtil.getReservations();
    }

    public static Reservation getReservationById(int id){
       ArrayList<Reservation> reservations = DatabaseUtil.getReservations();
        for (Reservation r: reservations) {
            if (r.getId()==id)return r;
        }
        return null;
    }

    public static boolean deleteReservation(int id){
        DatabaseUtil.deleteFromDB(DatabaseUtil.getConnection(), id);
        return getReservationById(id) == null;
    }

    public static boolean addReservation(Reservation reservation){
       return DatabaseUtil.insertToDb(reservation);
    }

}
