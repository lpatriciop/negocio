/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JOptionPane;
import modelo.Modelo;
import modelo.dbModelo;
import vista.Vista;
import vista.frmInsertar;

/**
 *
 * @author Patricio
 */
public class Controlador {
    private modelo.dbModelo modelo;
    private vista.frmInsertar vista;

    public Controlador(dbModelo modelo, frmInsertar vista) {
        this.modelo = modelo;
        this.vista = vista;
      
        vista.setVisible(true);
    }

  
    public void iniciaVista(){
    //    vista.getTxtNombre().setText(modelo.getNombre());
    //    vista.getTxtApellido().setText(modelo.getApellido());
    }
    
    public void iniciaControlador(){
        vista.getBtnInsertar().addActionListener(e->insertaPersona());
        vista.getBtnCancelar().addActionListener(e->ocultaVentana());
//        vista.getBtnGuardar().addActionListener(e->guardaNombre());
//        vista.getBtnRecuperar().addActionListener(e->recuperaNombre());
     }
    private void ocultaVentana(){

    vista.setVisible(false);
    }
    
    private void insertaPersona(){
     modelo.setIdPersona(vista.getTxtIdPersona().getText());
     modelo.setNombre(vista.getTxtNombres().getText());
     modelo.setApellido(vista.getTxtApellidos().getText());
     if (modelo.insertar()){
         JOptionPane.showMessageDialog(null, "Datos grabados correctamente.");
     }else{
        JOptionPane.showMessageDialog(null, "Error al grabar.");
     }
    }
    
    public void guardaNombre(){
//     modelo.setNombre(vista.getTxtNombre().getText());
//        JOptionPane.showMessageDialog(null,"Nombre Guardado " + modelo.getNombre());
    }
    
    public void recuperaNombre(){
//    vista.getTxtNombre().setText(modelo.getNombre());
//    JOptionPane.showMessageDialog(null,"Nombre Recuperado: " + modelo.getNombre());
    }
}




























