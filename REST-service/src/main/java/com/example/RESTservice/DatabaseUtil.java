package com.example.RESTservice;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseUtil {

    public static Connection getConnection(){
        Connection connection;

        String jdbcURL= "jdbc:mysql://192.168.0.173:3306/reservation";
        String username= "admin";
        String password= "admin";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,username,password);
            return connection;
        } catch (Exception e) {
            System.out.println("problem with connection: "+e);
            return null;
        }
    }

    public static boolean insertToDb(Reservation reservation){
        Connection con = getConnection();
        String sql = "INSERT INTO reservation(name,surname,phoneNumber,licensePlate,dateOfReservation,timeOfReservation) values " +
                "('"+reservation.getCustomerName() +"','"+reservation.getCustomerSurname() +"','"+reservation.getPhoneNumber() +"','"+reservation.getLicensePlate() +
                "' ,'"+ reservation.getDateOfReservation()+ "','"+java.sql.Time.valueOf(reservation.getTimeOfReservation()+":00")+"')";

        Statement myStmt;
        try {
            myStmt = con.createStatement();
            myStmt.executeUpdate(sql);
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("problem with adding to db: "+e);
            return false;
        }


    }

    public static ArrayList<Reservation> getReservations(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        Connection connection = getConnection();
        String query= "SELECT * FROM reservation";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Reservation reservation;
            while (rs.next()){
                reservation = new Reservation(rs.getInt("id"), rs.getString("name"),rs.getString("surname"),rs.getString("phoneNumber"), rs.getString("licensePlate"), rs.getDate("dateOfReservation"), rs.getString("timeOfReservation"));
                reservations.add(reservation);
            }
            connection.close();
        }
        catch (Exception e){
            System.out.println("problem with getting data from db:" +e);
        }
        return reservations;
    }

    public static boolean deleteFromDB(Connection con,int id){
        String sql = "DELETE FROM reservation WHERE id = "+ id;
        Statement myStmt;
        try {
            myStmt = con.createStatement();
            myStmt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            System.out.println("problem with deleting from db: "+e);
            return false;
        }
        return true;
    }


    public static boolean update(Connection con, int id, String name, String surname, String phoneNumber, String licensePlate, Date date, String time) {
        String sql ="UPDATE reservation SET name='"+name+"' , surname= '"+surname+" ' ,  phoneNumber='"+ phoneNumber+"' , licensePlate='"+  licensePlate+"' , dateOfReservation='"+date+"' , timeOfReservation='"+java.sql.Time.valueOf(time+":00")+"'  WHERE id='"+id+"'" ;
        Statement myStmt;
        try {
            myStmt = con.createStatement();
            myStmt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            System.out.println("problem with adding to db: "+e);
            return false;
        }
        return true;
    }
}
