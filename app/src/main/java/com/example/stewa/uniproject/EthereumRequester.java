//package com.example.stewa.uniproject;
//
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//public class EthereumRequester extends AppCompatActivity {
//
//   // private EthereumTransactionMaker ethereumTransactionMaker();
//
//    //creates and runs application
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //when runGetEthereumClientVersion button is pressed it will send a get request to the blockchain
//        //and show the output of the Ethereum client version in ethereumOutputText
//        final Button runGetEthereumClientVersion = findViewById(R.id.ethereumVersionButton);
//        final TextView ethereumOutputText = findViewById(R.id.ethereumOutputText);
//
//
//        runGetEthereumClientVersion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AsyncTask asyncTask = new AsyncTask() {
//                    @Override
//                    protected Object doInBackground(Object[] objects) {
//                        String clientVersionString = null;
//                        EthereumTransactionMaker ethereumTransactionMaker = new EthereumTransactionMaker();
//                        clientVersionString = ethereumTransactionMaker.getClientVersion();
//                        return clientVersionString;
//                    }
//
//                    @Override
//                    protected void onPostExecute(Object o){
//                        ethereumOutputText.setText(o.toString());
//                    }
//                }.execute();
//            }
//        });
//
//
//        //when runEthereumTransactionButton is pressed it will send the ethereumTransactionValue
//        //amount to the example transaction and execute the transaction
//        final Button runEthereumTransactionButton = findViewById(R.id.ethereumTransactionButton);
//        final TextView ethereumTransactionValue = findViewById(R.id.ethereumTransactionValue);
//
//        runEthereumTransactionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AsyncTask asyncTask = new AsyncTask() {
//                    @Override
//                    protected Object doInBackground(Object[] objects) {
//                        double transactionValue = Double.parseDouble(ethereumTransactionValue.getText().toString());
//                        EthereumTransactionMaker ethereumTransactionMaker = new EthereumTransactionMaker();
//                       // String transactionMessage = ethereumTransactionMaker.executeAddress1toAddress2Transaction(transactionValue);
//                        return null;
//                    }
//
//                    @Override
//                    protected void onPostExecute(Object o){
//                        ethereumOutputText.setText(o.toString());
//                    }
//                }.execute();
//            }
//        });
//
//        //when runEthereumTransactionButton is pressed it will send the ethereumTransactionValue
//        //amount to the example transaction and execute the transaction
//        final Button runEthereumContractButton = findViewById(R.id.ethereumContractButton);
//        final TextView ethereumContractValue = findViewById(R.id.ethereumContractValue);
//
//        runEthereumContractButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AsyncTask asyncTask = new AsyncTask() {
//                    @Override
//                    protected Object doInBackground(Object[] objects) {
//                        String userName = ethereumContractValue.getText().toString();
//                        EthereumTransactionMaker ethereumTransactionMaker = new EthereumTransactionMaker();
//                        String contractMessage = ethereumTransactionMaker.addUsersToUserAddressBook(userName);
//                        return contractMessage;
//                    }
//
//                    @Override
//                    protected void onPostExecute(Object o){
//                        ethereumOutputText.setText(o.toString());
//                    }
//                }.execute();
//            }
//        });
//
//
//    }
//}
