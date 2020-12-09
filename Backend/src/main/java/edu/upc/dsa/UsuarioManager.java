package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface UsuarioManager {

    public void addUsuario(String nombre, String password);
    public Usuario getUsuario(String id);
    public Usuario getUsuario(String nombre, String password);
    public void eliminarUsuario(String id);




    //Añadir usuario v
    //Eliminar usuario v
    //Leer usuario get v
    //Actualizar? v
}
