/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.EstudianteDB;
import modelo.Usuario;
import modelo.UsuarioDB;
import vista.FrmGestionEstudiantes;
import vista.FrmLogin;
import vista.FrmMenu;

/**
 *
 * @author panch
 */
public class LoginController implements ActionListener{
    private FrmLogin ventana;
    private UsuarioDB usuariodb;
    
    public LoginController(FrmLogin ventana, UsuarioDB usuariodb){
        this.ventana = ventana;
        this.usuariodb = usuariodb;
        this.ventana.btnInicio.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == ventana.btnInicio){
            //TODO: Método de incio de sesión
            iniciarSesion();
        }
    }
    
    //Método para iniciar sesión
    private void iniciarSesion(){
        String usuario = ventana.txtUsuario.getText().trim();
        String password = new String(ventana.txtPass.getPassword());
        
        if(usuario.isEmpty() || password.isBlank()){
            JOptionPane.showMessageDialog(ventana, "Ingresa todos los campos");
            return;
        }
        
        Usuario usuarioEncontrado = usuariodb.validarLogin(usuario, password);
        
        //validacion del rol del usuario
        if(usuarioEncontrado != null){
            JOptionPane.showMessageDialog(ventana, "Bienvenido " + usuarioEncontrado.getUsuario()); //getUsuario getRol
            //iniciarSistema();
            if(usuarioEncontrado.getRol() != 1){
                JOptionPane.showMessageDialog(ventana, "No usuario admin");
                iniciarSistema();
            }else{
                iniciarSistemaAdmin();
            }
            
        }else{
            JOptionPane.showMessageDialog(ventana, "Credenciales incorrectas");
        }
    }
    
    //Metodo del inicio des sistema
    private void iniciarSistemaAdmin(){
        //FrmMenu vistaMenu = new FrmMenu();
        FrmGestionEstudiantes vistaMenu = new FrmGestionEstudiantes();
        EstudianteDB estudiantedb = new EstudianteDB();
        
        //EstudianteController controladorRegistro = new EstudianteController(vistaMenu, estudiantedb);
        
        GestionEstudiantesController controlador = new GestionEstudiantesController(vistaMenu, estudiantedb);
        
        
        //Mostrar ventana del registro de estudientes
        vistaMenu.setLocationRelativeTo(null);
        vistaMenu.setVisible(true);
        
        //Quitar la ventana del inicio de sesión
        ventana.dispose();
    }
    
    private void iniciarSistema(){
        //FrmGestionEstudiantes vistaMenu = new FrmGestionEstudiantes();
        FrmMenu vistaMenu = new FrmMenu();
        EstudianteDB estudiantedb = new EstudianteDB();
        
        //GestionEstudiantesController controlador = new GestionEstudiantesController(vistaMenu, estudiantedb);
        EstudianteController controladorRegistro = new EstudianteController(vistaMenu, estudiantedb);
        
        //Mostrar ventana del registro de estudientes
        vistaMenu.setLocationRelativeTo(null);
        vistaMenu.setVisible(true);
        
        //Quitar la ventana del inicio de sesión
        ventana.dispose();
    }
}
