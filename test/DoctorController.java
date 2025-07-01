

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author NOOR
 */
public class DoctorController implements Initializable {

    @FXML
    private TabPane TabPane1;
    @FXML
    private Tab AddPatient;
    @FXML
    private TextField TextFieldName;
    @FXML
    private ComboBox<?> ComBoxGender;
    @FXML
    private TextArea problem;
    @FXML
    private Button ButtonAdd;
    @FXML
    private Button ResetButton;
    @FXML
    private Text errorTextAdd;
    @FXML
    private ComboBox<?> ComBoxDoctor_id;
    @FXML
    private DatePicker EntranceDate;
    @FXML
    private TextField TextFieldAge;
    @FXML
    private Button selectPro;
    @FXML
    private Button editPro;
    @FXML
    private Button View_specific;
    @FXML
    private Button searchByCat1;
    @FXML
    private ComboBox<?> ComBoxCategory1;
    @FXML
    private Button viewAllClients;
    @FXML
    private Button selectPro1;
    @FXML
    private Button makeprescription;
    @FXML
    private TableView<?> Product_Table1;
    @FXML
    private TableColumn<?, ?> Pro_Id;
    @FXML
    private TableColumn<?, ?> pro_name;
    @FXML
    private TableColumn<?, ?> pro_Age;
    @FXML
    private TableColumn<?, ?> gender;
    @FXML
    private TableColumn<?, ?> proble;
    @FXML
    private TableColumn<?, ?> entrancedate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reset(ActionEvent event) {
    }
    
}
