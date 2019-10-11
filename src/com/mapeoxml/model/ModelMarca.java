/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mapeoxml.model;

import com.mapeoxml.model.persistencia.Marca;
import com.mapeoxml.model.persistencia.Presentacion;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author USER
 */
public class ModelMarca {
    public void crearMarca(Marca marca) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(marca);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Registro de Marca Correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public void eliminarMarca(int id) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Marca marca = (Marca) session.get(Marca.class, id);
            session.delete(marca);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Eliminación de Marca Correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public void modificarMarca(int id, String descripcion) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Marca marca = (Marca) session.get(Marca.class, id);
            marca.setDescripcion(descripcion);
            session.update(marca);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Actualizacion de Marca Correcto");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID ingresado no existe");
        } finally {
            session.flush();
            session.close();
        }
    }

    public Marca buscarMarca(int id) {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            Marca marca = (Marca) session.get(Marca.class, id);
            session.getTransaction().commit();

            return marca;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }

    public List<Marca> listarMarcas() {
        Session session = null;
        try {
            SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<Marca> marcas = session.createQuery("from Marca").list();
            session.getTransaction().commit();

            return marcas;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }
}
