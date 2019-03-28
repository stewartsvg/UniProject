package com.example.stewa.uniproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

public class ProductAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS",MODE_PRIVATE);

        final EditText etProductName = (EditText) findViewById(R.id.et_product_name);
        final EditText etProductDescription = (EditText) findViewById(R.id.et_product_description);
        final EditText etProductItemCost = (EditText) findViewById(R.id.et_product_item_cost);
        final EditText etProductStock = (EditText) findViewById(R.id.et_product_stock);
        final EditText etProductWeight = (EditText) findViewById(R.id.et_product_weight);

        String productName = etProductName.getText().toString();
        String productDescription = etProductDescription.getText().toString();
        String productItemCost = etProductItemCost.getText().toString();
        String productStock = etProductStock.getText().toString();
        String productWeight= etProductWeight.getText().toString();
        //String producer = sharedPreferences.getString();
        //Product productToAdd = new Product(productName,productDescription,productItemCost,productStock,productWeight,);
    }

    public void navBarHome(MenuItem menuItem){
        Intent goToHomeScreen = new Intent(ProductAddActivity.this,HomeActivity.class);
        startActivity(goToHomeScreen);
        finish();
    }

    public void navBarLogOut(MenuItem menuItem){
        this.logOut();
    }

    private void logOut(){
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        //sets credentials to be empty when user logs out
        editor.putString("credentials", "");
        editor.commit();

        Intent goToLoginScreen = new Intent(ProductAddActivity.this, LoginActivity.class);
        startActivity(goToLoginScreen);
        finish();
    }
}
