package com.example.stewa.uniproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button logoutButton = (Button) findViewById(R.id.btn_home_log_out);

        //empties credentials and returns to login screen
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                //sets credentials to be empty when user logs out
                editor.putString("credentials", "");
                editor.commit();

                Intent goToLoginScreen = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(goToLoginScreen);
                finish();

            }
        });
    }
}
