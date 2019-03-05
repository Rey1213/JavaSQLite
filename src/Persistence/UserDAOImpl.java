package Persistence;

import Controller.JDBC;
import Model.User;
import View.SignUpForm;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Reynaldo
 */
class UserDAOImpl implements UserDAO { //Sin modificador solo puede ser implementado en paquete "Persistence"
    private final Connection connection = JDBC.getConnection();
    
    public UserDAOImpl() {
        checkIfTableExists();
    }
    
    private void checkIfTableExists() {
        try {
           // Revisar si existe la Tabla de usuarios
            DatabaseMetaData dbmd = connection.getMetaData();   //Obtener meta-datos de base de datos
            ResultSet tables = dbmd.getTables(null, null, "USERS", null);   //Buscar tabla USERS
            
            if (tables.next()) { //Revisar si hay informacion sobre la tabla USERS
              System.out.println("Conexion Exitosa!");
            }
            else { // Si tabla no existe, se crea
                Statement stmt = connection.createStatement(); //Crear objeto para mandar comandos SQL a el base de datos
                String sql = "CREATE TABLE USERS " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
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
        catch(Exception e) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0); // Cerrar pantalla
        }
    }
    
    
    // Revisar si nombre de usuario ya existe
    @Override
    public boolean checkIfUsernameExists(String username) {
        boolean checkUser = false;
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "SELECT * FROM USERS WHERE UNAME=?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            
            rs = ps.executeQuery(); //Ejecuta el codigo SQL
            
            if(rs.next()) {
                checkUser = true;
            }
            rs.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return checkUser;
    }
    
    @Override
    public boolean createUser(User newUser) {
        int i = 1;
        boolean userAdded = false;
        PreparedStatement ps;
        
        String query = "INSERT INTO USERS (FNAME, LNAME, UNAME, PSWD, BDATE, ADDRESS) VALUES (?,?,?,?,?,?)";
        
        try {
            ps = connection.prepareStatement(query);

            for(String data: newUser.getData()) { 
                ps.setString(i, data);
                i++;
            }
            
            if(ps.executeUpdate() > 0) {
                userAdded = true;
            }
        }  
        catch (SQLException ex) {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userAdded;
    }
    
    // Validar Usuario
    @Override
    public boolean validateUser(String userName, String password) {
        boolean loginSuccess = false;
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "SELECT * FROM USERS WHERE UNAME=? AND PSWD=?";
        
        try {
            ps = connection.prepareStatement(query);

            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                rs.close();
                loginSuccess = true;
            }
        }  
        catch (SQLException ex) {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return loginSuccess;
    }
    
    // Obtener los datos del Usuario
    @Override
    public String[] readDbUserInfo(String userName) {
        String[] userInfo = new String[6];
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "SELECT * FROM USERS WHERE UNAME=?";
        
        try {
            ps = connection.prepareStatement(query);

            ps.setString(1, userName);
            rs = ps.executeQuery();
            
            for(int i=0; i<6; i++) {
                userInfo[i] = rs.getString(i+2);
            }
            
            rs.close();
        }  
        catch (SQLException ex) {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userInfo;
    }
    
    @Override
    public boolean updateUser(User updatedUser, String oldUName, String oldPswd) {
        int i = 1;
        boolean userUpdated = false;
        PreparedStatement ps;
        
        String query = "UPDATE USERS SET FNAME=?, LNAME=?, UNAME=?, PSWD=?, BDATE=?, ADDRESS=? WHERE UNAME=? AND PSWD=?";
        
        try {
            ps = connection.prepareStatement(query);

            for(String data: updatedUser.getData()) { 
                ps.setString(i, data);
                i++;
            }
            
            ps.setString(7, oldUName);
            ps.setString(8, oldPswd);
            
            if(ps.executeUpdate() > 0) {
                userUpdated = true;
            }
        }  
        catch (SQLException e) {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return userUpdated;
    }
    
    @Override
    public boolean deleteUser(String userName, String password) {
        boolean userDeleted = false;
        PreparedStatement ps;
        
        String query = "DELETE FROM USERS WHERE UNAME=? AND PSWD=?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, password);
            
            if(ps.executeUpdate() > 0) {
                userDeleted = true;
            }
        }  
        catch (SQLException ex) {
            Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userDeleted;
    }
}