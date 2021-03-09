package sample.models;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {
    Alert alert;

    public boolean confirmationAlert(String title,String headerText){
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get()==ButtonType.OK)return true;

        else return false;
    }

    public void informationalAlert(String title,String headerText){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void problemAlert(String headerText){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Problem");
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}
