package com.prokazov.recipes_app;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView logo;
    private EditText input_product;
    private Button add_button;
    private TextView product_list;
    private TextView recipes;
    private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_product = findViewById(R.id.input_product);
        add_button = findViewById(R.id.add_button);
        product_list = findViewById(R.id.product_list);
        recipes = findViewById(R.id.recipes);
        dbHelper = new DBHelper(this);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_product.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this, R.string.no_text_input, Toast.LENGTH_LONG).show();
                else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();

                    db.insert(dbHelper.RECIPES_TABLE,null, contentValues);


                }


            }

        });
    }

    public class RecipesData {



    }

}
