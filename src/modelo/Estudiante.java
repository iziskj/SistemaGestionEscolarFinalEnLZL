/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author panch
 */
//Clase para manejar la informcaión del estudiante (sin base de datos)
public class Estudiante {
    /*privado por que no sale de aqui*/
    private int id;  
    private String nombre;
    private String matricula;
    private int edad;
    private String carrera;
    
    //1. Constructor para crear un objeto vació
    //Estudiante estudiante = new Estudiante();
    public Estudiante(){}
    
    //2. Constructor para crear un objeto dentro del sistema (Sin BD)
    //Estudiante estudiante = new Estudiante("cesar", "pvco", 23, "ITI")
    public Estudiante(String nombre, String matricula, int edad, String carrera){
        this.nombre = nombre;
        this.matricula = matricula;
        this.edad = edad;
        this.carrera = carrera;
    }
    
    //3. Contructor para crear un objeto que ocupe la BD
    public Estudiante(int id, String nombre, String matricula, int edad, String carrera){
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
        this.edad = edad;
        this.carrera = carrera;
    }
    
    // USAR GETTERS Y SETTERS PARA LAS OTRA CLASES DEL SISTEMA   getID o obtenerID
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
        
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getMatricula(){
        return matricula;
    }
    
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    
    public int getEdad(){
        return edad;
    }
    
    public void setEdad(int edad){
        this.edad = edad;
    }
    
    public String getCarrera(){
        return carrera;
    }
    
    public void setCarrera(String carrera){
        this.carrera = carrera;
    }
}
