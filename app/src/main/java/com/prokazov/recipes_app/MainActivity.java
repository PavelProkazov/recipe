package com.prokazov.recipes_app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView logo;
    private EditText input_product;
    private Button add_button;
    private TextView product_list;
    private TextView recipes;
    private DBHelper dbHelper;
    private Set setProduct = new HashSet();
    private Button clear_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_product = findViewById(R.id.input_product);
        add_button = findViewById(R.id.add_button);
        product_list = findViewById(R.id.product_list);
        recipes = findViewById(R.id.recipes);
        dbHelper = new DBHelper(this);
        clear_button = findViewById(R.id.clear_button);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setProduct.add(input_product.getText().toString().trim());

                product_list.setText(setProduct.toString());


                if(input_product.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this, R.string.no_text_input, Toast.LENGTH_LONG).show();
                else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();

                    db.insert(dbHelper.RECIPES_TABLE,null, contentValues);

                    Cursor cursor = db.query(dbHelper.RECIPES_TABLE,null,null,null,null,null,null,null);

                        if(cursor.moveToFirst()){
                            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                            int productIndex = cursor.getColumnIndex(DBHelper.KEY_PRODUCT);
                            int recipeIndex = cursor.getColumnIndex(DBHelper.KEY_RECIPE);

                            do {
                                Log.d("mLog", "ID="+cursor.getInt(idIndex)+
                                        ", products= "+ cursor.getString(productIndex)+
                                        ", recipes = " + cursor.getString(recipeIndex));

                            } while (cursor.moveToNext());


                                                    }
                        else {  Log.d("mLog","No DATA");
                            recipes.setText(R.string.no_data);
                        }

                        cursor.close();

                    input_product.setText("");


                }


            }

        });
        dbHelper.close();


        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_product.setText("");
                product_list.setText("");
                recipes.setText("");
                setProduct.clear();


            }
        });

    }

    public class RecipesData {



    }

}
