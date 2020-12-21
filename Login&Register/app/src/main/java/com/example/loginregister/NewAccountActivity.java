package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAccountActivity extends AppCompatActivity {

    APIInterface apiIface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        apiIface = APIClient.getClient().create(APIInterface.class);
    }

    public String getUsername(View v){
        EditText usernameContainer;
        usernameContainer = (EditText)findViewById(R.id.textSignUpUsername);
        return usernameContainer.getText().toString();
    }

    public String getPassword(View v){
        EditText passwordContainer;
        passwordContainer = (EditText)findViewById(R.id.textSignUpPassword);
        return passwordContainer.getText().toString();
    }

    public String getPasswordConfirmation(View v){
        EditText passwordContainer;
        passwordContainer = (EditText)findViewById(R.id.textSignUpPasswordConfirm);
        return passwordContainer.getText().toString();
    }

    public void onNewAccountClick(View v){
        String username = getUsername(v);
        String password = getPassword(v);
        if(getPassword(v).equals(getPasswordConfirmation(v))) {
            Call<Usuario> call = apiIface.createUser(new Usuario(username, password));
            call.enqueue(new Callback<Usuario>() {
                @SuppressLint("ShowToast")
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.code() == 200) {
                        Usuario usuario = response.body();
                        Log.i("grup3", "Usuario creado: " + usuario.getNombre());
                        Toast.makeText(getApplicationContext(), "Account correctly created!", Toast.LENGTH_LONG).show();
                        openLoginActivity();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error when creating the account", Toast.LENGTH_LONG).show();
                        Log.i("grup3", "Account not created");
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.i("grup3", "Error", t);
                    call.cancel();
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Passwords don't match! Retry", Toast.LENGTH_LONG).show();
        }
    }
    public void openLoginActivity(){
        Intent openLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(openLoginActivity);
    }
}