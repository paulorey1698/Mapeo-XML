/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapeoxml.controller;

import com.mapeoxml.model.ModelMarca;
import com.mapeoxml.model.ModelPresentacion;
import com.mapeoxml.model.ModelProducto;
import com.mapeoxml.model.ModelProveedor;
import com.mapeoxml.model.ModelZona;
import com.mapeoxml.model.persistencia.Marca;
import com.mapeoxml.model.persistencia.Presentacion;
import com.mapeoxml.model.persistencia.Producto;
import com.mapeoxml.model.persistencia.Proveedor;
import com.mapeoxml.model.persistencia.Zona;
import com.mapeoxml.view.ProductoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class ProductoController implements ActionListener {

    private ProductoView pv;
    private ModelProveedor mproveedor = new ModelProveedor();
    private ModelMarca mmarca = new ModelMarca();
    private ModelPresentacion mpresentacion = new ModelPresentacion();
    private ModelZona mzona = new ModelZona();
    private ModelProducto mproducto = new ModelProducto();

    List<Proveedor> p = mproveedor.listarProveedores();
    List<Marca> m = mmarca.listarMarcas();
    List<Presentacion> a = mpresentacion.listarPresentaciones();
    List<Zona> z = mzona.listarZonas();

    public void eventos() {
        pv.btnCrearProducto.addActionListener(this);
        pv.btnModificarProducto.addActionListener(this);
        pv.btnBuscarProducto.addActionListener(this);
        pv.btnEliminarProducto.addActionListener(this);
        pv.proveedores.addActionListener(this);
        pv.presentaciones.addActionListener(this);
        pv.zonas.addActionListener(this);
        pv.marcas.addActionListener(this);
    }
     public void vaciarCampos(){
         pv.txtCodigo.setText("");
        pv.txtDescripcion.setText("");
        pv.txtIva.setText("");
        pv.txtPeso.setText("");
        pv.txtPrecio.setText("");
        pv.txtStock.setText("");
        pv.txtIngresarID.setText("");
    }

    public ProductoController(ProductoView pv) {
        this.pv = pv;
        eventos();
    }

    public void añadir() {

        for (Proveedor proveedor : p) {
            pv.proveedores.addItem(Integer.toString(proveedor.getId_proveedor()));
        }
        for (Marca marca : m) {
            pv.marcas.addItem(Integer.toString(marca.getId_marca()));
        }

        for (Zona zona : z) {
            pv.zonas.addItem(Integer.toString(zona.getId_zona()));
        }
        for (Presentacion presentacion : a) {
            pv.presentaciones.addItem(Integer.toString(presentacion.getId_presentacion()));
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object evt = e.getSource();
        Producto producto = new Producto();
        if (evt.equals(pv.btnCrearProducto)) {
            Marca ma = mmarca.buscarMarca(pv.marcas.getSelectedIndex());
            Presentacion pr = mpresentacion.buscarPresentacion(pv.marcas.getSelectedIndex());
            Proveedor pro = mproveedor.buscarProveedor(pv.proveedores.getSelectedIndex());
            Zona zo = mzona.buscarZona(pv.zonas.getSelectedIndex());

            Integer codigo = Integer.parseInt(pv.txtCodigo.getText());
            String descrip = pv.txtDescripcion.getText();
            Integer precio = Integer.parseInt(pv.txtPrecio.getText());
            Integer stock = Integer.parseInt(pv.txtStock.getText());
            Integer Iva = Integer.parseInt(pv.txtIva.getText());
            Integer peso = Integer.parseInt(pv.txtPeso.getText());

            producto.setMarca(ma);
            producto.setPresentacion(pr);
            producto.setProveedor(pro);
            producto.setZona(zo);
            producto.setCodigo(codigo);
            producto.setDescripcion_producto(descrip);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setIva(Iva);
            producto.setPeso(peso);
            mproducto.crearProducto(producto);
            this.vaciarCampos();
        }
        if (evt.equals(pv.btnEliminarProducto)) {
            int id = Integer.parseInt(pv.txtIngresarID.getText());
            mproducto.eliminarProducto(id);
            this.vaciarCampos();
        }
        if (evt.equals(pv.btnModificarProducto)) {
            int id = Integer.parseInt(pv.txtIngresarID.getText());
            int codigo = Integer.parseInt(pv.txtCodigo.getText());
            String newDes = pv.txtDescripcion.getText();
            double precio = Integer.parseInt(pv.txtPrecio.getText());
            int stock = Integer.parseInt(pv.txtStock.getText());
            double peso = Integer.parseInt(pv.txtPeso.getText());
            int iva = Integer.parseInt(pv.txtIva.getText());
            mproducto.modificarProducto(id, codigo, newDes, precio, stock, peso, iva);
            this.vaciarCampos();
        }
        if (evt.equals(pv.btnBuscarProducto)) {
            int id = Integer.parseInt(pv.txtIngresarID.getText());
        
            Producto p = mproducto.buscarProducto(id);
            try {
                pv.txtCodigo.setText(Integer.toString(p.getCodigo()));
                pv.txtDescripcion.setText(p.getDescripcion_producto());
                pv.txtPrecio.setText(Integer.toString((int) p.getPrecio()));
                pv.txtStock.setText(Integer.toString(p.getStock()));
                pv.txtPeso.setText(Integer.toString((int) p.getPeso()));
                pv.txtIva.setText(Integer.toString(p.getIva()));
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "ID ingresado no existe");
            }
        }
    }
}
