package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private VBox mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage= primaryStage;
        primaryStage.setTitle("Autoservis Portal");
        showMainView();

    }

    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/sample/resources/servis_gui.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
