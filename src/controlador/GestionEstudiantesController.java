/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Estudiante;
import modelo.EstudianteDB;
import vista.FrmGestionEstudiantes;

/**
 *
 * @author panch
 */
public class GestionEstudiantesController implements ActionListener{
    private FrmGestionEstudiantes ventana;
    private EstudianteDB estudiantedb;
    private JTable tabla;
    private int idSeleccionado = -1;
    // Costructor
    public GestionEstudiantesController(FrmGestionEstudiantes ventana, EstudianteDB estudiantedb){
        this.ventana=ventana;
        this.estudiantedb = estudiantedb;
        //botones que tenemos en nuestra ventana gestionestudiantes
        this.ventana.btnActualizar.addActionListener(this);
        this.ventana.btnEliminar.addActionListener(this);
        this.ventana.btnLimpiar.addActionListener(this);
        
        mostrarEstudiantes();
    }    
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == ventana.btnActualizar){
                actualizarEstudiante();
                
            }
            
            if(e.getSource() == ventana.btnEliminar){
                eliminarEstudiantes();
                
            }
            
            if(e.getSource() == ventana.btnLimpiar){
                limpiarCampos();
                
            }
            
            
        }
        //METODO PARA DETECTAR EL CLIC EN LA TABLA
        private void seleccionTabla(){
            tabla.getSelectionModel().addListSelectionListener(evento ->{
                if(evento.getValueIsAdjusting()){
                    //TODO: Agrgar la carga de estudiantes
                    cargarEstudiantes();
                }
            });
        }
        
        //Metodo para cargar los estudiante
        private void cargarEstudiantes(){
            int fila = tabla.getSelectedRow();
            //si no se esta seleccionando nada
            if(fila == -1){
                return;
            }
            //Obtener los valores desde la tabla
            idSeleccionado = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
            String nombre = tabla.getValueAt(fila, 1).toString();
            String matricula = tabla.getValueAt(fila, 2).toString();
            int edad = Integer.parseInt(tabla.getValueAt(fila, 3).toString());
            String carrera = tabla.getValueAt(fila, 4).toString();
            
            //Mostrar los datos al usuario
            ventana.txtNombre1.setText(nombre);
            ventana.txtMatricula1.setText(matricula);
            ventana.spnEdad1.setValue(edad);
            ventana.cmbCarrera1.setSelectedItem(carrera);
        }
        
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
        tabla = new JTable(modelo);
        
        //Usar JScrollPane para mostrar la tabla
        ventana.paneTabla1.setViewportView(tabla);
        
        seleccionTabla();
    }
        
    //Método para actualizar un registro
    private void actualizarEstudiante(){
        //Validar si un ID fue seleccionado
        if(idSeleccionado == -1){
            JOptionPane.showMessageDialog(ventana, "Selecciona un registro");
            return;
        }
        //Obtener los valores desde la ventana
        String nombre = ventana.txtNombre1.getText().trim();
        String matricula = ventana.txtMatricula1.getText().trim();
        int edad = Integer.parseInt(ventana.spnEdad1.getValue().toString());
        String carrera = ventana.cmbCarrera1.getSelectedItem().toString();
        
        if(nombre.isEmpty() || matricula.isEmpty()){
           JOptionPane.showMessageDialog(ventana, "Completa todos los cambios");
           return;
        }
        Estudiante estudiante = new Estudiante(idSeleccionado, nombre, matricula, edad, carrera);
        boolean actualizar = estudiantedb.actualizar(estudiante);
        
        if(actualizar){
            JOptionPane.showMessageDialog(ventana, "Actualización exitosa");
            mostrarEstudiantes();
            limpiarCampos();
        }else{
            JOptionPane.showMessageDialog(ventana, "Error al actualizar");
        }
    }
    
    //Metodo para eliminiar registros
    private void eliminarEstudiantes(){
        //Validar se hay un ID seleccionado
        if(idSeleccionado == -1){
            JOptionPane.showMessageDialog(ventana, "Selecciona un registro");
            return;
        }
        //Hacer confirmación de la eliminación del registro
        int confirmacion = JOptionPane.showConfirmDialog(
                ventana, 
                "¿Deseas eliminar al estudiante?",
                "Confirmar eliminación"
                ,JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        //
        if(confirmacion != JOptionPane.YES_OPTION){
            return;
        }
        boolean eliminacion = estudiantedb.eliminar(idSeleccionado);
        
        if(eliminacion){
            JOptionPane.showMessageDialog(ventana, "Eliminacion exitosa");
            mostrarEstudiantes();
            limpiarCampos();
        }else{
            JOptionPane.showMessageDialog(ventana, "Error al eliminar");
        }
    }
    
    //Método para limpiar campos
    private void limpiarCampos(){
        //Reiniciar el ID en cada actualizacion
        idSeleccionado = -1;
        ventana.txtNombre1.setText("");
        ventana.txtMatricula1.setText("");
        ventana.spnEdad1.setValue(0);
        ventana.cmbCarrera1.setSelectedIndex(0);
        
        if(tabla != null){
            tabla.clearSelection();
        }
    }
   
}


