/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

//import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiante;
import modelo.EstudianteDB;
import vista.FrmGestionEstudiantes;
import vista.FrmMenu;

/**
 *
 * @author panch
 */
public class EstudianteController implements ActionListener{
    //Crear objetos para el modelo y la vista
    private FrmMenu ventana;
    private EstudianteDB estudiantedb;
    //Cosntructor para inicializar los objetos
    public EstudianteController(FrmMenu ventana, EstudianteDB estudiantedb){
        this.ventana = ventana;
        this.estudiantedb = estudiantedb;
        
        this.ventana.btnGuardar.addActionListener(this);
        //para el unevo boton de ir a la ventana de gestion
        this.ventana.btnGestion.addActionListener(this);
        
        //Mostrar usuarios cuando se crea la ventana
        mostrarEstudiantes();
    }
    //Metodo obligatorio para la clase abstracta
    //Override es sobrescrivir el código de un método existente (actionPerformed)
    @Override
    public void actionPerformed(ActionEvent e){
        //Validad si el boton fue presionado
        if(e.getSource() == ventana.btnGuardar){
            //Código para guardar los estudiantes
            guardarEstudiantes();
        }
        ///la otra parte para ir a la bentana gestion
        if(e.getSource() == ventana.btnGestion){
            irGestionEstudiantes();
        }
    }
    
    //Método para guardar estudiantes
    private void guardarEstudiantes(){
        //Obtener los datos desde la ventana
        //.trim quita el primer espacio antes de la palabra si es que existe uno por error
        String nombre = ventana.txtNombre.getText().trim(); //Obtenemos el nombre que el usuario va escribir
        String matricula = ventana.txtMatricula.getText().trim();
        int edad = Integer.parseInt(ventana.spnEdad.getValue().toString());
        String carrera = ventana.cmbCarrera.getSelectedItem().toString();
        
        //Crear objeto para el estudiante
        Estudiante estudiante = new Estudiante(nombre, matricula, edad, carrera);
        
        //Enviar el objeto de estudiante antes guardado para que el modelo lo reciba
        boolean resultado = estudiantedb.insertar(estudiante);
        
        //Evaluar si el resultado de la inserción fue exitoso
        if(resultado){
            JOptionPane.showMessageDialog(ventana, "Registro exitoso");
            limpiarCampos();
            mostrarEstudiantes();
        }else{
           JOptionPane.showMessageDialog(ventana, "Error al registrar"); 
        }
    }
    
    //Método para crear la tabla
    private void mostrarEstudiantes(){
        DefaultTableModel modelo = new DefaultTableModel();
        
        //Añadir columnas de la tabla
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Matricula");
        modelo.addColumn("Edad");
        modelo.addColumn("Carrera");
        
        List<Estudiante> listaEstudiantes = estudiantedb.consultarEstudiantes();
        
        //Ciclo para recorrer la lista de estudiantes
        for(Estudiante estudiante : listaEstudiantes){
            
            //Guardar los objetos en un arreglo para usarlos en la tabla
            Object[] fila = {
                estudiante.getId(),
                estudiante.getNombre(),
                estudiante.getMatricula(),
                estudiante.getEdad(),
                estudiante.getCarrera(),
            };
            
            modelo.addRow(fila);
           
        }
        
        //Crear tabla
        JTable tablaEst = new JTable(modelo);
        
        //Usar JScrollPane para mostrar la tabla
        ventana.paneTabla.setViewportView(tablaEst);
    }
    
    //Método para limpiar campos
    private void limpiarCampos(){
        ventana.txtNombre.setText("");
        ventana.txtMatricula.setText("");
        ventana.spnEdad.setValue(0);
        ventana.cmbCarrera.setSelectedIndex(0);
    }
    
    //metodo para ir a la gestion de estudiantes
    private void irGestionEstudiantes(){
        FrmGestionEstudiantes ventanaGestion = new FrmGestionEstudiantes();
            EstudianteDB estudiantedb = new EstudianteDB();
            
            GestionEstudiantesController controladorGestion = 
                    new GestionEstudiantesController(ventanaGestion, estudiantedb);
        ventanaGestion.setLocationRelativeTo(null);
        ventanaGestion.setVisible(true);
        
        //Cerrar la ventana actual
        ventana.dispose();
    }
}
