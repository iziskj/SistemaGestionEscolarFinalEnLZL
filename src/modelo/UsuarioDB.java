/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import conexion.ConexionDB;
import java.sql.*;
/**
 *
 * @author panch
 */
public class UsuarioDB {
    //MÉTODO PARA INICIAR SESIÓN
    public Usuario validarLogin(String usuario, String password){
        String query_sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
        try{
           Connection conn = ConexionDB.conexion();
           PreparedStatement stmt= conn.prepareStatement(query_sql);
           
           stmt.setString(1, usuario);
           stmt.setString(2, password);
           
           ResultSet result = stmt.executeQuery();
           
           if(result.next()){
               //Obtener la ainformación desde la DB y gurdar en un objeto
               Usuario usuarioEncontrado = new Usuario(
                       result.getInt("id_usuario"),
                       result.getString("usuario"),
                       result.getString("passwword"),
                       result.getInt("rol")
               );
               return usuarioEncontrado;
           }
                   
        }catch(SQLException e){
            System.out.println("Eror al consultar en la DB" + e.getMessage());
        }
        return null;
    }
}
