package com.koravant.uachain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextInputEditText username = findViewById(R.id.text_username);
        TextInputEditText pass = findViewById(R.id.password);
        MaterialButton loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eUser = username.getEditableText().toString().trim();
                String ePass = pass.getEditableText().toString().trim();
                Log.e("Login", eUser+":"+ePass);
                if(eUser.equals("admin") && ePass.equals("password")) {
                    finish();
                } else {
                    pass.setText("");
                    pass.setError("Password or username incorrect");
                }
                }
        });

    }
//    @Override
//    public void onBackPressed() {
//        finishActivity(25);
//    }
}