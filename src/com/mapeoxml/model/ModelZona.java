/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapeoxml.model;

import com.mapeoxml.model.persistencia.Presentacion;
import com.mapeoxml.model.persistencia.Zona;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author USER
 */
public class ModelZona {
    public void crearZona(Zona zona) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(zona);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Registro de Zona Correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public void eliminarZona(int id) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Zona zona = (Zona) session.get(Zona.class, id);
            session.delete(zona);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Eliminación de Zona Correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public void modificarZona(int id, String descripcion) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Zona zona = (Zona) session.get(Zona.class, id);
            zona.setDescripcion(descripcion);
            session.update(zona);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Actualizacion de Zona Correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID ingresado no existe");
        } finally {
            session.flush();
            session.close();
        }
    }

    public Zona buscarZona(int id) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Zona zona = (Zona) session.get(Zona.class, id);
            session.getTransaction().commit();

            return zona;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }

    public List<Zona> listarZonas() {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<Zona> zonas = session.createQuery("from Zona").list();
            session.getTransaction().commit();

            return zonas;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }
}
