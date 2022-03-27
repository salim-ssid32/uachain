package com.koravant.uachain.utils;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;

import com.koravant.uachain.R;
import com.zcs.sdk.DriverManager;
import com.zcs.sdk.Printer;
import com.zcs.sdk.SdkResult;
import com.zcs.sdk.print.PrnStrFormat;
import com.zcs.sdk.print.PrnTextFont;
import com.zcs.sdk.print.PrnTextStyle;

import java.io.IOException;
import java.io.InputStream;

public class PrinterUtil {

    public static void testPrint(String data, String purpose) {
//        Wrapper wrapper = new Wrapper();
//        wrapper.string = data;

        new Thread(new Runnable() {
            @Override
            public void run() {
                DriverManager manager = DriverManager.getInstance();
                Printer mPrinter = manager.getPrinter();
                int printStatus = mPrinter.getPrinterStatus();
                if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
                    Utils.log_error("PRINTER", "SDK_PRN_STATUS_PAPEROUT");
                } else {
                    PrnStrFormat format = new PrnStrFormat();
                    format.setTextSize(30);
                    format.setAli(Layout.Alignment.ALIGN_CENTER);
                    format.setStyle(PrnTextStyle.BOLD);
                    mPrinter.setPrintAppendString("----------- UAChain --------------", format);
                    mPrinter.setPrintAppendQRCode(data, 300, 300, Layout.Alignment.ALIGN_CENTER);

                    mPrinter.setPrintAppendString(Utils.strim(purpose), format);
                    mPrinter.setPrintAppendString(" ", format);
                    mPrinter.setPrintAppendString(" ", format);
                    format.setFont(PrnTextFont.DEFAULT);

                    format.setTextSize(22);
                    format.setStyle(PrnTextStyle.NORMAL);
                    format.setAli(Layout.Alignment.ALIGN_NORMAL);
                    printStatus = mPrinter.setPrintStart();
                    if (printStatus == SdkResult.SDK_OK) {
//                        checkTrue("print1", getString(R.string.print_successful));
                        Utils.log_error("PRINTER", "mPrinter.setPrintStart()");
                    } else {
//                        checkFalse("print1", printStatus);
                    }
                }

            }
        }).start();
    }



}
