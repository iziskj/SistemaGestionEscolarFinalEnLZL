/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

/**
 *
 * @author panch
 */
import java.sql.*;

public class ConexionDB {
    //Manejo de las constantes de datos de la conexion
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_escolar";
    private static final String USER = "root";
    private static final String PASS = "";
    //Metodo para realizaar la conexion
    public static Connection conexion(){
        Connection conn = null;
        //Manejar el error en la conexión con la base de datos
        try{
            //Guardar conexión en el objeto Connection
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.print("Conexion exitosa");
            
            //priebas
            System.out.println("Cambio en el repositorio local");
        }catch(SQLException e){
            //Manejar el error de SQL
            System.out.print("Error en la conexion: " + e.getMessage());
            
            //pruebas de la conexion al repositorio
            System.out.println("Prueba 1 de GIT");
            
        }
        return conn;
    }
}