package com.example.stewa.uniproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class OrderConfirmationActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private DatabaseHelper productDBHelper;
    private Double transactionTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        //opens up navigation menu by clicking on white menu icon

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ImageButton navBarMenuButton = (ImageButton) findViewById(R.id.btn_order_confirm_product_drawer_menu);
        navBarMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        //gets product selected in previous page from its passed on productID
        Bundle bundle = getIntent().getExtras();
        String productID = bundle.getString("productID");
        productDBHelper = new DatabaseHelper(OrderConfirmationActivity.this);
        final Product selectedProduct = productDBHelper.getProductByID(productID);

        final TextView tvEmptyQuantityFieldError = (TextView) findViewById(R.id.tv_product_order_confirm_empty_quantity_error);
        final TextView tvTotalTransactionCost = (TextView) findViewById(R.id.tv_product_order_total_cost);
        final TextView tvProductName = (TextView) findViewById(R.id.tv_order_product_name);
        final TextView tvProductDescription = (TextView) findViewById(R.id.tv_order_product_description);
        final TextView tvProductWeight = (TextView) findViewById(R.id.tv_order_product_weight);
        final TextView tvProductCost = (TextView) findViewById(R.id.tv_order_product_cost);
        final TextView tvProductStock = (TextView) findViewById(R.id.tv_order_product_current_stock);
        final TextView tvProductProducerName = (TextView) findViewById(R.id.tv_order_product_producer_name);
        final EditText etQuantity = (EditText) findViewById(R.id.et_order_product_quantity);

        //populates textviews with fields of previously selected productID
        tvProductName.setText("Name: " + selectedProduct.getName());
        tvProductDescription.setText("Description: " + selectedProduct.getDescription());
        tvProductWeight.setText("Weight (in KG): " + Double.toString(selectedProduct.getWeight()));
        tvProductCost.setText("Cost per Item (in Ether): " + Double.toString(selectedProduct.getItemCost()));
        tvProductStock.setText("Stock Remaining: " + Integer.toString(selectedProduct.getStock()));
        tvProductProducerName.setText("Sold by: " + selectedProduct.getProducer());

        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvTotalTransactionCost.setVisibility(View.INVISIBLE);
            }

            //sets transaction total to be the cost per item multiplied by quantity selected
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvTotalTransactionCost.setVisibility(View.VISIBLE);
                if (!etQuantity.getText().toString().equals("")) {
                    transactionTotal = Integer.parseInt(etQuantity.getText().toString()) * selectedProduct.getItemCost();
                    String transactionTotalString = "Total: " + transactionTotal.toString();
                    tvTotalTransactionCost.setText(transactionTotalString);
                } else {
                    String emptyQuantityTotal = "Please Enter a Quantity";
                    tvTotalTransactionCost.setText(emptyQuantityTotal);
                }
            }

            //sets transaction total to be the cost per item multiplied by quantity selected
            @Override
            public void afterTextChanged(Editable s) {
                tvTotalTransactionCost.setVisibility(View.VISIBLE);
                if (!etQuantity.getText().toString().equals("")) {
                    transactionTotal = Integer.parseInt(etQuantity.getText().toString()) * selectedProduct.getItemCost();
                    String transactionTotalString = "Total: " + transactionTotal.toString()+" Ether";
                    tvTotalTransactionCost.setText(transactionTotalString);
                } else {
                    String emptyQuantityTotal = "Please Enter a Quantity";
                    tvTotalTransactionCost.setText(emptyQuantityTotal);
                }
            }
        });
    }

    public void navBarHome(MenuItem menuItem) {
        Intent goToHomeScreen = new Intent(OrderConfirmationActivity.this, HomeActivity.class);
        startActivity(goToHomeScreen);
        finish();
    }

    public void navBarLogOut(MenuItem menuItem) {
        this.logOut();
    }

    //returns to login screen and empties credentials
    private void logOut() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        //sets credentials to be empty when user logs out
        editor.putString("credentials", "");
        editor.commit();

        Intent goToLoginScreen = new Intent(OrderConfirmationActivity.this, LoginActivity.class);
        startActivity(goToLoginScreen);
        finish();
    }
}
