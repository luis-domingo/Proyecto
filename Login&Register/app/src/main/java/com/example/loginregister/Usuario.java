package com.example.loginregister;


public class Usuario {
    private String uname;

    private String pswrd;

    private String id;

    public Usuario(String username, String password) {
        this.uname = username;
        this.pswrd = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPswrd() {
        return pswrd;
    }

    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //User Constructor
    public Usuario(String uname, String pswrd, String email, String id){
        this.uname = uname;
        this.pswrd = pswrd;
        this.id=id;
    }
    //Empty Constructor
    public Usuario(){ }
    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + uname + '\'' +
                '}';
    }
}
