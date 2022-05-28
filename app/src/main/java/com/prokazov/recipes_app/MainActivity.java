package com.prokazov.recipes_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    private Map<String,String> foodRecipesMap = new HashMap<>();



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
        Set values = new HashSet();

        foodRecipesMap.put("Овсяные хлопья www Вода Изюм и другие сухофрукты Белок Яблоко Какао-порошок Кокосовая стружка",

                        "Шоколадное овсяное печенье" +
                "на 100грамм - 193.65 ккал, Б/Ж/У - 6.45/4.07/35.53\n" +
                " \n" +
                "Ингредиенты:\n" +
                "- Овсяные хлопья — 200 г\n" +
                "- Вода — 70 мл\n" +
                "- Изюм и другие сухофрукты — 60 г\n" +
                "- Белок — 2 шт.\n" +
                "- Яблоко — 1 шт.\n" +
                "- Какао-порошок — 2-3 ч.л.\n" +
                "- Кокосовая стружка — по вкусу и по желанию\n" +
                " \n" +
                "Приготовление:\n" +
                "1. Половину овсяных хлопьев измельчить в блендере (в кофемолке) и залить водой, чтобы получилась густая масса.\n" +
                "2. Добавить изюм, цукаты, кокосовую стружку, какао-порошок.\n" +
                "3. Яблоко натереть.\n" +
                "4. Смешать хорошо все компоненты.\n" +
                "5. Форму выложить бумагой для запекания.\n" +
                "6. Чайной ложкой брать тесто, формировать шарики (лучше чуть смочить руки водой), приплюснуть и выкладывать в форму или на противень.\n" +
                "7. Выпекать 20 минут при 200*С.\n" +
                "Можно добавить немного воды на дно противня чтобы печенье не было слишком сухим. Но если любите похрустеть, то эту процедуру можно опустить.");

        foodRecipesMap.put("www","testtesttesttesttes\nttesttesttesttesttesttesttesttestte\nsttesttesttesttesttesttesttestte\nsttesttesttesttest\ntesttesttesttesttesttesttest");
        foodRecipesMap.put("Ингредиенты:","Самрецепт в любой виде форматирования ");
        

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(input_product.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this, R.string.no_text_input, Toast.LENGTH_LONG).show();
                else {
                    setProduct.add(input_product.getText().toString().trim());
                    product_list.setText(setProduct.toString());

                   for(Map.Entry<String, String> entry : foodRecipesMap.entrySet()) {
                       String key = entry.getKey().toLowerCase();
                       String value = entry.getValue().toLowerCase();
                      String productsSets = setProduct.toString().toLowerCase();
                      String it_set = setProduct.iterator().next().toString();
                  if(key.contains(setProduct.iterator().next().toString()))
                  {

                      values.add(value+"\n\n");
                      recipes.setText(values.toString().trim());
                  }else if(values.isEmpty()) {  Log.d("mLog","No DATA");
                      recipes.setText(R.string.no_data);
                                        } else recipes.setText(values.toString().trim());
                   }
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
                values.clear();


            }
        });

    }

    public class RecipesData {



    }

}
