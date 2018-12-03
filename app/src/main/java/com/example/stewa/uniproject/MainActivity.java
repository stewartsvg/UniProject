package com.example.stewa.uniproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //creates and runs application
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //when runGetEthereumClientVersion button is pressed it will send a get request to the blockchain
        //and show the output of the Ethereum client version in ethereumOutputText
        final Button runGetEthereumClientVersion = findViewById(R.id.ethereumExampleButton);
        final TextView ethereumOutputText = findViewById(R.id.ethereumOutputText);

        runGetEthereumClientVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        String clientVersionString = null;
                        EthereumRequest ethereumRequest = new EthereumRequest();
                        clientVersionString = ethereumRequest.getClientVersion();
                        return clientVersionString;
                    }

                    @Override
                    protected void onPostExecute(Object o){
                        ethereumOutputText.setText(o.toString());
                    }
                }.execute();
            }
        });


        //when runEthereumTransactionButton is pressed it will send the ethereumTransactionValue
        //amount to the example transaction and execute the transaction
        final Button runEthereumTransactionButton = findViewById(R.id.ethereumTransactionButton);
        final TextView ethereumTransactionValue = findViewById(R.id.ethereumTransactionValue);
        
        runEthereumTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        double transactionValue = Double.parseDouble(ethereumTransactionValue.getText().toString());
                        EthereumRequest ethereumRequest = new EthereumRequest();
                        String transactionMessage = ethereumRequest.executeAddress1toAddress2Transaction(transactionValue);
                        return transactionMessage;
                    }

                    @Override
                    protected void onPostExecute(Object o){
                        ethereumOutputText.setText(o.toString());
                    }
                }.execute();
            }
        });

    }
}
