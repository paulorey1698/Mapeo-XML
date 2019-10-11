/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapeoxml.controller;

import com.mapeoxml.model.ModelMarca;
import com.mapeoxml.model.persistencia.Marca;
import com.mapeoxml.model.persistencia.Proveedor;
import com.mapeoxml.view.MarcaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class MarcaController implements ActionListener {
    
    private MarcaView mv;
    private ModelMarca mm = new ModelMarca();
    
    public void eventos() {
        mv.btnCrearMarca.addActionListener(this);
        mv.btnModificarMarca.addActionListener(this);
        mv.btnEliminarMarca.addActionListener(this);
        mv.btnBuscarMarca.addActionListener(this);
    }
     public void vaciarCampos(){
        mv.txtDescripcionMarca.setText("");
        mv.txtIngresarID.setText("");
    }
    
    public MarcaController(MarcaView mv) {
        this.mv = mv;
        eventos();
    }

    public void actionPerformed(ActionEvent e) {
        Object evt = e.getSource();
        Marca marca = new Marca();
        if (evt.equals(mv.btnCrearMarca)) {
            String des = mv.txtDescripcionMarca.getText();
            marca.setDescripcion(des);
            mm.crearMarca(marca);
            this.eventos();
        }
        if (evt.equals(mv.btnModificarMarca)) {
            int id = Integer.parseInt(mv.txtIngresarID.getText());
            String newDes = mv.txtDescripcionMarca.getText();
            mm.modificarMarca(id, newDes);
        }
         if (evt.equals(mv.btnEliminarMarca)) {
            int id = Integer.parseInt(mv.txtIngresarID.getText());
            mm.eliminarMarca(id);
            this.eventos();
        }
         if (evt.equals(mv.btnBuscarMarca)) {
            int id = Integer.parseInt(mv.txtIngresarID.getText());
            Marca p=mm.buscarMarca(id);
            try{
            mv.txtDescripcionMarca.setText(p.getDescripcion());
            mv.txtIngresarID.setText(Integer.toString(p.getId_marca()));
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "ID ingresado no existe");
            }
        }
        
    }
    
}
