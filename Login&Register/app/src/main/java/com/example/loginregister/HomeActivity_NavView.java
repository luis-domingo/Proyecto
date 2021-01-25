package com.example.loginregister;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity_NavView extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences sp;
    TextView nombre;
    TextView ID;
    ImageView icono;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                NavigationView navigationView = drawerView.findViewById(R.id.nav_view);
                View headerView = navigationView.getHeaderView(0);
                icono = (ImageView)headerView.findViewById(R.id.imageView);
                Picasso.get().load("http://147.83.7.205:8080/userImages/" + sp.getAll().get("ID").toString() + ".jpg").into(icono);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_forum, R.id.nav_profile, R.id.nav_shop, R.id.nav_yourInventory, R.id.nav_stats)
                .setDrawerLayout(drawer)
                .build();
        navigationView.getMenu().findItem(R.id.nav_web).setOnMenuItemClickListener(menuItem -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://147.83.7.205:8080/login.html"));
            startActivity(browserIntent);
            return true;
        });
        navigationView.getMenu().findItem(R.id.nav_logOut).setOnMenuItemClickListener(menuItem -> {
            Intent loginActivity = new Intent(this, LoginActivity.class);
            loginActivity.putExtra("logout", true);
            startActivity(loginActivity);
            return true;
        });
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        sp = this.getSharedPreferences("mySharedPreferences",MODE_PRIVATE);
        View headerView = navigationView.getHeaderView(0);
        nombre = (TextView)headerView.findViewById(R.id.textNombre1);
        ID = (TextView)headerView.findViewById(R.id.textView);
        nombre.setText("Usuario: " + sp.getAll().get("Username").toString());
        ID.setText("ID: " + sp.getAll().get("ID").toString());
        icono = (ImageView)headerView.findViewById(R.id.imageView);
        Picasso.get().load("http://147.83.7.205:8080/userImages/" + sp.getAll().get("ID").toString() + ".jpg").into(icono);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_activity_nav_view, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}