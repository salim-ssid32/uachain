package com.koravant.uachain.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.koravant.uachain.R;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;


/**
 * A simple web3j application that demonstrates a number of core features of web3j:
 *
 * <ol>
 *     <li>Connecting to a node on the Ethereum network</li>
 *     <li>Loading an Ethereum wallet file</li>
 *     <li>Sending Ether from one address to another</li>
 *     <li>Deploying a smart contract to the network</li>
 *     <li>Reading a value from the deployed smart contract</li>
 *     <li>Updating a value in the deployed smart contract</li>
 *     <li>Viewing an event logged by the smart contract</li>
 * </ol>
 *
 * <p>To run this demo, you will need to provide:
 *
 * <ol>
 *     <li>Ethereum client (or node) endpoint. The simplest thing to do is
 *     <a href="https://infura.io/register.html">request a free access token from Infura</a></li>
 *     <li>A wallet file. This can be generated using the web3j
 *     <a href="https://docs.web3j.io/command_line.html">command line tools</a></li>
 *     <li>Some Ether. This can be requested from the
 *     <a href="https://www.rinkeby.io/#faucet">Rinkeby Faucet</a></li>
 * </ol>
 *
 * <p>For further background information, refer to the project README.
 */

public class BlockTest2 extends AppCompatActivity {

    public Web3j web3 = null;
    private static final Logger log = LoggerFactory.getLogger(BlockTest2.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_test2);
        BlockChain.connect();
//        ##########################################
        Button btn2 = findViewById(R.id.btn_Account_blcktest);
        TextView textView2 = findViewById(R.id.txt_Account_blcktest);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EthAccounts res = BlockChain.getEthAccounts();
                textView2.setText(res.getAccounts().toString());
            }
        });

        //        ##########################################
        Button btn3 = findViewById(R.id.btn_AB_blcktest);
        TextView textView3 = findViewById(R.id.txt_AB_blcktest);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                run_test();
//                StringUtils.join(list, ", ");
                String res = BlockChain.getEthBalance(Options.ADDRESS);
//                BigInteger f = res.getBalance();
//                Utils.log_error("Dump", Dumper.dump(res));
                textView3.setText(" The Eth Balance is: " + res);
            }
        });


        //        ##########################################
        Button btn4 = findViewById(R.id.btn_CBN_blcktest);
        TextView textView4 = findViewById(R.id.txt_CBN_blcktest);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView4.setText(" The Block Number is: " + BlockChain.getEthBlockNumber());
            }
        });


        //        ##########################################
        Button btn5 = findViewById(R.id.btn_SE_blcktest);
        TextView textView5 = findViewById(R.id.txt_SE_blcktest);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlockChain.sendEth(BlockChain.getCredentialFromKey(Options.blockchain_privateKey),"0x90F8bf6A479f320ead074411a4B0e7944Ea8c9C1", BigDecimal.ONE);
                textView5.setText(" Send ETH ");
            }
        });
    }


}