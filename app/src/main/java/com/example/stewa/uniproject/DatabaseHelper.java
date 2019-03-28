package com.example.stewa.uniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

   private static final String productTableName = "PRODUCTS";
   private static final String productID = "Product_ID";
   private static final String productName = "Product_Name";
   private static final String productDescription = "Description";
   private static final String productWeight = "Product_Weight";
   private static final String productItemCost = "Item_Cost";
   private static final String productStock = "Stock_Remaining";
   private static final String productProducerName = "Producer_Name";

    public DatabaseHelper(Context context) {
        super(context, productTableName, null,1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ productTableName);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE "+productTableName+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                        productName+" TEXT,"+
                                        productDescription+" TEXT,"+
                                        productWeight+" REAL,"+
                                        productItemCost+" REAL,"+
                                        productStock+" INTEGER,"+
                                        productProducerName+" TEXT)";
        db.execSQL(createTableStatement);
    }

    public boolean addProduct(ArrayList productDetails){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues productContentValues = new ContentValues();

        //productContentValues.put(productName, String.valueOf);
        return true;
    }

}
