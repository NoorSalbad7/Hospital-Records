/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Class.DBConnection;
import Class.medicine;
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
import javafx.scene.control.Dialog;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NOOR
 */
public class PharmacistController implements Initializable {

    @FXML
    private Tab ManageOrderTab;
    @FXML
    private TextField TextFieldQuantity;
    @FXML
    private Button AddButton;
    @FXML
    private Text errorAddOrderTex;
    @FXML
    private TextField name;
    @FXML
    private Slider slider;
    @FXML
    private TableView<medicine> Table_Order;
    @FXML
    private TableColumn<medicine, Integer> ID;
    @FXML
    private TableColumn<medicine, String> Name;
    @FXML
    private TableColumn<medicine, Integer> Qauntity;
    @FXML
    private TableColumn<medicine, Double> Dose;
    @FXML
    private Button deleteOrd;
    @FXML
    private Button show;
    @FXML
    private Text errSearchText1;
    Statement statment;
    @FXML
    private Button selectPro;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection connection = DBConnection.get_connection();
        try {
            statment = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ID.setCellValueFactory(new PropertyValueFactory("id"));
        Name.setCellValueFactory(new PropertyValueFactory("name"));
        Dose.setCellValueFactory(new PropertyValueFactory("dose"));
        Qauntity.setCellValueFactory(new PropertyValueFactory("quantity"));

    }
//***********************Add Patient*****************

    @FXML
    private void AddMedicine(ActionEvent event) throws SQLException {

        String Name_med = name.getText();
        Integer Quantity_med = Integer.parseInt(TextFieldQuantity.getText());
        double Does_med = slider.getValue();
        if (validate_input(Name_med)) {
            if (Quantity_med >= 0 && Quantity_med < 60) {
                if (Does_med > 0) {
                    String sql = "insert into medicine (name,dose,quantity)"
                            + "values('" + Name_med + "'," + Does_med + "," + Quantity_med + ")";
                    try {

                        int executeUpdate = statment.executeUpdate(sql);
                        if (executeUpdate > 0) {
                            clear_data();
                            System.out.println("affected rows : " + executeUpdate);
                            dialogPopWindow("ADD", " The Add was successful. ☺");
                        }
                    } catch (SQLException r) {
                        System.out.println(r.getMessage());
                    }
                } else {
                    dialogPopWindow("Falied Process", "Enter Medicine Does !!");
                }

            } else {
                dialogPopWindow("Falied Process", "Enter Medicine Quantity !!");
            }
        } else {
            dialogPopWindow("Falied Process", "Enter Medicine Name !!");

        }
    }
//****************** Show Patient**************

    @FXML
    private void show(ActionEvent event) throws SQLException {
        String sql = "select * from medicine";
        ResultSet rs = statment.executeQuery(sql);
        ArrayList<medicine> users_list = new ArrayList<>();
        while (rs.next()) {
            medicine med = new medicine(rs.getInt("id"), rs.getString("name"), rs.getDouble("dose"), rs.getInt("quantity"));
            users_list.add(med);
        }
        Table_Order.getItems().setAll(users_list);
    }

//***************************Delete Patient************************   
    @FXML
    private void delete(ActionEvent event) throws SQLException {
        medicine med = Table_Order.getSelectionModel().getSelectedItem();
        if (med != null) {
            Alert alert2 = new Alert(AlertType.CONFIRMATION);
            alert2.setContentText("Are you sure to delete this client?");
            alert2.setTitle(":<<");
            ButtonType yes = new ButtonType("yes");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            alert2.getButtonTypes().setAll(yes, buttonTypeCancel);
            Optional<ButtonType> result = alert2.showAndWait();
            if (result.get() == yes) {
                String sql = "delete from medicine where id=" + med.getId();
                int executeUpdate = statment.executeUpdate(sql);
                System.out.println("affected rows : " + executeUpdate);
                dialogPuchWindow("Delete", " The Delete was successful. ☺");
            } else {
                dialogPopWindow("Delete", "Error");

            }
        }

    }
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

    private void clear_data() {
        name.setText("");
        TextFieldQuantity.setText("");
        slider.setValue(0);

    }

    private boolean validate_input(String input) {
        return !input.equals("");
    }

    //***************************LogOut*******************************
    @FXML
    private void logoutbut(ActionEvent event) throws FileNotFoundException, IOException {
        Stage stage = (Stage) selectPro.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new UserController());
        Parent root = loader.load(new FileInputStream(new File("C:\\Users\\NOOR\\Documents\\NetBeansProjects\\FinalLab3\\src\\FXML\\User.fxml")));
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

}
