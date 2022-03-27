package com.koravant.uachain.utils;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import com.koravant.uachain.R;

public class loadingDialog {

    private Activity activity;
    private AlertDialog alertDialog;
    public boolean find = false;

    public loadingDialog(Activity myactivity)
    {
        activity= myactivity;
    }

    public void startLoadingDialog()
    {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(true);


        alertDialog = builder.create();
        alertDialog.show();
    }


    public void dismisDialog()
    {
        alertDialog.dismiss();
        Log.e("Scan", "Waiting...");
    }
}
