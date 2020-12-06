package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class ForgotPasswordActivity extends AppCompatActivity {

    private String username;

    public void setUsername() {
        this.username = (String)this.getIntent().getExtras().get("username");
    }

    @SuppressLint("SetTextI18n")
    public void setText(String username){
        TextView texto = (TextView)findViewById(R.id.textWelcome);
        String textoContenido = "Hi " + username + "!\nIt's a pleasure to see you back!\nIn order to recover access to your account we need that you answer to the following security question: \n";
        texto.setText(textoContenido);
    }

    //Ahora tengo que enviar al servidor el username y que me devuelva la pregunta de seguridad

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setUsername();
        this.setText(this.username);
        setContentView(R.layout.activity_forgot_password);
    }
}