/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Class.DBConnection;
import Class.Patient;
import Class.prescription;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author NOOR
 */
public class DoctorController implements Initializable {

    @FXML
    private TabPane TabPane1;
    @FXML
    private Tab AddProductTab;
    @FXML
    private TextField TextFieldName;
    @FXML
    private Button ButtonAdd;
    @FXML
    private TableView<Patient> Patient_Table;
    @FXML
    private TableColumn<Patient, Integer> Pro_Id_column;
    @FXML
    private TableColumn<Patient, String> pro_name_column;
    @FXML
    private TableColumn<Patient, Integer> pro_Age_column;
    @FXML
    private TableColumn<Patient, String> pro_Date_Column;
    @FXML
    private TableColumn<Patient, String> pro_Problem_Column;
    @FXML
    private TableColumn<Patient, String> pro_Gender_Column;
    @FXML
    private Button button;
    @FXML
    private Tab logOutTab;
    @FXML
    private ComboBox<Integer> ComBoxDoctor;
    @FXML
    private TextField TextFieldAge;
    @FXML
    private TextField txtFieldse;
    @FXML
    private TextArea TextAreaProblem;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton famel;

    Statement statment;
    public ToggleGroup tg;
    @FXML
    private AnchorPane idd;
    @FXML
    private Tab AddSpPatientInf;
    @FXML
    private AnchorPane idd1;
    @FXML
    private ComboBox<Integer> ComBoxDoctor1;
    @FXML
    private Button ButtonAdd1;
    @FXML
    private ComboBox<Integer> ComBoxMedicine;
    @FXML
    private ComboBox<Integer> ComBoxPatient;

