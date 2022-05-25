package com.prokazov.recipes_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="recipeDb";
    public static final String PRODUCTS_TABLE="products";

    public static final String KEY_ID_PRODUCT="_id";
    public static final String KEY_PRODUCT_NAME="product";



    public static final String RECIPES_TABLE="recipes";

    public static final String KEY_ID="_id";
    public static final String KEY_PRODUCT="product";
    public static final String KEY_RECIPE="recipe";



    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE " +RECIPES_TABLE+ "(" +KEY_ID+ "integer primary key,"
                + KEY_RECIPE+"text," + KEY_PRODUCT+"text"+")");

        db.execSQL("CREATE TABLE " +PRODUCTS_TABLE+ "(" +KEY_ID_PRODUCT+ "integer primary key,"
                + KEY_PRODUCT_NAME+"text"+")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+RECIPES_TABLE);
        db.execSQL("drop table if exists "+PRODUCTS_TABLE);


    }
}
