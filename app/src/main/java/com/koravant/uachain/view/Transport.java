package com.koravant.uachain.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.koravant.uachain.R;
import com.koravant.uachain.utils.CardUtils;
import com.koravant.uachain.utils.DialogUtils;
import com.koravant.uachain.utils.Options;
import com.koravant.uachain.utils.PrinterUtil;
import com.koravant.uachain.utils.Utils;

public class Transport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Billet de Transport");
        }
        // Payer
        MaterialButton payer = findViewById(R.id.tr_pay);
        payer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Options.purpose = "TRANSPORT";
                Intent i = new Intent(Transport.this, Payment.class);
                startActivity(i);

            }
        });

        // Verifier
        MaterialButton verifier = findViewById(R.id.tr_verify);
        verifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.log_error("VERIFIER", "N/A 2");
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}