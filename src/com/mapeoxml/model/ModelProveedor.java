/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapeoxml.model;

import com.mapeoxml.model.persistencia.Proveedor;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author USER
 */
public class ModelProveedor {

    public void crearProveedor(Proveedor proveedor) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(proveedor);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Registro de Proveedor Correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public void eliminarProveedor(int id){
        Session session = null;
        try{
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session =sessionFactory.openSession();
            session.beginTransaction();
            Proveedor proveedor = (Proveedor) session.get(Proveedor.class, id);
            session.delete(proveedor);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Eliminación de Proveedor Correcto");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        finally{
          session.flush();
          session.close();
        }
    }
    public void modificarProveedor(int id,String descripcion){
         Session session = null;
        try{
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session =sessionFactory.openSession();
            session.beginTransaction();
            Proveedor proveedor = (Proveedor) session.get(Proveedor.class, id);
            proveedor.setDescripcion(descripcion);
            session.update(proveedor);
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
    public Proveedor buscarProveedor(int id){
        Session session = null;
        try{
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session =sessionFactory.openSession();
            session.beginTransaction();
            Proveedor proveedor = (Proveedor) session.get(Proveedor.class, id);
            session.getTransaction().commit();
            
            return proveedor;
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
    public List<Proveedor> listarProveedores(){
        Session session = null;
        try{
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session =sessionFactory.openSession();
            session.beginTransaction();
            List<Proveedor> proveedores = session.createQuery("from Proveedor").list();
            session.getTransaction().commit();
            
            return proveedores;
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
