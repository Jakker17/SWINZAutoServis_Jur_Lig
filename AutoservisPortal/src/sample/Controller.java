package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller {


    public Button btnReserve;
    public DatePicker dpDate;
    public TextField tfName;
    public TextField tfSurname;
    public TextField tfPhoneNumber;
    public TextField tfLicensePlate;
    public ChoiceBox cbTime;
    public DatePicker dpDateGet;
    public Button btnDateGet;
    public TableView<Reservation> tvReservations;
    public TableColumn<Reservation,String> colName;
    public TableColumn<Reservation,String> colSurname;
    public TableColumn<Reservation,String> colLicensePlate;
    public TableColumn<Reservation,String> colTimeOfReservation;
    private String customerName;
    private String customerPhoneNumber;
    private String customerLicensePlate;
    private String customerSurname;

    public void handleButtonClick(ActionEvent actionEvent) {

        customerName = tfName.getText();
        customerSurname = tfSurname.getText();
        customerPhoneNumber = tfPhoneNumber.getText();
        customerLicensePlate = tfLicensePlate.getText();

        Database.insertToDb(Database.getConnection(),customerLicensePlate,customerName, customerSurname);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmed");
        alert.setContentText("Added to database");
        alert.showAndWait();

        tfName.clear();
        tfSurname.clear();
        tfPhoneNumber.clear();
        tfLicensePlate.clear();
    }

    public void handleReservationShow(ActionEvent actionEvent){
    showReservations(Database.getReservations());
    }

    public void showReservations(ObservableList<Reservation> reservations){
        colName.setCellValueFactory(new PropertyValueFactory<Reservation,String>("customerName"));
        colSurname.setCellValueFactory(new PropertyValueFactory<Reservation,String>("customerSurname"));
        colLicensePlate.setCellValueFactory(new PropertyValueFactory<Reservation,String>("licensePlate"));

        tvReservations.setItems(reservations);

    }
}
