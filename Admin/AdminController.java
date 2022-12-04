package Admin;

import com.sun.xml.internal.bind.v2.model.core.ID;
import dbutil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.sql.Connection;

public class AdminController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField ime;
    @FXML
    private TextField prezime;
    @FXML
    private TextField registracija;
    @FXML
    private DatePicker Datum;
    @FXML
    private TableView<KorisnikData> korisniktable;

    @FXML
    private TableColumn<KorisnikData, String> idcolumn;
    @FXML
    private TableColumn<KorisnikData, String> imecolumn;
    @FXML
    private TableColumn<KorisnikData, String> prezimecolumn;
    @FXML
    private TableColumn<KorisnikData, String> registracijacolumn;
    @FXML
    private TableColumn<KorisnikData, String> datumcolumn;

    private dbConnection dc;

    private ObservableList<KorisnikData> data;

    private String sql = "SELECT * FORM korisnik";


    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();
    }

    @FXML
    private void loadKorisnikData(ActionEvent event) throws SQLException{
        try {

            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                this.data.add(new KorisnikData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

            }


        }catch (SQLException e){

            System.err.println("Error " + e);

        }

        this.idcolumn.setCellValueFactory(new PropertyValueFactory<KorisnikData, String>("id"));
        this.imecolumn.setCellValueFactory(new PropertyValueFactory<KorisnikData, String>("ime"));
        this.prezimecolumn.setCellValueFactory(new PropertyValueFactory<KorisnikData, String>("prezime"));
        this.registracijacolumn.setCellValueFactory(new PropertyValueFactory<KorisnikData, String>("registracija"));
        this.datumcolumn.setCellValueFactory(new PropertyValueFactory<KorisnikData, String>("Datum"));

        this.korisniktable.setItems(null);
        this.korisniktable.setItems(this.data);

    }

    @FXML
    private void addKorisnik(ActionEvent event){
        String sqlInsert = "INSERT INTO korisnik(id, fname, lname, registracija, datum) VALUES (?,?,?,?,?)";

        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            stmt.setString(1, this.id.getText());
            stmt.setString(2, this.ime.getText());
            stmt.setString(3, this.prezime.getText());
            stmt.setString(4, this.registracija.getText());
            stmt.setString(5, this.Datum.getEditor().getText());

            stmt.execute();
            conn.close();


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clearFields(ActionEvent event){
        this.id.setText("");
        this.ime.setText("");
        this.prezime.setText("");
        this.registracija.setText("");
        this.Datum.setValue(null);
    }


}
