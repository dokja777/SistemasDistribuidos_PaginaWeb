/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Conexion;


import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

/**
 *
 * @author danie
 */
public class Conexion {

    protected Connection con=null;
    String url="jdbc:mysql://localhost:3306/bd_rest";
    String user="root";
    String pass="";
    public Connection Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
        }
        return con;
    }
    
    public Connection Discconet(){    
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error de desconexión.. "+ex.getMessage());
        }
        return null;
    }
    
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        Connection con = conexion.Conexion();
        if (con != null) {
            System.out.println("Conexión exitosa a la base de datos.");
            try {
                con.close(); // Cerrar la conexión después de usarla.
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo establecer conexión a la base de datos.");
        }
    }
}
