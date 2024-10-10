package modelo;

import java.sql.Connection;

import java.sql.DriverManager;

public class conexion {
    private String db = "productoscuy";
    private String url = "jdbc:mysql://localhost:3306/"+db;
    private String user = "root";
    private String pass = "0000";

    Connection conec = null;

    public Connection Conecta(){
        try {
            conec = DriverManager.getConnection(url,user,pass);
            System.out.println("conexion ok");
        }catch (Exception e) {
            System.out.println("Error en la conexion: " + e);
        }
        return conec;
    }

}
