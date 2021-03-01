package sample;

public class Reservation {
    private String customerName;
    private String customerSurname;
    private String licensePlate;


    public Reservation(String customerName, String customerSurname, String licensePlate) {
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.licensePlate = licensePlate;
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
