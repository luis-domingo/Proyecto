package edu.upc.dsa;

import edu.upc.dsa.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

public class UsuarioManagerImpl implements UsuarioManager{

    private static UsuarioManager instance;
    protected HashMap<String,Usuario> usuarios;
    final static Logger logger = Logger.getLogger(UsuarioManagerImpl.class);

    private UsuarioManagerImpl() {
        this.usuarios = new HashMap<>();
    }

    public static UsuarioManager getInstance() {
        if (instance==null) instance = new UsuarioManagerImpl();
        return instance;
    }

    @Override
    public void addUsuario(String nombre, String password) {

        Usuario u = new Usuario(nombre, password);
        usuarios.put(u.getId(),u);
        logger.info("Usuario añadido: "+u.getId());
    }

    @Override
    public Usuario getUsuario(String id) {
        Usuario u = new Usuario(usuarios.get(id).getNombre(), usuarios.get(id).getPassword());
        u.setId(id);
        return u;
    }

    @Override
    public void eliminarUsuario(String id) {
        try {
            usuarios.remove(id);
            logger.info("Usuario "+id+" eliminado");
        }
        catch(Exception e){
            logger.warn("No existe este id");
        }
    }

}
