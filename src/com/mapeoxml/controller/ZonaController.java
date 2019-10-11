/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapeoxml.controller;

import com.mapeoxml.model.ModelZona;
import com.mapeoxml.model.persistencia.Presentacion;
import com.mapeoxml.model.persistencia.Zona;
import com.mapeoxml.view.ZonaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class ZonaController implements ActionListener {

    private ZonaView zv;
    private ModelZona mz = new ModelZona();

    public void eventos() {
        zv.btnCrearZona.addActionListener(this);
        zv.btnEliminarZona.addActionListener(this);
        zv.btnModificarZona.addActionListener(this);
        zv.btnBuscarZona.addActionListener(this);
    }
     public void vaciarCampos(){
        zv.txtDescripcionZona.setText("");
        zv.txtIngresarID.setText("");
    }

    public ZonaController(ZonaView zv) {
        this.zv = zv;
        eventos();
    }

    public void actionPerformed(ActionEvent e) {
        Object evt = e.getSource();
        Zona zona = new Zona();
        if (evt.equals(zv.btnCrearZona)) {
            String des = zv.txtDescripcionZona.getText();
            zona.setDescripcion(des);
            mz.crearZona(zona);
            this.vaciarCampos();
        }
        if(evt.equals(zv.btnModificarZona)){
             int id = Integer.parseInt(zv.txtIngresarID.getText());
            String newDes = zv.txtDescripcionZona.getText();
            mz.modificarZona(id, newDes);
            this.vaciarCampos();
        }
        if (evt.equals(zv.btnEliminarZona)) {
            int id = Integer.parseInt(zv.txtIngresarID.getText());
            mz.eliminarZona(id);
            this.vaciarCampos();
        }
        if(evt.equals(zv.btnBuscarZona)){
            int id = Integer.parseInt(zv.txtIngresarID.getText());
            Zona p=mz.buscarZona(id);
            try{
            zv.txtDescripcionZona.setText(p.getDescripcion());
            zv.txtIngresarID.setText(Integer.toString(p.getId_zona()));
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "ID ingresado no existe");
            }
        }
    }

}
