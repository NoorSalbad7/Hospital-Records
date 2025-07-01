
package Controllers;


import Class.DBConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.xml.bind.DatatypeConverter;

/**
 * FXML Controller class
 *
 * @author JIT
 */
public class UserController implements Initializable {

    @FXML
    private TextField TextFieldUsername;
    @FXML
    private TextField TextFieldPassword;
    @FXML
    private Button ButtonLogin;
    @FXML
    private Label Text_Label;
    @FXML
    private Text errorText;
    @FXML
    private Button ButtonRegister;
    Statement statment;
    public static String username = "";
    public static String password = "";
    public static int ID;
    Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection connection = DBConnection.get_connection();
        try {
            statment = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //validating name and password
    @FXML
    private void ButtonLogin(ActionEvent event) throws SQLException, FileNotFoundException, IOException, NoSuchAlgorithmException {
        String in_name = TextFieldUsername.getText();
        String in_PassWord = TextFieldPassword.getText();
        if (validate_input(in_name)) {
            if (validate_input(in_PassWord)) {
                String sql = "select name, id, password, role from users where name = '" + in_name + "' and Password = '" + in_PassWord + "'";
                ResultSet rs = statment.executeQuery(sql);
                if (rs.next() == false) {
                    errorText.setText("error in Name or Password");
                } else {
                    ID = rs.getInt("id");
                    if (rs.getInt("role") == 0) {
                        username = rs.getString("name");
                        password = rs.getString("password");
                        stage = (Stage) ButtonRegister.getScene().getWindow();
                        stage.close();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setController(new DoctorController());
                        Parent root = loader.load(new FileInputStream(new File("C:\\Users\\NOOR\\Documents\\NetBeansProjects\\FinalLab3\\src\\FXML\\Doctor.fxml")));
                        Scene scene = new Scene(root);
                        Stage primaryStage = new Stage();
                        primaryStage.setScene(scene);
                        primaryStage.setTitle("Pharmacist");
                        primaryStage.show();

                    } else if (rs.getInt("role") == 1) {
                        username = rs.getString("name");
                        password = rs.getString("password");
                        stage = (Stage) ButtonRegister.getScene().getWindow();
                        stage.close();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setController(new PharmacistController());
                        Parent root = loader.load(new FileInputStream(new File("C:\\Users\\NOOR\\Documents\\NetBeansProjects\\FinalLab3\\src\\FXML\\Pharmacist.fxml")));
                        Scene scene = new Scene(root);
                        Stage primaryStage = new Stage();
                        primaryStage.setScene(scene);
                        primaryStage.setTitle("Pharmacist");
                        primaryStage.show();
                    }
                }
            } else {
                errorText.setText("Enter your password");
            }
        } else {
            errorText.setText("Enter your Name");
        }
    }

    @FXML
    private void ButtonRegister(ActionEvent event) throws FileNotFoundException, IOException {
        System.exit(0);
    }

    private boolean validate_input(String input) {
        return !input.equals("");
    }


    private String hashPass(String pass) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(TextFieldPassword.getText().getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest);
        System.out.println(myHash);
        return myHash;
    }
}
