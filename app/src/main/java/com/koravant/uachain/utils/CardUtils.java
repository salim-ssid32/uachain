package com.koravant.uachain.utils;

import android.widget.Toast;

import com.koravant.uachain.view.Transport;
import com.zcs.sdk.DriverManager;
import com.zcs.sdk.SdkData;
import com.zcs.sdk.SdkResult;
import com.zcs.sdk.Sys;
import com.zcs.sdk.card.CardInfoEntity;
import com.zcs.sdk.card.CardReaderManager;
import com.zcs.sdk.card.CardReaderTypeEnum;
import com.zcs.sdk.card.RfCard;
import com.zcs.sdk.fingerprint.FingerprintManager;
import com.zcs.sdk.listener.OnSearchCardListener;
import com.zcs.sdk.util.StringUtils;


public class CardUtils {


    public static CardReaderManager mCardReadManager= null;
    public static DriverManager mDriverManager = null;
    public static Sys mSys = null;
    public static final byte[] APDU_SEND_IC = {0x00, (byte) 0xA4, 0x04, 0x00, 0x0E,
            0x31, 0x50, 0x41, 0x59, 0x2E, 0x53, 0x59, 0x53, 0x2E, 0x44, 0x44, 0x46, 0x30,
            0x31, 0X00};
    public static final byte[] APDU_SEND_RF = {0x00, (byte) 0xA4, 0x04, 0x00, 0x0E,
            0x32, 0x50, 0x41, 0x59, 0x2E, 0x53, 0x59, 0x53, 0x2E, 0x44, 0x44, 0x46, 0x30,
            0x31, 0x00};
    public static final byte[] APDU_SEND_RANDOM = {0x00, (byte) 0x84, 0x00, 0x00,
            0x08};
    public static final byte[] APDU_SEND_FELICA = {0x10, 0x06, 0x01, 0x2E, 0x45,
            0x76, (byte) 0xBA, (byte) 0xC5, 0x45, 0x2B, 0x01, 0x09, 0x00, 0x01, (byte) 0x80,
            0x00};


    public static CardUtils getInstance(){
        return new CardUtils();
    }


    CardUtils(){
        mDriverManager= DriverManager.getInstance();
        mCardReadManager = mDriverManager.getCardReadManager();
        mSys = mDriverManager.getBaseSysDevice();
        init();
    }

    public void init(){
        int statue = mSys.getFirmwareVer(new String[1]);
        if (statue != SdkResult.SDK_OK) {
            int sysPowerOn = mSys.sysPowerOn();
            Utils.log_error("init", "sysPowerOn: " + sysPowerOn);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int i = mSys.sdkInit();
        if (i == SdkResult.SDK_OK) {
        }else {
            Utils.log_error("init", "Error to Init SDK");
        }
    }

    public void readRfCard() {
        RfCard mRfCard = mCardReadManager.getRFCard();
        int rfReset = mRfCard.rfReset();
        if (rfReset == SdkResult.SDK_OK) {
            byte[] apduSend;
//            if (realRfType == SdkData.RF_TYPE_FELICA) { // felica card
//                apduSend = APDU_SEND_FELICA;
//            } else {
            apduSend = APDU_SEND_RF;
//            }
            byte[] recvData = new byte[300];
            int[] recvLen = new int[1];
            int rfRes = mRfCard.rfExchangeAPDU(apduSend, recvData, recvLen);
            int powerDownRes = mRfCard.rfCardPowerDown();
            if (rfRes == SdkResult.SDK_OK) {
//                mHandler.sendEmptyMessage(rfRes);
                String recv = StringUtils.convertBytesToHex(recvData).substring(0,
                        recvLen[0] * 2);
                Utils.log_error("readRfCard",recv);
                Wrapper.resultCardRead = recv;
                Options.dialog.dismisDialog();
//                Toast.makeText(Options.context, "Reussite de l'operation", Toast.LENGTH_LONG);
            }
        }
        else {
            Utils.log_error("readRfCard","Cannot Reset");
            Wrapper.errorCardScanned = true;
        }
//        Options.dialog.dismisDialog();
    }

    public int searchCard(){
        Wrapper wrapper = new Wrapper();
        mCardReadManager.searchCard(CardReaderTypeEnum.RF_CARD, 0, (byte) (SdkData.RF_TYPE_A | SdkData.RF_TYPE_B | SdkData.RF_TYPE_FELICA | SdkData.RF_TYPE_N24G), new OnSearchCardListener() {
            @Override
            public void onCardInfo(CardInfoEntity cardInfoEntity) {
//                Log.e(TAG, "searchCard thread: " + Thread.currentThread().getName());
//                Message msg = Message.obtain();
                CardReaderTypeEnum cardTypeNew = cardInfoEntity.getCardExistslot();
                Utils.log_error("searchCard",cardInfoEntity.getCardNo()+"|"+
                        cardInfoEntity.getExpiredDate()+"|"+cardInfoEntity.getServiceCode());

                readRfCard();
//                switch (cardTypeNew) {
//                    case RF_CARD:
//                        if (bCardType == SdkData.RF_TYPE_A) {
//                            readM1Card();
//
//                        } else {
//                            readRfCard();
//                        }
//                        break;
//                    case MAG_CARD:
//                        readMagCard();
//                        break;
//                    case IC_CARD:
//                        readICCard(CardSlotNoEnum.SDK_ICC_USERCARD);
//                        break;
//                    case PSIM1:
//                        readICCard(CardSlotNoEnum.SDK_ICC_SAM1);
//                        break;
//                    case PSIM2:
//                        readICCard(CardSlotNoEnum.SDK_ICC_SAM2);
//                        break;
//                }

            }

            @Override
            public void onError(int i) {
                Utils.log_error("fdff", "search card onError: " + i);
                //  mHandler.sendEmptyMessage(i);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNoCard(CardReaderTypeEnum cardReaderTypeEnum, boolean b) {

            }
        });
        return wrapper.out;
    }
}
