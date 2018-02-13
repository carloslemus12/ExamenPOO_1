/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lemus.carlos.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author MekakuZero
 */
public class Conexion {
    
    // Variables para la conexion
    protected static String BASE = "Guia4_POO1";
    protected static String USUARIO = "root";
    protected static String CLAVE = "";
    
    // Obtenemos la conexion
    public static Connection GetConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/" + BASE, USUARIO, CLAVE);
            return c;
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
}
