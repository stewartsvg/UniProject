package com.example.stewa.uniproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_address);

        final EditText newName = (EditText) findViewById(R.id.etNewName);
        final EditText walletAddress = (EditText) findViewById(R.id.etWalletAddress);
        final EditText newPassword = (EditText) findViewById(R.id.etNewPassword);
        final EditText confirmedPassword = (EditText) findViewById(R.id.etConfirmPassword);
        Button confirmRegistrationButton = (Button) findViewById(R.id.btnRegisterCreate);

        confirmRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String newUsername = newName.getText().toString();
                String walletAddressString = walletAddress.getText().toString();
                String newPasswordString = newPassword.getText().toString();
                String confirmedPasswordString = confirmedPassword.getText().toString();

                //checks is any text fields are empty
                if (isFieldEmpty(newName.getText())||isFieldEmpty(walletAddress.getText())||isFieldEmpty(newPassword.getText())){
                    TextView emptyFieldsError = findViewById(R.id.tvEmptyFieldsError);
                    emptyFieldsError.setVisibility(View.VISIBLE);
                }else{

                    if (passwordsMatch(newPasswordString,confirmedPasswordString)){
                        editor.putString(newUsername+newPasswordString+"data",newUsername+"\n"+walletAddressString);
                        editor.commit();

                        Intent goToLogin = new Intent(Register.this,LoginActivity.class);
                        startActivity(goToLogin);
                    }else{
                       TextView passwordMatchError = findViewById(R.id.tvPasswordMatchError);
                       passwordMatchError.setVisibility(View.VISIBLE);
                    }
                 }
            }
        });
    }
        //checks if the new password matches the confirmed password
        private boolean passwordsMatch(String newPassword,String confirmedPassword){
        boolean passwordMatches = false;

        if(newPassword.equals(confirmedPassword)){
            passwordMatches=true;
        }
        return passwordMatches;
    }

        private boolean isFieldEmpty(Editable textField){
        if(TextUtils.isEmpty(textField)){
            return true;
        }else return false;
}
}

