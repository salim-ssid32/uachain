package com.koravant.uachain;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.koravant.uachain.utils.BlockTest2;
import com.koravant.uachain.utils.CardTest;
import com.koravant.uachain.view.Bibliotheque;
import com.koravant.uachain.view.Enregistrement;
import com.koravant.uachain.view.Logement;
import com.koravant.uachain.view.Photocopies;
import com.koravant.uachain.view.Restauration;
import com.koravant.uachain.view.Transport;
import com.koravant.uachain.view.Verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Login form
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_register, R.id.nav_slideshow, R.id.nav_verify, R.id.nav_about)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        LinearLayout transportView = findViewById(R.id.view_transport);
        transportView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(MainActivity.this, Transport.class);
            }
        });

        LinearLayout restaurantView = findViewById(R.id.view_restaurant);
        restaurantView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(MainActivity.this, Restauration.class);
            }
        });

        LinearLayout photocopiesView = findViewById(R.id.view_photocopie);
        photocopiesView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(MainActivity.this, Photocopies.class);
            }
        });

        LinearLayout logementView = findViewById(R.id.view_logement);
        logementView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(MainActivity.this, Logement.class);
            }
        });

        LinearLayout biblioView = findViewById(R.id.view_library);
        biblioView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(MainActivity.this, Bibliotheque.class);
            }
        });

    }

    public void launchActivity(Context context, Class obj){
        Intent i = new Intent(context, obj);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_register:
                Log.e("###", "item id = "+id);
                launchActivity(MainActivity.this, Enregistrement.class);
                break;
            case R.id.nav_home:
                Log.e("###", "item id 2 = "+id);
                break;

            case R.id.nav_verify:
                Log.e("###", "item id 4 = "+id);
                launchActivity(MainActivity.this, Verification.class);
                break;

            case R.id.nav_geth:
                Log.e("###", "item id 5 = "+id);
                launchActivity(MainActivity.this, BlockTest2.class);
                break;


            case R.id.nav_about:
                Log.e("###", "item id 3 = "+id);
                new AlertDialog.Builder(this)
                        .setTitle("UAChain Controlleur")
                        .setMessage(R.string.about_description)
                        .setPositiveButton("Compris", null)
                        .show();
                break;
            default:
                break;
        }
        this.drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}