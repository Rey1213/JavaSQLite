// Descargar Java:                      https://www.java.com/en/download/
// Descargar el Java JDK:               https://www.oracle.com/technetwork/java/javase/downloads/index.html
// Descargar J-Calender:                http://www.toedter.com/download/jcalendar-1.4.zip
// Descargar SQLite JDBC para Java:     https://bitbucket.org/xerial/sqlite-jdbc/downloads/
// Ver Base de Datos                    https://sqliteonline.com

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
        Connection connection = null;   //Para conectarse a un base de datos
      
        try {
            //Realizar conexion con SQLite
           Class.forName("org.sqlite.JDBC");
           connection = DriverManager.getConnection("jdbc:sqlite:user.db");
           
           // Revisar si existe la Tabla de usuarios
            DatabaseMetaData dbmd = connection.getMetaData();   //Obtener meta-datos de base de datos
            ResultSet tables = dbmd.getTables(null, null, "USERS", null);   //Buscar tabla USERS
            
            if (tables.next()) { //Revisar si hay informacion sobre la tabla USERS
              System.out.println("Base de Datos se pudo abrir");
            }
            else { // Si tabla no existe, se crea
                Statement stmt = connection.createStatement(); //Crear objeto para mandar comandos SQL a el base de datos
                String sql = "CREATE TABLE USERS " +
                    "(ID INTEGER PRIMARY KEY    AUTOINCREMENT," +
                    " FNAME     VARCHAR(20)     NOT NULL, " + 
                    " LNAME     VARCHAR(20), " + 
                    " UNAME     VARCHAR(20)     NOT NULL, " + 
                    " PSWD      VARCHAR(20)     NOT NULL, " + 
                    " BDATE     CHAR(10), " + 
                    " ADDRESS   TEXT)"; 
                stmt.executeUpdate(sql);    //Ejecuta el codigo SQL
                
                stmt.close(); // "Suelta" los datos del Base de Datos
                tables.close();
            }
        } 
        catch (Exception e) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0); // Cerrar pantalla
        }
        
        return connection;
    }
    
    // Revisar si nombre de usuario ya existe
    public static boolean checkUsername(String username)
    {
        Connection connection;
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        
        String query = "SELECT * FROM USERS WHERE UNAME=?";
        
        try {
            connection = MyConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            
            rs = ps.executeQuery(); //Ejecuta el codigo SQL
            
            if(rs.next())
            {
                checkUser = true;
            }
            connection.close();   // "Suelta" los datos del Base de Datos
            //ps se cierra cuando se cierra la conexion "connection"
            rs.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return checkUser;
    }
    
    public static boolean createQuery(String... newUser) {
        int i = 1;
        boolean userAdded = false;
        Connection connection;
        PreparedStatement ps;
        
        String query = "INSERT INTO USERS (FNAME, LNAME, UNAME, PSWD, BDATE, ADDRESS) VALUES (?,?,?,?,?,?)";
        
        try 
        {
            connection = MyConnection.getConnection();
            ps = connection.prepareStatement(query);

            for(String data: newUser) { 
                ps.setString(i, data);
                i++;
            }
            
            if(ps.executeUpdate() > 0)
            {
                connection.close();
                userAdded = true;
            }
        }  
        catch (SQLException ex) 
        {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userAdded;
    }
    
    // Revisar si usuario ya existe
    public static boolean readQuery(String userName, String password) {
        boolean loginSuccess = false;
        Connection connection;
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "SELECT * FROM USERS WHERE UNAME=? AND PSWD=?";
        
        try 
        {
            connection = MyConnection.getConnection();
            ps = connection.prepareStatement(query);

            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                connection.close();
                rs.close();
                loginSuccess = true;
            }
        }  
        catch (SQLException ex) 
        {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return loginSuccess;
    }
    
    // Revisar si usuario ya existe
    public static String[] readQuery(String userName) {
        String[] userInfo = new String[6];
        Connection connection;
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "SELECT * FROM USERS WHERE UNAME=?";
        
        try 
        {
            connection = MyConnection.getConnection();
            ps = connection.prepareStatement(query);

            ps.setString(1, userName);
            rs = ps.executeQuery();
            
            for(int i=0; i<6; i++){
                userInfo[i] = rs.getString(i+2);
            }
            
            connection.close();
            rs.close();
        }  
        catch (SQLException ex) 
        {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userInfo;
    }
    
    public static boolean updateQuery(String... updateUser) {
        int i = 1;
        boolean userUpdated = false;
        Connection connection;
        PreparedStatement ps;
        
        String query = "UPDATE USERS SET FNAME=?, LNAME=?, UNAME=?, PSWD=?, BDATE=?, ADDRESS=? WHERE UNAME=?";
        
        try 
        {
            connection = MyConnection.getConnection();
            ps = connection.prepareStatement(query);

            for(String data: updateUser) { 
                ps.setString(i, data);
                i++;
            }
            
            if(ps.executeUpdate() > 0)
            {
                connection.close();
                userUpdated = true;
            }
        }  
        catch (SQLException ex) 
        {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userUpdated;
    }
    
    public static boolean deleteQuery(String userName) {
        boolean userDeleted = false;
        Connection connection;
        PreparedStatement ps;
        
        String query = "DELETE FROM USERS WHERE UNAME = ?";
        
        try 
        {
            connection = MyConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            
            if(ps.executeUpdate() > 0)
            {
                connection.close();
                userDeleted = true;
            }
        }  
        catch (SQLException ex) 
        {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userDeleted;
    }
    
    //Abrir un formulario
    public static void setupForm(JFrame formulario)
    {
        formulario.setVisible(true);                                //Hacer visible el formulario
        formulario.pack();                                          //Establece dimensiones preferidas del formulario
        formulario.setLocationRelativeTo(null);                     //Centrar formulario en medio de la pantalla
        formulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Operacion cuando pantalla se cierra
    }
}