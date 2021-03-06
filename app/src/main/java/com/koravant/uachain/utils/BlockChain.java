package com.koravant.uachain.utils;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.koravant.uachain.R;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.ens.EnsResolver;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.abi.datatypes.
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class BlockChain {
//    public TransactionReceipt transfert(){
//        TransactionReceipt transferReceipt = Transfer.sendFunds(
//                web3j, credentials,
//                "0x19e03255f667bdfd50a32722df860b1eeaf4d635",  // you can put any address here
//                BigDecimal.ONE, Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
//                .send();
//        log.info("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
//                + transferReceipt.getTransactionHash());
//    }

    public static boolean isConnected=false;

    public static void connect(){
        new Thread(
                () -> {
                    try {
                        Options.web3 = Web3j.build(new HttpService(Options.url_blockchain));
                        isConnected = true;
                        Log.e("Slim","Connected to Ethereum client version: " + Options.web3.web3ClientVersion().send().getWeb3ClientVersion());
                    } catch (Exception e) {
                        e.printStackTrace();
                        isConnected =false;
                        Options.error = e.toString();
                    }

                }
        ).start();
    }

    public static String getEthBlockNumber(){
        if(!isConnected){
            Utils.log_error("getEthBlockNumber","Not Connected");
            return null;
        }
        EthBlockNumber result = null;
        try {
            result = Options.web3.ethBlockNumber().sendAsync().get();
//            Utils.log_error("Dump", result.get);
            return result.getBlockNumber().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Credentials getCredentialFromKey(String key){
        return Credentials.create(key);
    }

    public static EthAccounts getEthAccounts() {
        if(!isConnected){
            Utils.log_error("getEthAccounts","Not Connected");
            return null;
        }
        EthAccounts res = null;
        try {
            res = Options.web3.ethAccounts()
                    .sendAsync()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String getEthBalance(String address) {
        if(!isConnected){
            Utils.log_error("getEthBalance","Not Connected");
            return null;
        }
        String result = null;
//        Options.web3.ethGet
        try {
//            EthBlockNumber f = Options.web3.ethBlockNumber().sendAsync().get();
//            DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(
//                    f.getBlockNumber());
//            Utils.log_error("dddd", defaultBlockParameter.getValue());
            result = Options.web3.ethGetBalance(address,
                    DefaultBlockParameter.valueOf("latest"))
                    .sendAsync()
                    .get().getBalance().toString();
            result = Convert.fromWei(result,Convert.Unit.ETHER).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  static  void sendEth(Credentials credentials, String destinationAddress, BigDecimal amountToTransfer){

//        Utils.log_error("sendEth", Dumper.dump(credentials));
        if (!WalletUtils.isValidAddress(destinationAddress)
                && !EnsResolver.isValidEnsName(destinationAddress)) {
            Utils.log_error("sendEth","Invalid destination address specified");
        }
        TransactionReceipt transactionReceipt=null;
        try {
            transactionReceipt = Transfer.sendFunds(Options.web3, credentials, destinationAddress,
                    amountToTransfer, Convert.Unit.ETHER).sendAsync().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Utils.log_error("sendEth", Dumper.dump(transactionReceipt));
//                sendFunds(Options.web3, destinationAddress, credentials, amountInWei);


    }

    public static boolean IsConnected(Context context){
        if( !isConnected){
            if(Options.error.equals("")){
                return false;
            }
            new AlertDialog.Builder(context)
                    .setTitle("Erreur de Connection")
                    .setMessage(Options.error)
                    .setPositiveButton("Compris", null)
                    .show();
            return false;
        }
        return true;
    }

    public static void PrintError(Context context, String error){
        new AlertDialog.Builder(context)
                    .setTitle("Erreur...")
                    .setMessage(error)
                    .setPositiveButton("Compris", null)
                    .show();
    }


    public static String getTokenName(Context context){

        if(!IsConnected(context)){
            return "";
        }
        Wrapper wrapper = new Wrapper();
        ERC20 javaToken = ERC20.load(Options.contractAddress, Options.web3, getCredentialFromKey(Options.ADDRESS), new DefaultGasProvider());
        Thread th = new Thread(
                () -> {
                    try {
                        wrapper.string = javaToken.name().send();
                    } catch (Exception e) {
                        e.printStackTrace();
                        wrapper.error = true;
                        wrapper.errStr = e.toString();
                    }
                }
        );
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (wrapper.error){
            PrintError(context, wrapper.errStr);
        }
        return wrapper.string;
    }

}
