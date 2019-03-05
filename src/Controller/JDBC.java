package Controller;

// Descargar Java:                      https://www.java.com/en/download/
// Descargar el Java JDK:               https://www.oracle.com/technetwork/java/javase/downloads/index.html
// Descargar J-Calender:                http://www.toedter.com/download/jcalendar-1.4.zip
// Descargar SQLite JDBC para Java:     https://bitbucket.org/xerial/sqlite-jdbc/downloads/
// Ver Base de Datos                    https://sqliteonline.com

import Model.User;
import Persistence.UserService;
import View.WelcomeForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

/**
 * @author Reynaldo
 */
public class JDBC { //Java DataBase Connectivity
    public static UserService userDB = new UserService(); 
    
    public static Connection getConnection()
    {
        Connection connection = null;   //Para conectarse a un base de datos
      
        try {
            //Realizar conexion con SQLite
           Class.forName("org.sqlite.JDBC");
           connection = DriverManager.getConnection("jdbc:sqlite:user.db");
        } 
        catch (Exception e) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0); // Cerrar pantalla
        }
        
        return connection;
    }
    
    private static String checkData(User user, String rePswd) {
        String msg = "";
        
        if(user.getfName().equals("")) {
            msg = "Introduzca nombre de Usuario";
        }
        else if(user.getPswd().equals("")) {
            msg = "Introduzca Contraseña";
        }
        else if(!user.getPswd().equals(rePswd)) {
            msg = "Introduzca misma Contraseña de nuevo";
        }
        else if(userDB.checkIfUsernameExists(user.getuName())) {
            msg = "Nombre de Usuario ya existe";
        }
        
        return msg;
    }
    
    public static String newUser(String fName, String lName, String uName, String pswd, String rePswd, Date bDate, String address) {
        String msg;
        User newUser = new User(fName, lName, uName, pswd, bDate, address);
        
        msg = checkData(newUser, rePswd);
        
        if(userDB.createUser(newUser)) {
            msg = "Nuevo Usuario Añadido";
        }
        else {
            msg = "No se pudo crear el Usuario";
        }
        
        return msg;
    }
    
    public static String tryLogin(String uName, String pswd) {
        String msg;
        
        if(uName.equals("") || pswd.equals("")) {
            msg = "Introduzca los datos del Usuario";
        }
        else if(userDB.validateUser(uName, pswd)) {
            msg = "Bienvenido!";
        }
        else {
            msg = "Datos Erróneos";
        }
        
        return msg;
    }
    
    public static void loginUser(String uName, WelcomeForm welcomeForm) {
        welcomeForm.showUserInfo(userDB.readDbUserInfo(uName));
    }
    
    public static String updateUser(String fName, String lName, String oldUName, String newUName, String oldPswd, String newPswd, String newRePswd, Date bDate, String address) { 
        if(oldUName.equals("") || oldPswd.equals("")) {
            return("Introduzca antiguo Nombre de Usuario y Contraseña para cambiar datos");
        }
        
        String msg;
        User updatedUser = new User(fName, lName, newUName, newPswd, bDate, address);
        
        msg = checkData(updatedUser, newRePswd);
        
        if(userDB.updateUser(updatedUser, oldUName, oldPswd)) {
            msg = "Usuario Actualizado";
        }
        else {
            msg = "No se pudo actualizar el Usuario";
        }
        
        return msg;
    }
    
    public static String deleteUser(String uName, String pswd) {
        String msg;
        
        if(uName.equals("") || pswd.equals("")) {
            msg = "Introduzca los datos del Usuario";
        }
        else if(userDB.deleteUser(uName, pswd)) {
            msg = "Usuario Borrado";
        }
        else {
            msg = "No se pudo borrar el Usuario";
        }
        
        return msg;
    }
}