package com.koravant.uachain.utils;

import android.content.Context;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

public class Options {
    static Web3j web3 = null;
    static String url_blockchain = "http://192.168.0.101:7545";
    static String blockchain_privateKey = "0x41c60396c0166d75c7f9147e13c60345d9aa6d972d19398d6bbaaf2ef6b78e2a";
    static Credentials credentials = null;
    static String ADDRESS = "0xC3f7e624dBb5684f84bA4Dd13bE5DC2eeDe9B15D";
    static String contractAddress = "0xBA1655A6a2E7bB808e8Babf3775D308d493913d6";
    static  String error = "";
    public static Context context = null;
    public static loadingDialog dialog = null;
    public static String purpose = "";
}
