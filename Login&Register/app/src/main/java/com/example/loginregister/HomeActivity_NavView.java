package com.example.loginregister;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginregister.models.Coins;
import com.example.loginregister.models.Usuario;
import com.example.loginregister.utils.ImageDownloader;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.unity3d.player.UnityPlayerActivity;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity_NavView extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    APIInterface apiIface;
    SharedPreferences sp;
    TextView nombre;
    TextView ID;
    ImageView icono;
    UnityBridge unityBridge;

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
                Intent launchGame = new Intent(getApplicationContext(), UnityPlayerActivity.class);
                startActivity(launchGame);
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
                ImageDownloader imageDownloader = new ImageDownloader();
                imageDownloader.downloadAndSetImage(sp.getAll().get("ID").toString(), icono);
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
        TextView txtCoins = (TextView)findViewById(R.id.txtCoins);
        Usuario user = new Usuario(sp.getAll().get("Username").toString(), "", sp.getAll().get("ID").toString());
        apiIface = APIClient.getClient().create(APIInterface.class);
        Call<Coins> call = apiIface.getCoins(user);
        call.enqueue(new Callback<Coins>() {
            @Override
            public void onResponse(Call<Coins> call, Response<Coins> response) {
                if (response.code() == 200) {
                    //Tenemos que recoger también el ID del usuario
                    Coins coins = response.body();
                    Log.i("grup3", coins.getCoins());
                    txtCoins.setText(coins.getCoins());
                } else {
                    Toast.makeText(getApplicationContext(), "Error when logging in.", Toast.LENGTH_SHORT).show();
                    Log.i("grup3", "Coins records for this user not found");
                }
            }
            @Override
            public void onFailure(Call<Coins> call, Throwable t) {
                Log.i("grup3", "Error when connecting", t);
                call.cancel();
            }
        });
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