package com.koravant.uachain.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.koravant.uachain.MainActivity;
import com.koravant.uachain.R;
import com.zcs.sdk.DriverManager;
import com.zcs.sdk.SdkData;
import com.zcs.sdk.SdkResult;
import com.zcs.sdk.Sys;
import com.zcs.sdk.card.CardReaderManager;
import com.zcs.sdk.card.RfCard;
import com.zcs.sdk.util.StringUtils;

public class CardTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_test);

        CardUtils cardUtils = CardUtils.getInstance();

        Button btn = findViewById(R.id.btn1_cardtest);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardUtils.searchCard();
            }
        });
    }
}

