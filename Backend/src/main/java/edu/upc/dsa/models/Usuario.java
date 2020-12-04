package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Usuario {
    String id;
    String nombre;
    String password;

    public Usuario(){
    }

    public Usuario(String nombre, String password){
        this.id = RandomUtils.getId();
        this.nombre = nombre;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
