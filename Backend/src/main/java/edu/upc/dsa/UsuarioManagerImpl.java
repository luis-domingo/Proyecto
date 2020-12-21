/*package edu.upc.dsa;

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

    //Función para añadir usuario
    @Override
    public void addUsuario(String nombre, String password) {

        Usuario u = new Usuario(nombre, password);
        usuarios.put(u.getId(),u);
        logger.info("Usuario añadido. ID: "+u.getId() + ". Username: " + u.getNombre() + ". Password: " + u.getPassword());
    }

    @Override
    public Usuario getUsuario(String id) {
        Usuario u = new Usuario(usuarios.get(id).getNombre(), usuarios.get(id).getPassword());
        u.setId(id);
        return u;
    }

    //Función para el Log In
    @Override
    public Usuario getUsuario(String nombre, String password) {

        Usuario a = null;
        for(Usuario user : usuarios.values()){
            if(nombre.equals(user.getNombre()) && password.equals(user.getPassword())){
                Usuario u = new Usuario(nombre,password);
                u.setId(user.getId());
                a = u;
                logger.info(u.getId());
            }
        }
        return a;
    }

    //Función para eliminar Usuario
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
*/