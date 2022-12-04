package loginapp;

import Admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import korisnici.korisnikController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbstatus;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<option> comboBox;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginStatus;

    public void initialize(URL url, ResourceBundle rb) {
        if (this.loginModel.isDatabaseConnected()) {
            this.dbstatus.setText("Connected to Database");
        }
        else {
            this.dbstatus.setText("Not Connected to Database");
        }

        this.comboBox.setItems(FXCollections.observableArrayList(option.values()));
    }

    @FXML
    public void Login(ActionEvent event) {

        try {

            if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((option)this.comboBox.getValue()).toString())){
                Stage stage = (Stage)this.loginButton.getScene().getWindow();
                stage.close();
                switch (((option)this.comboBox.getValue()).toString()) {
                    case "Admin":
                        adminLogin();
                        break;
                    case "Korisnik":
                        korisnikLogin();
                        break;
                }
            }

            else {
                this.loginStatus.setText("Wrong Credentials");
            }
        }
        catch (Exception localException) {

        }

    }

    public void korisnikLogin() {
        try {
            Stage korisnikStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(Objects.requireNonNull(getClass().getResource("/korisnici/korisnikFXML.fxml")).openStream());

            korisnikController korisnikController = (korisnici.korisnikController) loader.getController();

            Scene scene = new Scene(root);
            korisnikStage.setScene(scene);
            korisnikStage.setTitle("Korisnik Dashboard");
            korisnikStage.setResizable(false);
            korisnikStage.show();

        }

        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void adminLogin() {

        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane)adminLoader.load(Objects.requireNonNull(getClass().getResource("/Admin/Admin.fxml").openStream()));

            AdminController adminController = (AdminController) adminLoader.getController();

            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
