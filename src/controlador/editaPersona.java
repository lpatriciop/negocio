/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.dbModelo;
import vista.frmInsertar;

/**
 *
 * @author Patricio
 */
public class editaPersona {
    final private modelo.dbModelo modelo;
    final private vista.frmInsertar vista;
    private String idp;
   
    public editaPersona(dbModelo modelo, frmInsertar vista, String idp) {
        this.modelo = modelo;
        this.vista = vista;
        this.idp = idp;
        vista.setVisible(true);
    }
    public void iniciaVista(){
        List<modelo.Modelo> rlista=modelo.obtieneDatos(idp);
        modelo.setIdPersona(rlista.get(0).getIdPersona());
        modelo.setNombre(rlista.get(0).getNombre());
        modelo.setApellido(rlista.get(0).getApellido());
        vista.setTitle("Edición de Registro");
        idp=modelo.getIdPersona();
        vista.getTxtIdPersona().setText(modelo.getIdPersona());
        vista.getTxtNombres().setText(modelo.getNombre());
        vista.getTxtApellidos().setText(modelo.getApellido());
    }
    public void iniciaControlador(){
    vista.getBtnInsertar().setText("Actualizar");
    vista.getBtnCancelar().addActionListener(e->ocultaVentana());
    vista.getBtnInsertar().addActionListener(e->actualizaPersona());
    
    }
    private void actualizaPersona(){
     modelo.setIdPersona(vista.getTxtIdPersona().getText());
     modelo.setNombre(vista.getTxtNombres().getText());
     modelo.setApellido(vista.getTxtApellidos().getText());
     if(modelo.actualizar(idp)){
          JOptionPane.showMessageDialog(null, "Datos actualizados correctamente.");
     }else {
            JOptionPane.showMessageDialog(null, "Error al actualizar.");
     }
    }
    private void ocultaVentana(){
     vista.setVisible(false);
    }
}
