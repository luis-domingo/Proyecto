package edu.upc.dsa.dao;

import edu.upc.dsa.models.Usuario;

public interface UsuarioDAO {
    public void addUsuario(String nombre, String password);
    public Usuario getUsuario(String id);
    public Usuario getUsuario(String nombre, String password);
    public void eliminarUsuario(String id);
}
