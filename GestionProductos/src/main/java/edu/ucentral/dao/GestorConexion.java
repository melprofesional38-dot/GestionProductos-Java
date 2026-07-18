package edu.ucentral.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorConexion {
    public static Connection obtenerConexion(){
        String host  = "127.0.0.1";
        String basedatos = "productosbd";
        int puerto = 5432;
        String clave = "123456";
        String usuario = "postgres";
        //jdbc jdbc:postgresql://[host]:[puerto]/[bd]
        //String url = "jdbc:postgresql://127.0.0.1:5432/"+basedatos;
        String url = "jdbc:postgresql://"+host+":"+puerto+"/"+basedatos;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, usuario, clave);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
