package com.koravant.uachain.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.koravant.uachain.R;
import com.koravant.uachain.utils.CardUtils;
import com.koravant.uachain.utils.Options;
import com.koravant.uachain.utils.PrinterUtil;
import com.koravant.uachain.utils.Utils;
import com.koravant.uachain.utils.Wrapper;
import com.koravant.uachain.utils.loadingDialog;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Gestionnaire de " +
                    "Billet");
        }
        scan();

        MaterialButton imprimer = findViewById(R.id.payment_printticket);
        imprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrinterUtil.testPrint(Utils.getRandom(16), Options.purpose);
                Utils.log_error("IMPRIMER", "N/A 2");
            }
        });

    }


    public void scan(){
        loadingDialog dialog = new loadingDialog(Payment.this);
        Options.dialog = dialog;
        dialog.startLoadingDialog();
        CardUtils cardUtils = CardUtils.getInstance();
        cardUtils.searchCard();
//        int i = 0;
//        while (!dialog.find){
//            i++;
//            if (i==5) {
//                    dialog.dismisDialog();
//                }
//            }
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