package com.example.loginregister;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public String getUsername(View v){
        EditText usernameContainer;
        usernameContainer = (EditText)findViewById(R.id.textUsername);
        return usernameContainer.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

}