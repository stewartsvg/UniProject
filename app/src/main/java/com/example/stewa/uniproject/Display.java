package com.example.stewa.uniproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_info);

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS",MODE_PRIVATE);
        String display = sharedPreferences.getString("credentials","");

        TextView displayInfo = (TextView) findViewById(R.id.tvDisplayCredentials);
        displayInfo.setText(display);
    }
}
