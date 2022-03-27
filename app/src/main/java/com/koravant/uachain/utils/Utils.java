package com.koravant.uachain.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.zcs.sdk.DriverManager;
import com.zcs.sdk.card.CardReaderManager;

import org.bouncycastle.util.encoders.Hex;

import java.security.SecureRandom;

public class Utils {

    public static void log_error(String TAG, String Mess){
        Log.e(TAG,Mess);
    }

    public static void showSearchCardDialog(Context context, String title, String msg) {
        String dia_msg = msg;
        CardReaderManager mCardReadManager = DriverManager.getInstance().getCardReadManager();
        Object mProgressDialog = DialogUtils.show(context, title, msg, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mCardReadManager.cancelSearchCard();
//                switch (dia_msg) {
//                    case R.string.msg_rf_card:
//                        checkresult[2] = 2;
//                        rfTestResult.setText(getString(R.string.no_test));
//                        rfTestResult.setTextColor(0xff1B2227);
//                        icCardTest();
//                        break;
//                    case R.string.msg_ic_card:
//                        icTestResult.setText(getString(R.string.no_test));
//                        icTestResult.setTextColor(0xff1B2227);
//                        checkresult[3] = 2;
//                        m1CardTest();
//                        break;
//                    case R.string.msg_mag_card:
//                        magTestResult.setText(getString(R.string.no_test));
//                        magTestResult.setTextColor(0xff1B2227);
//                        checkresult[5] = 2;
//                        psam1CardTest();
//                        break;
//                    case R.string.msg_psam1_reading:
//                        psam1TestResult.setText(getString(R.string.no_test));
//                        psam1TestResult.setTextColor(0xff1B2227);
//                        checkresult[6] = 2;
//                        psam2CardTest();
//                        break;
//                    case R.string.msg_psam2_reading:
//                        psam2TestResult.setText(getString(R.string.no_test));
//                        psam2TestResult.setTextColor(0xff1B2227);
//                        checkresult[7] = 2;
//                        scanTest();
//                        break;
//                    case R.string.msg_m1_card:
//                        mM1TestResult.setText(getString(R.string.no_test));
//                        mM1TestResult.setTextColor(0xff1B2227);
//                        checkresult[4] = 2;
//                        magCardTest();
//                        break;
//                    default:
//                        break;
//                }
            }
        }, null);
    }

    public static String getRandom(int n){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[n];
        random.nextBytes(bytes);
        return Hex.toHexString(bytes);
    }
    public static String mul(String m, int n){
        StringBuilder out = new StringBuilder();
        for (int i=0; i < n; i++){
            out.append(m);
        }
        return out.toString();
    }
    public static String strim(String m){
        int len = 34;
        len -= m.length();
        len = len/2 -2;
        String pad = mul("-", len);
        return pad + " " + m + " " + pad;
    }
}
