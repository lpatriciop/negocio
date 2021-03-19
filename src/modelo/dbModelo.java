/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patricio
 */
public class dbModelo extends modelo.Modelo {
    pgConect conecta=new pgConect();

    public dbModelo() {
    }
    
    public dbModelo(String idPersona, String nombre, String apellido) {
        super(idPersona, nombre, apellido);
    }
    public boolean insertar(){
    String nsql="INSERT INTO public.persona(\n" +
"	\"idPersona\", \"Nombres\", \"Apellidos\")\n" +
"	VALUES ('"+getIdPersona()+"', '"+getNombre()+"','"+getApellido()+"' )";
    
    if(conecta.noQuery(nsql)==null){
        return true;
    }else{
        System.out.println("Error");
        return false;
    }
    
    }
     public boolean actualizar(String idp){
//    String nsql="INSERT INTO public.persona(\n" +
//"	\"idPersona\", \"Nombres\", \"Apellidos\")\n" +
//"	VALUES ('"+getIdPersona()+"', '"+getNombre()+"','"+getApellido()+"' )";
    String nsql="UPDATE persona set \"idPersona\"='"+getIdPersona()+"',\"Nombres\"='"+getNombre()+"',\"Apellidos\"='"+getApellido()+"' "
            + "WHERE \"idPersona\"='"+idp+"'";
    if(conecta.noQuery(nsql)==null){
        return true;
    }else{
        System.out.println("Error");
        return false;
    }
    
    }
     public boolean eliminar(String idp){
     String nsql="delete from persona "
         + "WHERE \"idPersona\"='"+idp+"'";
      if(conecta.noQuery(nsql)==null){
        return true;
    }else{
        System.out.println("Error");
        return false;
    }
     }
    public List<Modelo> mostrarDatos(){
    
        try {
            List<Modelo> lista=new ArrayList<Modelo>();
            String sql="select * from persona";
            ResultSet rs=conecta.query(sql);
            while(rs.next()){
                Modelo m = new Modelo();
                m.setIdPersona(rs.getString("idPersona"));
                m.setNombre(rs.getString("Nombres"));
                m.setApellido(rs.getString("Apellidos"));
                lista.add(m);
            }
            rs.close();//cerramos conexion base.
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(dbModelo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    public List<Modelo> mostrarDatos(String aguja){
        try {
            List<Modelo> datosList = new ArrayList<Modelo>();
            String sql="Select * from persona "
                    + "where UPPER(\"idPersona\") like UPPER('%"+aguja+"%') "
                    + "or UPPER(\"Nombres\") like UPPER('%"+aguja+"%')"
                    + "or UPPER(\"Apellidos\") like UPPER('%"+aguja+"%')";
            ResultSet rs= conecta.query(sql);
            System.out.println(sql);
            while(rs.next()){
                Modelo m=new Modelo();
                m.setIdPersona(rs.getString("idPersona"));
                m.setNombre(rs.getString("Nombres"));
                m.setApellido(rs.getString("Apellidos"));
                datosList.add(m);
            }
            rs.close();
            return datosList;
        } catch (SQLException ex) {
            Logger.getLogger(dbModelo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
     public List<Modelo> obtieneDatos(String idp){
        try {
            List<Modelo> datosList = new ArrayList<Modelo>();
            String sql="Select * from persona "
                    + "where UPPER(\"idPersona\") = UPPER('"+idp+"')";
            ResultSet rs= conecta.query(sql);
            System.out.println(sql);
            while(rs.next()){
                Modelo m=new Modelo();
                m.setIdPersona(rs.getString("idPersona"));
                m.setNombre(rs.getString("Nombres"));
                m.setApellido(rs.getString("Apellidos"));
                datosList.add(m);
            }
            rs.close();
            return datosList;
        } catch (SQLException ex) {
            Logger.getLogger(dbModelo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}




















