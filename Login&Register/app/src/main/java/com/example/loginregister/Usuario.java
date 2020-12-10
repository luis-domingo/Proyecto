package com.example.loginregister;


public class Usuario {
    private String nombre;

    private String password;

    private String id;

    public Usuario(String username, String password) {
        this.nombre = username;
        this.password = password;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //User Constructor
    public Usuario(String nombre, String password, String email, String id){
        this.nombre = nombre;
        this.password = password;
        this.id=id;
    }
    //Empty Constructor
    public Usuario(){ }
    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + nombre + '\'' +
                '}';
    }
}
