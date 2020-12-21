package com.example.loginregister;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    APIInterface apiIface;
    SharedPreferences sharedPreferences;
    String username;

    public String getUsername(View v){
        EditText usernameContainer;
        usernameContainer = (EditText)findViewById(R.id.textUsername);
        return usernameContainer.getText().toString();
    }

    public String getPassword(View v){
        EditText passwordContainer;
        passwordContainer = (EditText)findViewById(R.id.testPassword);
        return passwordContainer.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiIface = APIClient.getClient().create(APIInterface.class);
        sharedPreferences = getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
        if(sharedPreferences.getAll().size()!=0){
            username = (String)sharedPreferences.getAll().get("Username");
            Toast.makeText(getApplicationContext(), "Welcome back, " + username + "!", Toast.LENGTH_LONG).show();
            openHomeActivity();
        }
    }

    public void onPasswordClick(View v){
        Intent openPasswordActivity = new Intent(this, ForgotPasswordActivity.class);
        openPasswordActivity.putExtra("username", this.getUsername(v));
        startActivity(openPasswordActivity);
    }

    public void onAccountClick(View v){
        Intent openAccountActivity = new Intent(this, NewAccountActivity.class);
        startActivity(openAccountActivity);
    }

    public void onLoginClick(View v) {
        username = getUsername(v);
        String password = getPassword(v);
        Call<Usuario> call = apiIface.loginUser(new Usuario(username, password));
        call.enqueue(new Callback<Usuario>() {
            @SuppressLint("ShowToast")
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.code() == 200) {
                    //Tenemos que recoger también el ID del usuario
                    Usuario usuario = response.body();
                    Log.i("grup3", usuario.getNombre());
                    String username = usuario.getNombre();
                    Toast.makeText(getApplicationContext(), "Logged in correctly!", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = getSharedPreferences("mySharedPreferences", MODE_PRIVATE).edit();
                    editor.putString("Username", username);
                    editor.apply();
                    openHomeActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Error when logging in.", Toast.LENGTH_LONG).show();
                    Log.i("grup3", "Usuario not found");
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.i("grup3", "Error", t);
                call.cancel();
            }
        });
    }
    public void openHomeActivity(){
        Intent homeActivity = new Intent(this, HomeActivity_NavView.class);
        homeActivity.putExtra("Username", this.username);
        //homeActivity.putExtra("ID", this.id);
        startActivity(homeActivity);
    }
}