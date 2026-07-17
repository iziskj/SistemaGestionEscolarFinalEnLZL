/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controlador.EstudianteController;
import controlador.LoginController;
import modelo.EstudianteDB;
import modelo.UsuarioDB;
import vista.FrmLogin;
import vista.FrmMenu;

/**
 *
 * @author panch
 */
//import java.sql.*;
public class Main {
    public static void main(String[] args){
       FrmLogin ventana = new FrmLogin();
       UsuarioDB usuariodb = new UsuarioDB();
       
       //Crear objeto del controlador
      LoginController controlador = new LoginController(ventana, usuariodb);
       
       //Mostrar ventana en el centro de la pantalla
       ventana.setLocationRelativeTo(null);
       ventana.setVisible(true);
    }
}
