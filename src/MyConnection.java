// Descargar Java:                      https://www.java.com/en/download/
// Descargar el Java JDK:               https://www.oracle.com/technetwork/java/javase/downloads/index.html
// Descargar J-Calender:                http://www.toedter.com/download/jcalendar-1.4.zip
// Descargar SQLite JDBC para Java:     https://bitbucket.org/xerial/sqlite-jdbc/downloads/

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * @author Reynaldo
 */
public class MyConnection {
    
    public static Connection getConnection()
    {
        Connection connection = null;
      
        try {
            //Realizar conexion con SQLite
           Class.forName("org.sqlite.JDBC");
           connection = DriverManager.getConnection("jdbc:sqlite:user.db");
           
           // Revisar si existe la Tabla de usuarios
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet tables = dbmd.getTables(null, null, "USERS", null);
            
            if (tables.next()) {
              System.out.println("Base de Datos se pudo abrir");
            }
            else { // Si tabla no existe, se crea
                Statement stmt = connection.createStatement();
                String sql = "CREATE TABLE USERS " +
                    "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                    " FNAME           TEXT    NOT NULL, " + 
                    " LNAME           TEXT    NOT NULL, " + 
                    " UNAME           TEXT    NOT NULL, " + 
                    " PSWD            TEXT     NOT NULL, " + 
                    " BDATE            CHAR(10), " + 
                    " ADDRESS         TEXT)"; 
                
                stmt.executeUpdate(sql);    //Ejecuta el codigo SQL
                stmt.close(); // "Suelta" los datos del Base de Datos
            }
        } 
        catch (Exception e) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0); // Cerrar pantalla
        }
        
        return connection;
    }
    
    //Abrir un formulario
    public static void setupForm(JFrame formulario)
    {
        formulario.setVisible(true);                                //Hacer visible el formulario
        formulario.pack();                                          //Establece dimensiones preferidas del formulario
        formulario.setLocationRelativeTo(null);                     //Centrar formulario en medio de la pantalla
        formulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Operacion cuando pantalla se cierra
    }
    
    // Revisar si nombre de usuario ya existe
    public static boolean checkUsername(String username)
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        
        String query = "SELECT * FROM USERS WHERE UNAME =?";
        
        try {
            Connection connection = MyConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            
            rs = ps.executeQuery(); //Ejecuta el codigo SQL
            
            if(rs.next())
            {
                checkUser = true;
            }
            connection.close();   // "Suelta" los datos del Base de Datos
        } 
        catch (SQLException ex) {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return checkUser;
    }
}