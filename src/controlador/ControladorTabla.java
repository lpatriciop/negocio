/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import modelo.dbModelo;
import modelo.pgConect;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.frmInsertar;
import vista.frmListaPersonas;

/**
 *
 * @author Patricio
 */
public class ControladorTabla {
    final private modelo.dbModelo dbm;
    final private vista.frmListaPersonas v;


    public ControladorTabla(dbModelo dbm, frmListaPersonas v) {
        this.dbm = dbm;
        this.v = v;
        v.setVisible(true);
  
    }
    public void cargaLista(){
        DefaultTableModel modelotabla;
        modelotabla=(DefaultTableModel) v.getTblPersonas().getModel();
        List<modelo.Modelo> lista = dbm.mostrarDatos();
        int columnas=modelotabla.getColumnCount();
        //limpio tabla:
          for(int j=v.getTblPersonas().getRowCount()-1;j>=0;j--){
             modelotabla.removeRow(j);
         }
        for(int i=0;i<lista.size();i++){
            modelotabla.addRow(new Object[columnas]);
            v.getTblPersonas().setValueAt(lista.get(i).getIdPersona(), i, 0);
            v.getTblPersonas().setValueAt(lista.get(i).getNombre(), i, 1);
            v.getTblPersonas().setValueAt(lista.get(i).getApellido(), i, 2);
        }
        v.getLblEstado().setText( lista.size() + " registros cargados.");
    }
        public void buscaIncremental(String aguja){
        System.out.println(aguja);
     
         DefaultTableModel modelotabla;
         modelotabla=(DefaultTableModel) v.getTblPersonas().getModel();
         for(int j=v.getTblPersonas().getRowCount()-1;j>=0;j--){
             modelotabla.removeRow(j);
         }
         List<modelo.Modelo> lista = dbm.mostrarDatos(aguja);
         int columnas=modelotabla.getColumnCount();
        for(int i=0;i<lista.size();i++){
            modelotabla.addRow(new Object[columnas]);
            v.getTblPersonas().setValueAt(lista.get(i).getIdPersona(), i, 0);
            v.getTblPersonas().setValueAt(lista.get(i).getNombre(), i, 1);
            v.getTblPersonas().setValueAt(lista.get(i).getApellido(), i, 2);
        }
                       
    }
    
    
    public void iniciaControlador(){
   
       KeyListener kl=new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
//            buscaIncremental(v.getTxtBuscar().getText());
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyReleased(KeyEvent e) {
      //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      buscaIncremental(v.getTxtBuscar().getText());
        }
    };
     v.getTxtBuscar().addKeyListener(kl);
    
     





//Actualizar:
     v.getBtnActualizar().addActionListener(e->cargaLista());
     v.getBtnNuevo().addActionListener(e->nuevoRegistro());
     v.getBntEditar().addActionListener(e->actualizaRegistro());
     v.getBtnEliminar().addActionListener(e->eliminaRegistro());
     
     v.getBtnImprimir().addActionListener(e->imprimir());
    }
    
private void imprimir(){
        pgConect conecta=new pgConect();
    
        try {
            System.out.println("Imprimiendo...");
            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/vista/jr/rptPersonas.jasper"));
            //Mapa de parametros
            Map<String,Object> params=new HashMap<String, Object>();
            String aguja = v.getTxtBuscar().getText();
            params.put("aguja", "%"+aguja+"%");
            //JasperPrint jp=JasperFillManager.fillReport(jr,null,conecta.getCon());
            JasperPrint jp=JasperFillManager.fillReport(jr, params, conecta.getCon());
            JasperViewer pv=new JasperViewer(jp);
            pv.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(ControladorTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    private void nuevoRegistro(){
        frmInsertar vi =new frmInsertar();
        Controlador c = new Controlador(dbm, vi);
        vi.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); //Ocultamos frame.
      //  vi.setAlwaysOnTop(true);
        vi.setLocationRelativeTo(v);
        c.iniciaControlador();
    }
    
    private void actualizaRegistro(){
    frmInsertar vi=new frmInsertar();
     DefaultTableModel modelotabla;
     modelotabla=(DefaultTableModel) v.getTblPersonas().getModel(); //obtenemos modelo tabla
     String idp=(String) modelotabla.getValueAt(v.getTblPersonas().getSelectedRow(), 0);
    editaPersona c= new editaPersona(dbm, vi,idp);
    vi.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    vi.setLocationRelativeTo(v);
    c.iniciaVista();
    c.iniciaControlador();
    }
    private void eliminaRegistro(){
     frmInsertar vi=new frmInsertar();
     DefaultTableModel modelotabla;
     modelotabla=(DefaultTableModel) v.getTblPersonas().getModel(); //obtenemos modelo tabla
     String idp=(String) modelotabla.getValueAt(v.getTblPersonas().getSelectedRow(), 0);
    eliminaPersona c= new eliminaPersona(dbm, vi,idp);
    vi.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    vi.setLocationRelativeTo(v);
    c.iniciaVista();
    c.iniciaControlador();
    }
}
