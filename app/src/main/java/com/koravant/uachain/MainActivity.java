package com.koravant.uachain;

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
import com.koravant.uachain.view.Transport;

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
                Intent i = new Intent(MainActivity.this, Transport.class);
                startActivity(i);
            }
        });
        // Tests
//        Button btn = findViewById(R.id.blcktest);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, BlockTest2.class);
//                startActivity(intent);
//            }
//        });
//
//        Button btn2 = findViewById(R.id.cardtest);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CardTest.class);
//                startActivity(intent);
//            }
//        });


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

//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
////        return super.onContextItemSelected(item);
//        Log.e("###", "item"+item.getItemId());
//        return super.onContextItemSelected(item);
//    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_register:
//                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
//                String[] recipients = new String[]{"koravant1@gmail.com", "",};
//                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
//                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Write for Android Remote Hacker");
//    //                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "This is email's message");
//                emailIntent.setType("message/rfc822");
//                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//    //                Commandes.showToast(this,this,"home");
                Log.e("###", "item id = "+id);
                break;
            case R.id.nav_home:
                Log.e("###", "item id 2 = "+id);
                break;

            case R.id.nav_verify:
                Log.e("###", "item id 4 = "+id);
                break;

            case R.id.nav_about:
    //                Commandes.startActivity(this,HotSpot.class);
    //                Commandes.showToast(this,this,"home");
//                Commandes.alerte(this,this,"About",getResources().getString(R.string.app_description));
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