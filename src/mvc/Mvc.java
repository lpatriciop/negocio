/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import controlador.Controlador;
import controlador.ControladorTabla;
import modelo.Modelo;
import modelo.dbModelo;
import vista.Vista;
import vista.frmInsertar;
import vista.frmListaPersonas;

/**
 *
 * @author Patricio
 */
public class Mvc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      //  modelo.Modelo m = new Modelo("Juan", "Perez");
//        modelo.dbModelo m =new dbModelo();
//        vista.frmInsertar v = new frmInsertar();
//        controlador.Controlador c = new Controlador(m, v);
//        c.iniciaControlador();

        modelo.dbModelo m =new modelo.dbModelo();
        vista.frmListaPersonas v = new frmListaPersonas();
        v.setLocationRelativeTo(null);
        controlador.ControladorTabla c = new ControladorTabla(m, v);
        c.cargaLista();
        c.iniciaControlador();
        
    }
    
}










































