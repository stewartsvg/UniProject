package com.example.stewa.uniproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText loginName = (EditText) findViewById(R.id.etLoginName);
        final EditText loginPassword = (EditText) findViewById(R.id.etLoginPassword);
        Button loginButton = (Button) findViewById(R.id.btnLoginButton);
        Button registerButton = (Button) findViewById(R.id.btnLoginRegister);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
                String usernameString = loginName.getText().toString();
                String passwordString = loginPassword.getText().toString();

                if (usernameString.equals("") || passwordString.equals("")) {
                    TextView emptyLoginFieldsError = findViewById(R.id.tvEmptyLoginFieldsError);
                    emptyLoginFieldsError.setVisibility(View.VISIBLE);
                } else {
                    String userDetails = sharedPreferences.getString(usernameString + passwordString + "data", "Invalid Username or Password.");
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("credentials", userDetails);
                    editor.commit();

                    Intent goToHomeScreen = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(goToHomeScreen);
                }
            }});

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegistration = new Intent(LoginActivity.this,Register.class);
                startActivity(goToRegistration);
            }
        });
    }
}
