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
public class eliminaPersona {
    final private modelo.dbModelo modelo;
    final private vista.frmInsertar vista;
    private String idp;

    public eliminaPersona(dbModelo modelo, frmInsertar vista, String idp) {
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

        idp=modelo.getIdPersona();
        vista.getTxtIdPersona().setText(modelo.getIdPersona());
        vista.getTxtNombres().setText(modelo.getNombre());
        vista.getTxtApellidos().setText(modelo.getApellido());
        vista.setTitle("Eliminación de Registro");
        vista.getTxtIdPersona().setEditable(false);
        vista.getTxtNombres().setEditable(false);
        vista.getTxtApellidos().setEditable(false);
    }
    public void iniciaControlador(){
    vista.getBtnInsertar().setText("Eliminar");
    vista.getBtnCancelar().addActionListener(e->ocultaVentana());
    vista.getBtnInsertar().addActionListener(e->actualizaPersona());
    
    }
    private void actualizaPersona(){
     modelo.setIdPersona(vista.getTxtIdPersona().getText());
     int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?");
     if (resp==0){
     if(modelo.eliminar(modelo.getIdPersona())){
          JOptionPane.showMessageDialog(null, "Datos eliminados correctamente.");
     }else {
            JOptionPane.showMessageDialog(null, "Error al eliminar.");
     }
    }
    }
    private void ocultaVentana(){
     vista.setVisible(false);
    }
    
}
