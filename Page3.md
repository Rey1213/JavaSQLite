# Comandos SQL
![SQL](https://udemy-images.udemy.com/course/750x422/46674_1196_6.jpg)

## Establecer Conexión con DB
Si _user.db_ no existe, se crea.

```java
Connection connection = null;
Class.forName("org.sqlite.JDBC");
connection = DriverManager.getConnection("jdbc:sqlite:user.db");
```	

## Crear Tabla
Para poder usar el _AUTOINCREMENT_ (que autoincrementa el id de cada usuario creado) tenemos que usar el tipo de dato _INTEGER_ en ves del _INT_.

Si no existe una tabla antes de hacer cualquier operación C.R.U.D. (Create, Read, Update, Delete), tendremos problemas.

```java
Statement stmt = connection.createStatement();
String sql = "CREATE TABLE USERS " +
	"(ID	INTEGER	PRIMARY KEY	AUTOINCREMENT," +
	" FNAME		VARCHAR(20)		NOT NULL, " + 
	" LNAME		VARCHAR(20)		NOT NULL, " + 
	" UNAME		VARCHAR(20)		NOT NULL, " + 
	" PSWD		VARCHAR(20)		NOT NULL, " + 
	" BDATE		CHAR(10), " + 
	" ADDRESS	TEXT)";

stmt.executeUpdate(sql); //Ejecuta el codigo SQL
stmt.close(); //"Suelta" los datos del Base de Datos 
```

## Insertar Datos
Datos deben insertarse en el _mismo orden_ que fueron creadas las columnas de la tabla.

```java
String query = "INSERT INTO USERS " + 
	"(FNAME, LNAME, UNAME, PSWD, BDATE, ADDRESS)" + 
	" VALUES (?,?,?,?,?,?)";

PreparedStatement ps = connection.prepareStatement(query);
ps.setString(1, "Nombre");
ps.setString(2, "Apellido");
ps.setString(3, "Nombre de Usuario");
ps.setString(4, "Contraseña");
ps.setString(5, "Fecha de Nacimiento");
ps.setString(6, "Dirección");

ResultSet rs = ps.executeQuery();
if(rs.next()) {	//Revisar si fue exitosa el comando SQL
	connection.close();	//Cierra la conexion con el base de datos
} 
```

## Leer Datos
```java
String query = "SELECT * FROM USERS WHERE UNAME=? AND PSWD=?";

PreparedStatement ps = connection.prepareStatement(query);
ps.setString(1, "Nombre de Usuario");
ps.setString(2, "Contraseña");

ResultSet rs = ps.executeQuery();
if(rs.next()) {
	connection.close();
} 
```

## Actualizar Datos
```java
String query = "UPDATE USERS SET " + 
	"FNAME=?, LNAME=?, UNAME=?, PSWD=?, BDATE=?, ADDRESS=?" + 
	" WHERE UNAME=?";

PreparedStatement ps = connection.prepareStatement(query);
ps.setString(1, "Nombre");
ps.setString(2, "Apellido");
ps.setString(3, "Nuevo Nombre de Usuario");
ps.setString(4, "Contraseña");
ps.setString(5, "Fecha de Nacimiento");
ps.setString(6, "Dirección");
ps.setString(7, "Nombre de Usuario");

ResultSet rs = ps.executeQuery();
if(rs.next()) {
	connection.close();
} 
```

## Borrar Datos
```java
String query = "DELETE FROM USERS WHERE UNAME=?";

PreparedStatement ps = connection.prepareStatement(query);
ps.setString(1, "Nombre de Usuario");

ResultSet rs = ps.executeQuery();
if(rs.next()) {
	connection.close();
} 
```