package com.example.stewa.uniproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EditOrRemoveProductActivity extends AppCompatActivity {
    
    private DrawerLayout drawerLayout;
    private ListView productListView;
    //added private
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_remove_product);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ImageButton navBarMenuButton = (ImageButton) findViewById(R.id.btn_edit_product_drawer_menu);
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

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        final String currentUserCredentials = sharedPreferences.getString("credentials", null);
        final String currentUser = getCurrentUserNameFromSharedPrefs(currentUserCredentials);

        productListView = (ListView) findViewById(R.id.product_list_view);
        ArrayList<String> productList = new ArrayList<>();
        dbHelper = new DatabaseHelper(EditOrRemoveProductActivity.this);
        Cursor productResults = dbHelper.getProductsForCurrentUser(currentUser);

        if (productResults.getCount() == 0) {
            displayNoProductsFoundError("You have not added any products, or a database " +
                    "error has occurred.", "No Products Added");
        } else {
            while (productResults.moveToNext()) {
                productList.add(productResults.getString(0) + "\n" + productResults.getString(1) + "\n" + productResults.getString(2) + "\n" +
                        productResults.getString(3) + "\n" + productResults.getString(4) + "\n" +
                        productResults.getString(5));
                ListAdapter productListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
                productListView.setAdapter(productListAdapter);
            }
        }

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String productListItemValue = (String) productListView.getItemAtPosition(position);
                goToProductEdit(productListItemValue);
            }
        });
    }

    public void navBarHome(MenuItem menuItem) {
        Intent goToHomeScreen = new Intent(EditOrRemoveProductActivity.this, HomeActivity.class);
        startActivity(goToHomeScreen);
        finish();
    }

    public void navBarLogOut(MenuItem menuItem) {
        this.logOut();
    }

    public void navBarAddProduct(MenuItem menuItem) {
        goToAddProductScreen();
    }

    private void goToAddProductScreen() {

        Intent goToAddProductScreen = new Intent(EditOrRemoveProductActivity.this, ProductAddActivity.class);
        startActivity(goToAddProductScreen);
    }

    private void logOut() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        //sets credentials to be empty when user logs out
        editor.putString("credentials", "");
        editor.commit();

        Intent goToLoginScreen = new Intent(EditOrRemoveProductActivity.this, LoginActivity.class);
        startActivity(goToLoginScreen);
        finish();
    }

    //opens ProductEditActivity and passes the value of the product clicked on the product list
    private void goToProductEdit(String productListItemValue) {
        Intent goToProductEditScreen = new Intent(EditOrRemoveProductActivity.this, ProductEditActivity.class);
        String productDetails = productListItemValue;
        Bundle bundle = new Bundle();
        bundle.putString("productDetails", productDetails);
        goToProductEditScreen.putExtras(bundle);
        startActivity(goToProductEditScreen);
    }

    //looks through shared preferences to get username of currently logged in user
    private String getCurrentUserNameFromSharedPrefs(String currentUserDetails) {

        System.out.println(currentUserDetails);
        int indexValue = currentUserDetails.indexOf("\n");
        if (indexValue == -1) {
            System.out.println("oops, error getting current user name from shared preferences.");
            return "";
        } else {
            String currentUserName = currentUserDetails.substring(0, indexValue);
            System.out.println("producer name: " + currentUserName);
            return currentUserName;
        }
    }

    //this should be displayed if there are no products added by the currently logged in user
    private void displayNoProductsFoundError(String errorMessage, String errorName) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(errorMessage);
        alertDialogBuilder.setTitle(errorName);
        alertDialogBuilder.setCancelable(true);
    }
}
