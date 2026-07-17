/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import conexion.ConexionDB;
//import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author panch
 */
//Clase que permite manejar los objetos del sistema y enviarlos a la BD
public class EstudianteDB {
    //Metodo para registrar en la base de datos
    public boolean insertar(Estudiante estudiante){
        //Crear sentencia SQL
        String sql_query = "INSERT INTO estudiantes(nombre, matricula, edad, carrera) VALUES (?, ?, ?, ?)";
        try{
            //Conexión a la base de datos
            Connection conn = ConexionDB.conexion();
            //Crear el preparedStatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            //Enviar los datos del modelo
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getMatricula());
            stmt.setInt(3, estudiante.getEdad());
            stmt.setString(4, estudiante.getCarrera());
            
            //Ejecutar el query en la base de datos
            stmt.executeUpdate();
            
            //Cerrar Statement y conexión a la DB
            stmt.close();
            conn.close();
            
            return true;
        }catch(SQLException e){
            //En caso de error, notificar al usuario
        
            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }
    
    //Metodo para consultar todos los estudiantes y ya despues seria busqueda por filtros
    public List<Estudiante> consultarEstudiantes(){
        
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        
        String query_sql = "SELECT * FROM estudiantes";
        
        try{
            //Manejar la conexion a la bases de datos
            Connection conn = ConexionDB.conexion();
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            ResultSet result = stmt.executeQuery();
            
            //Ciclo para obtener todos los registros
            while(result.next()){
                
                int id = result.getInt("id_estudiante");//Tiene que coincidir con los nombres de los campos de la base de datos
                String nombre = result.getString("nombre");
                String matricula =  result.getString("matricula");
                int edad = result.getInt("edad");
                String carrera = result.getString("carrera");
                
                //Crear objeto estudiante y guardarlos en la lista
                Estudiante estudiante = new Estudiante(id, nombre, matricula, edad, carrera);
                
                //Guardar el objeto en la lista
                listaEstudiantes.add(estudiante);
                
            }
        }catch(SQLException e){
            System.out.println("Error en consulta: " + e.getMessage());
        }
        
        return listaEstudiantes;
    }
    
    //Metodo para actualizar estududiantes o registros
    public boolean actualizar(Estudiante estudiante){
        String query_sql = "UPDATE estudiantes SET nombre = ?, matricula = ?, edad = ?, carrera = ? WHERE id_estudiante = ? ";
        try{
            //Conectar con la base de datos
            Connection conn = ConexionDB.conexion();
            //Crear el preparedStatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            //Enviar los datos del modelo
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getMatricula());
            stmt.setInt(3, estudiante.getEdad());
            stmt.setString(4, estudiante.getCarrera());
            stmt.setInt(5, estudiante.getId());
            //Verificar el número de filas que cambiaron
            int filas_cambiadas = stmt.executeUpdate();
            //Regresando un valor booleano si hay un cambio en la tabla
            return filas_cambiadas > 0;
        }catch(SQLException e){
            System.out.println("Error al actualizar en la DB" + e.getMessage());
            return false;
        }
    }
   
    //Método para eliminar un registro
    public boolean eliminar(int idEstudiante){
        String query_sql = "DELETE FROM estudiantes WHERE id_estudiante = ?";
        try{
            //Conectar con la base de datos
            Connection conn = ConexionDB.conexion();
            //Crear el preparedStatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            stmt.setInt(1, idEstudiante);
            
            //Valor de la filas afectadas
            int filas_cambiadas = stmt.executeUpdate();
            return filas_cambiadas > 0;
        }catch(SQLException e){
            System.out.println("Error en la eliminación; " + e.getMessage());
            return false;
        }
    }
}
