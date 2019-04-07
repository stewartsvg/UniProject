package com.example.stewa.uniproject;

import android.os.AsyncTask;

public class EthereumTransactionTask extends AsyncTask {

    TransactionDetails transactionDetails;

    public EthereumTransactionTask(TransactionDetails transactionDetails){
        this.transactionDetails = transactionDetails;
    }

    @Override
    protected String doInBackground(Object[] objects) {
        EthereumTransactionMaker ethereumTransactionMaker = new EthereumTransactionMaker(transactionDetails);
        ethereumTransactionMaker.successfulTransaction();
        return null;
    }

    @Override
    protected void onPostExecute(Object o){

    }

}
