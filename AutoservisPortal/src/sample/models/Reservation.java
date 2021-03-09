package sample.models;


import java.sql.Date;
import java.sql.Time;

public class Reservation {
    private int id;
    private String customerName;
    private String customerSurname;
    private String licensePlate;
    private Date dateOfReservation;
    private String phoneNumber;
    private Time timeOfReservation;


    public Reservation(int id, String customerName, String customerSurname, String phoneNumber, String licensePlate, Date dateOfReservation, Time timeOfReservation) {
        this.id = id;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.licensePlate = licensePlate;
        this.dateOfReservation = dateOfReservation;
        this.phoneNumber = phoneNumber;
        this.timeOfReservation = timeOfReservation;
    }

    public Time getTimeOfReservation() {
        return timeOfReservation;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDateOfReservation() {
        return dateOfReservation;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}
