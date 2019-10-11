/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapeoxml.controller;

import com.mapeoxml.model.ModelProveedor;
import com.mapeoxml.model.persistencia.Proveedor;
import com.mapeoxml.view.ProveedorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class ProveedorController implements ActionListener {

    private ProveedorView pv;
    private ModelProveedor mp = new ModelProveedor();

    public void eventos() {
        pv.btnCrearProveedor.addActionListener(this);
        pv.btnModificarProveedor.addActionListener(this);
        pv.btnEliminarProveedor.addActionListener(this);
        pv.btnBuscarProveedor.addActionListener(this);
    }
    public void vaciarCampos(){
        pv.txtDescripcionProveedor.setText("");
        pv.txtIngresarID.setText("");
    }

    public ProveedorController(ProveedorView pv) {
        this.pv = pv;
        eventos();
    }

    public void actionPerformed(ActionEvent e) {
        Object evt = e.getSource();
        Proveedor proveedor = new Proveedor();
        if (evt.equals(pv.btnCrearProveedor)) {

            String des = pv.txtDescripcionProveedor.getText();
            proveedor.setDescripcion(des);
            mp.crearProveedor(proveedor);
            this.vaciarCampos();
        }
        if (evt.equals(pv.btnModificarProveedor)) {
            int id = Integer.parseInt(pv.txtIngresarID.getText());
            String newDes = pv.txtDescripcionProveedor.getText();
            mp.modificarProveedor(id, newDes);
            this.vaciarCampos();
        }
        if (evt.equals(pv.btnEliminarProveedor)) {
            int id = Integer.parseInt(pv.txtIngresarID.getText());
            mp.eliminarProveedor(id);
            this.vaciarCampos();
        }
        if (evt.equals(pv.btnBuscarProveedor)) {
            int id = Integer.parseInt(pv.txtIngresarID.getText());
            
            Proveedor p=mp.buscarProveedor(id);
            try{
            pv.txtDescripcionProveedor.setText(p.getDescripcion());
            pv.txtIngresarID.setText(Integer.toString(p.getId_proveedor()));
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "ID ingresado no existe");
            }
            
        }
    }
}
