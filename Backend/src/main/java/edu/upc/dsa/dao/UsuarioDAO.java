package edu.upc.dsa.dao;

import edu.upc.dsa.models.Usuario;

import java.sql.SQLException;

public interface UsuarioDAO {
    public void addUsuario(String nombre, String password) throws SQLException;
    public Usuario getUsuario(String id) throws SQLException;
    public Usuario getUsuario(String nombre, String password) throws SQLException;
    public void eliminarUsuario(String id) throws SQLException;
}