    ResultSet rsP = null;
    @FXML
    private VBox pane;
    @FXML
    private Button selectPro1;
    @FXML
    private Button editPro1;
    @FXML
    private TableColumn<prescription, Integer> Medicine_ID;
    @FXML
    private TableColumn<prescription, Integer> Patient_ID;
    @FXML
    private TableColumn<prescription, String> publishedDate;
    @FXML
    private TableView<prescription> Patienttable;
    @FXML
    private TextField txtFieldsearch;
    @FXML
    private Button EditProf;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label age;
    @FXML
    private Label gender;
    @FXML
    private Label date;
    @FXML
    private TextField id2;
    @FXML
    private TextField date2;
    @FXML
    private TextField gender2;
    @FXML
    private TextField name2;
    @FXML
    private TextField age2;
    @FXML
    private TextField txtField;
    @FXML
    private Button b;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection connection = DBConnection.get_connection();
            statment = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            Logger.getLogger(DoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //*******************************
        tg = new ToggleGroup();
        famel.setToggleGroup(tg);
        male.setToggleGroup(tg);
        //***********************************
        //users combobox if doctor 
        try {
            String sqlP = "SELECT id from users where role = 0";
            rsP = statment.executeQuery(sqlP);
            while (rsP.next()) {
                ComBoxDoctor.getItems().addAll(rsP.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //****************************************
        //patient combobox 
        try {
            String smm = "SELECT id from patient";
            rsP = statment.executeQuery(smm);
            while (rsP.next()) {
                ComBoxPatient.getItems().addAll(rsP.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //******************************************
        //medicine combobox      
        try {
            String sqlP = "SELECT id from medicine";
            rsP = statment.executeQuery(sqlP);
            while (rsP.next()) {
                ComBoxMedicine.getItems().addAll(rsP.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //********************************************
        //users combobox if doctor 
        try {
            String sqlP = "SELECT id from users where role = 0";
            rsP = statment.executeQuery(sqlP);
            while (rsP.next()) {
                ComBoxDoctor1.getItems().addAll(rsP.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //************************************
        //patient table..joining columns with attributes
        Pro_Id_column.setCellValueFactory(new PropertyValueFactory("id"));
        pro_name_column.setCellValueFactory(new PropertyValueFactory("name"));
        pro_Age_column.setCellValueFactory(new PropertyValueFactory("age"));
        pro_Gender_Column.setCellValueFactory(new PropertyValueFactory("gender"));
        pro_Date_Column.setCellValueFactory(new PropertyValueFactory("entranceDate"));
        pro_Problem_Column.setCellValueFactory(new PropertyValueFactory("problem"));

        //*****************************************
        //Medicine table..joining columns with attributes  
        Patient_ID.setCellValueFactory(new PropertyValueFactory("patient_id"));
        Medicine_ID.setCellValueFactory(new PropertyValueFactory("medicine_id"));
        publishedDate.setCellValueFactory(new PropertyValueFactory("date"));

//***********************************************
        //set text when double click then enter
        Pro_Id_column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        pro_name_column.setCellFactory(TextFieldTableCell.<Patient>forTableColumn());
        pro_Problem_Column.setCellFactory(TextFieldTableCell.<Patient>forTableColumn());
        pro_Age_column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        pro_Gender_Column.setCellFactory(TextFieldTableCell.<Patient>forTableColumn());

        //comfirm the change
        pro_name_column.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setName(event.getNewValue());

        });
        pro_Problem_Column.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setProblem(event.getNewValue());

        });
        pro_Age_column.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setAge(event.getNewValue());

        });
        pro_Gender_Column.setOnEditCommit(event -> {
            event.getTableView().getItems().
                    get(event.getTablePosition().getRow()).setGender(event.getNewValue());

        });

    }
//***********************Add Patient****************

    @FXML
    private void Add(ActionEvent event) throws SQLException {
        String gender6 = "";
        if (famel.isSelected()) {
            gender6 = "female";
        } else if (male.isSelected()) {
            gender6 = "male";
        }
        String Name = TextFieldName.getText();
        String Age = TextFieldAge.getText();
        String gender7 = gender6;
        String problem = TextAreaProblem.getText();
        Integer doctor_id2 = ComBoxDoctor.getSelectionModel().getSelectedItem();
        if (validate_input(Name)) {
            if (validate_numeric_val(Age)) {
                if (validate_input(gender7)) {
                    if (validate_input(problem)) {
                        if (doctor_id2 != null) {
                            String sql = "insert into patient (name,age,gender,problem,doctor_id,img)"
                                    + "values('" + Name + "'," + Age + ",'" + gender7 + "','" + problem + "', " + doctor_id2 + " ,'" + fName + " ' )";
                            try {
                                int executeUpdate = statment.executeUpdate(sql);
                                if (executeUpdate > 0) {
                                    System.out.println("affected rows : " + executeUpdate);
                                    dialogPuchWindow("ADD", " The Add was successful. ☺");
                                    clear_data();
                                }
                            } catch (SQLException r) {
                                System.out.println(r.getMessage());
                            }
                        } else {
                            dialogPopWindow("Falied Process", "Enter Doctor_ID !!");
                        }
                    } else {
                        dialogPopWindow("Falied Process", "Enter Problem !!");
                    }
                } else {
                    dialogPopWindow("Falied Process", "Enter Gender !!");
                }
            } else {
                dialogPopWindow("Falied Process", "Enter Age !!");
            }
        } else {
            dialogPopWindow("Falied Process", "Enter patient Name !!");
        }
    }

//****************** Show Patient**************
    //show patient in table view
    @FXML
    private void show(ActionEvent event) throws SQLException {
        String sql = "select * from patient where doctor_id = " + UserController.ID;
        ResultSet rsr;
        try {
            rsr = statment.executeQuery(sql);
            ArrayList<Patient> users_list = new ArrayList<>();
            while (rsr.next()) {
                Patient med = new Patient(rsr.getInt("id"), rsr.getString("name"), rsr.getInt("age"), rsr.getString("gender"), rsr.getString("problem"), rsr.getInt("doctor_id"), rsr.getString("entranceDate"));
                users_list.add(med);
            }
            Patient_Table.getItems().setAll(users_list);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//***************************Delete Patient************************
    //delete the selected row

    @FXML
    private void deletText(ActionEvent event) throws SQLException {
        Patient patient = Patient_Table.getSelectionModel().getSelectedItem();
        if (patient != null) {
            Alert alert2 = new Alert(AlertType.CONFIRMATION);
            alert2.setContentText("Are you sure to delete this client?");
            alert2.setTitle("pag Deleted:");
            ButtonType yes = new ButtonType("yes");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            alert2.getButtonTypes().setAll(yes, buttonTypeCancel);
            Optional<ButtonType> result = alert2.showAndWait();
            if (result.get() == yes) {
                String sql = "delete from patient where id= " + patient.getId();
                int executeUpdate = statment.executeUpdate(sql);
                System.out.println("affected rows : " + executeUpdate);
                dialogPuchWindow(":>", "Deleted.");
            } else {
                dialogPopWindow(":>", "Not Deleted.");
            }
        }
    }

//*************************Search Patient**************************
    //search by id and display in table
    @FXML
    private void buttonSearchhandel(ActionEvent event) throws SQLException {
        String input = txtFieldsearch.getText();
        if (!input.isEmpty()) {
            String sql = "select * from patient where id = " + input;
            try {
                ResultSet rsP = statment.executeQuery(sql);
                ArrayList<Patient> list = new ArrayList<>();
                while (rsP.next()) {
                    Patient medw = new Patient(rsP.getInt("id"), rsP.getString("name"), rsP.getInt("age"), rsP.getString("gender"), rsP.getString("problem"), rsP.getInt("doctor_id"), rsP.getString("entranceDate"));
                    list.add(medw);
                }
                if (!list.isEmpty()) {
                    Patient_Table.getItems().setAll(list);
                    txtFieldsearch.setText("");
                    dialogPuchWindow("Search", " The Search was successful. ☺");
                } else {
                    dialogPopWindow("Search", " The Search not successful..!!");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            dialogPuchWindow("Search", "NoT Choice");
        }
    }
//*************************Edit Patient**************************
    //edit the selected row

    @FXML
    private void editProa(ActionEvent event) {
        Patient patien = Patient_Table.getSelectionModel().getSelectedItem();
        if (patien != null) {
            Alert alert2 = new Alert(AlertType.CONFIRMATION);
            alert2.setContentText("Are you sure to Edit this patient?");
            alert2.setTitle("pag Edit:");
            ButtonType yes = new ButtonType("yes");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            alert2.getButtonTypes().setAll(yes, buttonTypeCancel);
            Optional<ButtonType> result = alert2.showAndWait();
            if (result.get() == yes) {
                String sql = "update patient set name= '" + patien.getName() + "', age = " + patien.getAge()
                        + ", problem = ' " + patien.getProblem() + "', gender = '" + patien.getGender() + "' where id = " + patien.getId();
                int executeUpdate;
                try {
                    executeUpdate = statment.executeUpdate(sql);
                    System.out.println("affected rows : " + executeUpdate);
                    dialogPuchWindow("Edit", " The Edit was successful. ☺");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                dialogPuchWindow("Edit", " The Patient Not Edit.!!!");
            }
        }
    }
    //*******************************LogOut***********************************

    @FXML
    private void logoutbut(ActionEvent event) throws FileNotFoundException, IOException {
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new UserController());
        Parent root = loader.load(new FileInputStream(new File("C:\\Users\\NOOR\\Documents\\NetBeansProjects\\FinalLab3\\src\\FXML\\User.fxml")));
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Logout");
        primaryStage.show();
    }

    //****************************Make Prescriptions**************************
    @FXML
    public void Make(ActionEvent event) throws SQLException {
        Integer ComPatient = ComBoxPatient.getSelectionModel().getSelectedItem();
        Integer ComMedicine = ComBoxMedicine.getSelectionModel().getSelectedItem();
        Integer ComDoctor = ComBoxDoctor1.getSelectionModel().getSelectedItem();
        try {
            if (ComPatient != null) {
                if (ComMedicine != null && ComMedicine > 0) {
                    if (ComDoctor != null) {
                        String sql = "insert into prescription (patient_id,medicine_id,doctor_id)"
                                + "values(" + ComPatient + " , " + ComMedicine + " , " + ComDoctor + " )";
                        int executeUpdate = statment.executeUpdate(sql);
                        if (executeUpdate > 0) {
                            clear_data();
                            System.out.println("affected rows : " + executeUpdate);
                            dialogPuchWindow("ADD", " The Add was successful. ☺");
                        }
                    } else {
                        dialogPopWindow("Falied Process", "Error Enter The Doctor!!");
                    }
                } else {
                    dialogPopWindow("Falied Process", "Error Enter The Medicine!!");
                }

            } else {
                dialogPopWindow("Falied Process", "Error Enter The Patient!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //***************************Show prescription history************************   

    @FXML
    private void buttonSear(ActionEvent event) throws SQLException {
        String input = txtFieldse.getText();
        try {
            ResultSet rsP;
            String sql = "SELECT * FROM  prescription  WHERE patient_id =" + input;
            rsP = statment.executeQuery(sql);
            ArrayList<prescription> list = new ArrayList<>();
            while (rsP.next()) {
                prescription med = new prescription(rsP.getInt("patient_id"), rsP.getInt("medicine_id"), rsP.getInt("doctor_id"), rsP.getString("publishedDate"));
                list.add(med);
            }
            if (!list.isEmpty()) {
                Patienttable.getItems().setAll(list);
                txtFieldse.setText("");
                dialogPuchWindow("Search", " The Search was successful. ☺");
            } else {
                dialogPopWindow("Search", " The Search not successful. !!!");
            }
        } catch (SQLException y) {
            JOptionPane.showMessageDialog(null, y);
        }
    }

//************************View a specific patient information******************
    @FXML
    public void Prof() throws SQLException, FileNotFoundException {
        String input = txtField.getText();
        try {
            if (!input.isEmpty()) {
                String sql = "select * from patient where id = " + input + "";
                ResultSet rsn = statment.executeQuery(sql);
                while (rsn.next()) {
                    rsn.first();
                    id2.setText(String.valueOf(rsn.getInt("id")));
                    name2.setText(rsn.getString("name"));
                    age2.setText(String.valueOf(rsn.getInt("age")));
                    gender2.setText(rsn.getString("gender"));
                    date2.setText(rsn.getString("entranceDate"));
                    if (rsn.getString("img") != null) {
                        Image img = new Image(new FileInputStream(rsn.getString("img")));
                        image.setImage(img);
                    }
                    dialogPuchWindow("Search", " The View was successful. ☺");
                }
            } else {
                dialogPopWindow("Search", "The input is empty");
            }
            txtField.setText("");
        } catch (SQLException y) {
            y.printStackTrace();
        }
    }
    //********************************************

    public void dialogPopWindow(String tital, String msg) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(tital);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.setContentText(msg);
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        dialog.show();
    }

    public void dialogPuchWindow(String tital, String msg) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(tital);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType type2 = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(type, type2);
        dialog.setContentText(msg);
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        dialog.show();
    }

//************************************
    private boolean validate_input(String input) {
        return !input.equals("");
    }

    private boolean validate_numeric_val(String input) {
        if (validate_input(input)) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private void clear_data() {
        TextFieldName.setText("");
        if (famel.isSelected()) {
            famel.setSelected(false);
        } else if (male.isSelected()) {
            male.setSelected(false);
        }
        TextFieldAge.setText("");
        TextAreaProblem.setText("");
        ComBoxDoctor.getSelectionModel().clearSelection();
        ComBoxPatient.getSelectionModel().clearSelection();
        ComBoxMedicine.getSelectionModel().clearSelection();
        ComBoxDoctor1.getSelectionModel().clearSelection();

    }

    String fName = "";

    @FXML
    private void addPicBut(ActionEvent event) throws SQLException {
        File selectedFile;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"));
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            fName = selectedFile.getAbsolutePath();
            fName = fName.replace('\\', '/');
        } else {
        }
    }

}
