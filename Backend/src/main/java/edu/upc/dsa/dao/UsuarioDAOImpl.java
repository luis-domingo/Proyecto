package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySession;
import edu.upc.dsa.Session;
import edu.upc.dsa.models.Usuario;

import java.util.HashMap;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO{
    @Override
    public void addUsuario(String nombre, String password) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            Usuario u = new Usuario(nombre, password);
            session.save(u);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }

    @Override
    public Usuario getUsuario(String id) {
        Session session = null;
        Usuario u = null;
        try {
            session = FactorySession.openSession();
            u = (Usuario)session.get(Usuario.class, id);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return u;
    }

    @Override
    public void eliminarUsuario(String id) {
        Usuario u = this.getUsuario(id);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(Usuario.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }
}
