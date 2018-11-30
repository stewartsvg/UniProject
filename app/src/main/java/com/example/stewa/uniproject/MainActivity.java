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

        //when runEthereumExampleButton is pressed it will send the sample data to the blockchain
        //and show the output in ethereumOutputText
        final Button runEthereumExampleButton = findViewById(R.id.ethereumExampleButton);
        final TextView ethereumOutputText = findViewById(R.id.ethereumOutputText);

        runEthereumExampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        String clientVersionString = null;
                        SmartContractRequest smartContractRequest = new SmartContractRequest();
                        clientVersionString = smartContractRequest.getClientVersion();
                        return clientVersionString;
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
