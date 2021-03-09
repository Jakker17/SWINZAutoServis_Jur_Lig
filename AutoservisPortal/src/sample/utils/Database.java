package sample.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.models.Reservation;


import java.sql.*;

public class Database {

    public static Connection getConnection(){
        Connection connection;

        String jdbcURL= "jdbc:mysql://192.168.0.173:3306/servisreservation";
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

    public static void insertToDb(Connection con, String licencePlate, String name, String surname,Date dateOfReservation,String phoneNumber,String time){
        String sql = "INSERT INTO reservation(name,surname,phoneNumber,licensePlate,dateOfReservation,timeOfReservation) values ('"+name +"','"+surname +"','"+phoneNumber +"','"+licencePlate +"' ,'"+ dateOfReservation+ "','"+java.sql.Time.valueOf(time+":00")+"')";

        Statement myStmt;
        try {
            myStmt = con.createStatement();
            myStmt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            System.out.println("problem with adding to db: "+e);
        }


    }

    public static ObservableList<Reservation> getReservations(){
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query= "SELECT * FROM reservation";
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Reservation reservation;
            while (rs.next()){
                reservation = new Reservation(rs.getInt("id"), rs.getString("name"),rs.getString("surname"),rs.getString("phoneNumber"), rs.getString("licensePlate"), rs.getDate("dateOfReservation"), rs.getTime("timeOfReservation"));
                reservations.add(reservation);
            }
            connection.close();
        }
        catch (Exception e){
            System.out.println("problem with getting data from db:" +e);
        }
        return reservations;
    }

    public static void deleteFromDB(Connection con,int id){
        String sql = "DELETE FROM reservation WHERE id = "+ id;
        Statement myStmt;
        try {
            myStmt = con.createStatement();
            myStmt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            System.out.println("problem with adding to db: "+e);
        }

    }


    public static void update(Connection con, int id, String name, String surname, String phoneNumber, String licensePlate, Date date, String time) {
        String sql ="UPDATE reservation SET name='"+name+"' , surname= '"+surname+" ' ,  phoneNumber='"+ phoneNumber+"' , licensePlate='"+  licensePlate+"' , dateOfReservation='"+date+"' , timeOfReservation='"+java.sql.Time.valueOf(time+":00")+"'  WHERE id='"+id+"'" ;
        Statement myStmt;
        try {
            myStmt = con.createStatement();
            myStmt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            System.out.println("problem with adding to db: "+e);
        }

    }
}
