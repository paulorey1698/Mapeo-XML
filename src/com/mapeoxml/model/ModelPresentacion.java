/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapeoxml.model;

import com.mapeoxml.model.persistencia.Presentacion;
import com.mapeoxml.model.persistencia.Proveedor;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author USER
 */
public class ModelPresentacion {

    public void crearPresentacion(Presentacion presentacion) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(presentacion);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Registro de Presentacion Correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public void eliminarPresentacion(int id) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Presentacion presentacion = (Presentacion) session.get(Presentacion.class, id);
            session.delete(presentacion);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Eliminación de Presentacion Correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public void modificarPresentacion(int id, String descripcion) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Presentacion presentacion = (Presentacion) session.get(Presentacion.class, id);
            presentacion.setDescripcion(descripcion);
            session.update(presentacion);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Actualizacion de Presentacion Correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID ingresado no existe");
        } finally {
            session.flush();
            session.close();
        }
    }

    public Presentacion buscarPresentacion(int id) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Presentacion presentacion = (Presentacion) session.get(Presentacion.class, id);
            session.getTransaction().commit();

            return presentacion;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }

    public List<Presentacion> listarPresentaciones() {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<Presentacion> presentaciones = session.createQuery("from Presentacion").list();
            session.getTransaction().commit();

            return presentaciones;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }
}
