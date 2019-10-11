/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapeoxml.model;

import com.mapeoxml.model.persistencia.Producto;
import com.mapeoxml.model.persistencia.Proveedor;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author USER
 */
public class ModelProducto {
     public void crearProducto(Producto producto) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(producto);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Registro de Producto Correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }
    public void eliminarProducto(int id){
        Session session = null;
        try{
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session =sessionFactory.openSession();
            session.beginTransaction();
            Producto producto = (Producto) session.get(Producto.class, id);
            session.delete(producto);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Eliminación de Producto Correcto");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally{
          session.flush();
          session.close();
        }
    }
    public void modificarProducto(int id,int codigo,String descripcion,double precio,int stock,double peso,int iva){
         Session session = null;
        try{
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session =sessionFactory.openSession();
            session.beginTransaction();
            Producto producto = (Producto) session.get(Producto.class, id);
            producto.setCodigo(codigo);
            producto.setDescripcion_producto(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setPeso(peso);
            producto.setIva(iva);
            session.update(producto);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Actualizacion de Proveedor Correcto");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ID ingresado no existe");
        }
        finally{
          session.flush();
          session.close();
        }
    }
    public Producto buscarProducto(int id){
        Session session = null;
        try{
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session =sessionFactory.openSession();
            session.beginTransaction();
            Producto producto = (Producto) session.get(Producto.class, id);
            session.getTransaction().commit();
            
            return producto;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally{
          session.flush();
          session.close();
        }
        return null;
    }
}

