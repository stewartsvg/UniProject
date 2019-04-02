package com.example.stewa.uniproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //opens up navigation menu by clicking on white menu icon
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ImageButton navBarMenuButton = (ImageButton) findViewById(R.id.btn_drawer_menu);
        navBarMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        //buttons on home screen
        Button logoutButton = (Button) findViewById(R.id.btn_home_log_out);
        Button addProductButton = (Button) findViewById(R.id.btn_home_add_product);
        Button editOrRemoveProductButton = (Button) findViewById(R.id.btn_home_edit_remove_product);
        Button orderProductButton = (Button) findViewById(R.id.btn_home_order_produce);
        //empties credentials and returns to login screen
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              logOut();

            }
        });

        //goes to add product screen
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddProductScreen();

            }
        });

        //goes to edit or remove product screen
        editOrRemoveProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEditOrRemoveProductScreen();

            }
        });

        //goes to order product screen
        orderProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOrderProductScreen();

            }
        });


    }

    public void navBarHome(MenuItem menuItem){
        Intent goToHomeScreen = new Intent(HomeActivity.this,HomeActivity.class);
        startActivity(goToHomeScreen);
        finish();
    }

    public void navBarLogOut(MenuItem menuItem){
       this.logOut();
    }

    public void navBarAddProduct(MenuItem menuItem){
        goToAddProductScreen();
    }

    private void logOut(){
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        //sets credentials to be empty when user logs out
        editor.putString("credentials", "");
        editor.commit();

        Intent goToLoginScreen = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(goToLoginScreen);
        finish();
    }

    private void goToAddProductScreen(){

        Intent goToAddProductScreen = new Intent(HomeActivity.this, ProductAddActivity.class);
        startActivity(goToAddProductScreen);
    }

    private void goToEditOrRemoveProductScreen(){

        Intent goToEditOrRemoveProductScreen = new Intent(HomeActivity.this, EditOrRemoveProductActivity.class);
        startActivity(goToEditOrRemoveProductScreen);
    }

    private void goToOrderProductScreen(){
        Intent goToOrderProductScreen = new Intent(HomeActivity.this, OrderProductActivity.class);
        startActivity(goToOrderProductScreen);
    }
}
