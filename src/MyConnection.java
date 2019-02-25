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
    
    public static Connection getConnection(){
        
        Connection c = null;
      
        try {
            //Realizar conexion con SQLite
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:test.db");
           
           // Revisar si existe la Tabla de usuarios
            DatabaseMetaData dbm = c.getMetaData();
            
            ResultSet tables = dbm.getTables(null, null, "USERS", null);
            if (tables.next()) {
              System.out.println("Base de Datos se pudo abrir");
            }
            else {
              // Si tabla no existe
                Statement stmt = c.createStatement();
                String sql = "CREATE TABLE USERS " +
                               "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                               " FNAME           TEXT    NOT NULL, " + 
                               " LNAME           TEXT    NOT NULL, " + 
                               " UNAME           TEXT    NOT NULL, " + 
                               " PSWD            TEXT     NOT NULL, " + 
                               " BDATE            CHAR(10), " + 
                               " ADDRESS         TEXT)"; 
                stmt.executeUpdate(sql);
                stmt.close();
            }

           
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        
        return c;
    }
    
    public static void mainMenu(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
        mainMenu.pack();
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Revisar si nombre de usuario ya existe
    public static boolean checkUsername(String username)
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        
        String query = "SELECT * FROM USERS WHERE UNAME =?";
        
        try {
            Connection c = MyConnection.getConnection();
            ps = c.prepareStatement(query);
            ps.setString(1, username);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                checkUser = true;
            }
            c.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
         return checkUser;
    }
}