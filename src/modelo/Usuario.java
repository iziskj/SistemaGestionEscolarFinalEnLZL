/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author panch io
 */
public class Usuario {
    private int idUsuario;
    private String usuario;
    private String password;
    private int rol;
    
    //1. Constructor para crear objetos
    public Usuario(){
        
    }
    
    //2. Constructor para enviar a la DB
    public Usuario(int idUsuario, String usuario, String password, int rol){
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
        
    }
    
    //3. 
    public int getIdUsuario(){
        return idUsuario;
        
    }
    public String getUsuario(){
        return usuario;
    }
    
    public String getPassword(){
        return password;
    }
    
     public int getRol(){
        return rol;
    }
    
    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setRol(int rol){
        this.rol = rol;
    }
    
}
