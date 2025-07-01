
package Class;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
public class DBConnection {
    
    public static Connection get_connection(){
         Connection connection=null;
         try {
            String url="jdbc:mysql://localhost:3306/hospital_system?serverTimezone=UTC";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(url,"root","");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }}

