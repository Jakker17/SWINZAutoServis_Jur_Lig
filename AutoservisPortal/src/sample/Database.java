package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.sound.midi.Soundbank;
import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Database {

    public static Connection getConnection(){
        Connection connection;
        String jdbcURL= "jdbc:derby://localhost:1527/ReservationSystem";
        String username= "admin";
        String password= "admin";
        try {
            connection = DriverManager.getConnection(jdbcURL,username,password);
            return connection;
        } catch (Exception e) {
            System.out.println("problem with connection: "+e);
            return null;
        }
    }

    public static void insertToDb(Connection con, String licencePlate, String name, String surname){
        String sql = "INSERT INTO Reservation(licencePlate,name,surname,dateOfReservation) values ('"+licencePlate +"','"+surname +"','"+name +"' ,CURRENT_TIMESTAMP)";

        Statement myStmt;
        try {
            myStmt = con.createStatement();
            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("problem with adding to db: "+e);
        }

    }

    public static ObservableList<Reservation> getReservations(){
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query= "SELECT * FROM Reservation";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Reservation reservation;
            while (rs.next()){
                reservation = new Reservation(rs.getString("name"),rs.getString("surname"), rs.getString("licencePlate"));
                reservations.add(reservation);
            }
        }
        catch (Exception e){
            System.out.println("problem with getting data from db:" +e);
        }
        return reservations;
    }





}
