package edu.upc.dsa.dao;

import edu.upc.dsa.FactorySession;
import edu.upc.dsa.Session;
import edu.upc.dsa.UsuarioManager;
import edu.upc.dsa.UsuarioManagerImpl;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO{

    private static UsuarioDAO instance;

    final static Logger logger = Logger.getLogger(UsuarioDAOImpl.class);

    private UsuarioDAOImpl() {
    }

    public static UsuarioDAO getInstance() {
        if (instance==null) instance = new UsuarioDAOImpl();
        return instance;
    }

    @Override
    public void addUsuario(String nombre, String password) throws SQLException {
        Session session = null;
        try {
            session = FactorySession.openSession();
            Usuario u = new Usuario(nombre, password);
            logger.info("A punto de guardar en la BBDD al usuario ID: "+u.getId() + ". Username: " + u.getNombre() + ". Password: " + u.getPassword());
            session.save(u);
            logger.info("Usuario añadido. ID: "+u.getId() + ". Username: " + u.getNombre() + ". Password: " + u.getPassword());
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }

    @Override
    public Usuario getUsuario(String id) throws SQLException {
        Session session = null;
        Usuario u = null;
        try {
            session = FactorySession.openSession();
            u = (Usuario)session.get(Usuario.class, id);
            logger.info(u.getId());
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
    public Usuario getUsuario(String nombre, String password) throws SQLException {
        Session session = null;
        Usuario a = null;
        Usuario u = new Usuario(nombre, password);
        logger.info(nombre + " esta intentando iniciar sesion");
        try {
            session = FactorySession.openSession();
            a = (Usuario)session.get(u, nombre);
            if(nombre.equals(a.getNombre()) && password.equals(a.getPassword())){
                u = a;
                logger.info("Id de " + u.getNombre() +" es " + u.getId());
            }
        }
        catch (Exception e) {
            logger.warn("Exception! "  + e.getLocalizedMessage());
        }
        finally {
            session.close();
        }

        return u;
    }

    @Override
    public void eliminarUsuario(String id) throws SQLException {
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
