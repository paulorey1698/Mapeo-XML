/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapeoxml.controller;

import com.mapeoxml.model.ModelPresentacion;
import com.mapeoxml.model.persistencia.Presentacion;
import com.mapeoxml.model.persistencia.Proveedor;
import com.mapeoxml.view.PresentacionView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class PresentacionController implements ActionListener {
    private PresentacionView pv;
    private ModelPresentacion mp = new ModelPresentacion();
    public void eventos(){
        pv.btnCrearPresentacion.addActionListener(this);
        pv.btnEliminarPresentacion.addActionListener(this);
        pv.btnBuscarPresentacion.addActionListener(this);
        pv.btnModificarPresentacion.addActionListener(this);
    }

    public PresentacionController(PresentacionView pv) {
        this.pv=pv;
        eventos();
    }
     public void vaciarCampos(){
        pv.txtDescripcionPresentacion.setText("");
        pv.txtIngresarID.setText("");
    }
    public void actionPerformed(ActionEvent e){
        Object evt = e.getSource();
        Presentacion presentacion = new Presentacion();
        if(evt.equals(pv.btnCrearPresentacion)){
            String des = pv.txtDescripcionPresentacion.getText();
            presentacion.setDescripcion(des);
            mp.crearPresentacion(presentacion);
            this.vaciarCampos();
        }
        if (evt.equals(pv.btnModificarPresentacion)) {
            int id = Integer.parseInt(pv.txtIngresarID.getText());
            String newDes = pv.txtDescripcionPresentacion.getText();
            mp.modificarPresentacion(id, newDes);
            this.vaciarCampos();
        }
        if(evt.equals(pv.btnEliminarPresentacion)){
             int id = Integer.parseInt(pv.txtIngresarID.getText());
            mp.eliminarPresentacion(id);
            this.vaciarCampos();
        }
        if (evt.equals(pv.btnBuscarPresentacion)) {
            int id = Integer.parseInt(pv.txtIngresarID.getText());
            Presentacion p=mp.buscarPresentacion(id);
            try{
            pv.txtDescripcionPresentacion.setText(p.getDescripcion());
            pv.txtIngresarID.setText(Integer.toString(p.getId_presentacion()));
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "ID ingresado no existe");
            }
        }
    }
    
}
