package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.models.Alerts;
import sample.utils.Database;
import sample.models.Reservation;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    public Button btnReserve;
    public DatePicker dpDate;
    public TextField tfName;
    public TextField tfSurname;
    public TextField tfPhoneNumber;
    public TextField tfLicensePlate;
    public DatePicker dpDateGetReservations;
    public Button btnDateGet;
    public TableView<Reservation> tvReservations;
    public TableColumn<Reservation,String> colName;
    public TableColumn<Reservation,String> colSurname;
    public TableColumn<Reservation,String> colLicensePlate;
    public TableColumn<Reservation,String> colTimeOfReservation;
    public TableColumn<Reservation,Integer> colID;
    public TableColumn<Reservation,String> colPhoneNumber;
    public ChoiceBox<String> cbTimePicker;
    public Tab tabReservations;
    public Tab tabNewReservation;

    private TVController tvController= new TVController();
    private Alerts alerts = new Alerts();
    private final ObservableList<String> timeSelectorList = FXCollections.observableArrayList("07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTimePicker.setItems(timeSelectorList);
        dpDateGetReservations.setShowWeekNumbers(true);
        dpDateGetReservations.setValue(LocalDate.now());
        refreshTV(Date.valueOf(LocalDate.now()));
        SingleSelectionModel<Tab> selectionModel = tabReservations.getTabPane().getSelectionModel();
        selectionModel.select(tabNewReservation);
    }

    public void addNewReservation(ActionEvent actionEvent) {
        if (tfLicensePlate.getText().equals("")){
            alerts.problemAlert("License plate cannot be empty.");
        }
        else {
            Database.insertToDb(Database.getConnection(), tfLicensePlate.getText(), tfName.getText(), tfSurname.getText(), Date.valueOf(dpDate.getValue()), tfPhoneNumber.getText(), cbTimePicker.getValue());
            alerts.informationalAlert("Confirmed", "Added new reservation.");
            clear();
            refreshTV(Date.valueOf(LocalDate.now()));
            SingleSelectionModel<Tab> selectionModel = tabReservations.getTabPane().getSelectionModel();
            selectionModel.select(tabNewReservation);
        }
    }

    public void handleReservationShow(ActionEvent actionEvent){
   showReservations(tvController.getReservationsByDate(Database.getReservations(),Date.valueOf(dpDateGetReservations.getValue())));
    }

    public void showReservations(ObservableList<Reservation> reservations){
        colID.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Reservation,String>("customerName"));
        colSurname.setCellValueFactory(new PropertyValueFactory<Reservation,String>("customerSurname"));
        colLicensePlate.setCellValueFactory(new PropertyValueFactory<Reservation,String>("licensePlate"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<Reservation,String>("phoneNumber"));
        colTimeOfReservation.setCellValueFactory(new PropertyValueFactory<Reservation,String>("timeOfReservation"));
        tvReservations.setItems(reservations);
    }

    public void editRow(MouseEvent actionEvent) throws IOException {
        Reservation reservation = tvReservations.getSelectionModel().getSelectedItem();
        showClickedReservation(actionEvent, reservation);
    }

    public void showClickedReservation(MouseEvent event, Reservation reservation) throws IOException {
        if (reservation!=null){
            Stage stage = (Stage) btnDateGet.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/resources/servis_gui_edit.fxml"));
            Parent root = loader.load();

            EditController controller = loader.getController();
            controller.showReservation(reservation);

            Stage newStage  = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("edit Reservation");
            newStage.show();
        }
    }

    public void refreshTV(Date date){
        SingleSelectionModel<Tab> selectionModel = tabReservations.getTabPane().getSelectionModel();
        selectionModel.select(tabReservations);
        dpDateGetReservations.setValue(date.toLocalDate());
        showReservations(tvController.getReservationsByDate(Database.getReservations(),date));
    }

    public void clear(){
        tfName.clear();
        tfSurname.clear();
        tfPhoneNumber.clear();
        tfLicensePlate.clear();
        dpDate.getEditor().clear();
        cbTimePicker.getSelectionModel().clearSelection();
    }
}
