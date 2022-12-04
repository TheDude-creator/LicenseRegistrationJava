package loginapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginApp extends Application {

    public void start(Stage stage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registracija vozila");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
