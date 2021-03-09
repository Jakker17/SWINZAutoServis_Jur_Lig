package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.models.Alerts;
import sample.utils.Database;
import sample.models.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    public DatePicker dpDateEdit;
    public TextField tfNameEdit;
    public TextField tfSurnameEdit;
    public TextField tfLicensePlateEdit;
    public TextField tfPhoneNumberEdit;
    public ChoiceBox<String> cbTimePickerEdit;
    private Reservation reservation;
    public Button btnEdit;
    public Button btnDelete;
    private Alerts alerts = new Alerts();
    private final ObservableList<String> timeSelectorList = FXCollections.observableArrayList("07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTimePickerEdit.setItems(timeSelectorList);
    }

    public void showReservation(Reservation reservation){
        this.reservation = reservation;
        tfLicensePlateEdit.setText(reservation.getLicensePlate());
        tfNameEdit.setText(reservation.getCustomerName());
        tfSurnameEdit.setText(reservation.getCustomerSurname());
        tfPhoneNumberEdit.setText(reservation.getPhoneNumber());
        dpDateEdit.setValue(reservation.getDateOfReservation().toLocalDate());
        cbTimePickerEdit.setValue(reservation.getTimeOfReservation().toString().substring(0,5));

    }

    public void handleButtonClickDelete(ActionEvent actionEvent) throws IOException {
        if (alerts.confirmationAlert("Confirm","Are you sure you want to delete this reservation? ")){
            Database.deleteFromDB(Database.getConnection(),reservation.getId());
            closeWindow();
        }
    }

    public void handleButtonClickEdit(ActionEvent actionEvent) throws IOException {

        Database.update(Database.getConnection(),reservation.getId(),
                        tfNameEdit.getText(),
                        tfSurnameEdit.getText(),
                        tfPhoneNumberEdit.getText(),
                        tfLicensePlateEdit.getText(),
                        Date.valueOf(dpDateEdit.getValue()),
                        cbTimePickerEdit.getValue()
                        );

        alerts.informationalAlert("Edited","Your reservation has been edited.");
        closeWindow();

    }

    public void onClickCloseWithoutSave(ActionEvent actionEvent) throws IOException {
        closeWindow();
    }

    public void closeWindow() throws IOException {
        Date date = reservation.getDateOfReservation();
        Stage stage = (Stage) btnDelete.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/resources/servis_gui.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        Stage newStage  = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
        controller.refreshTV(date);


    }

}
