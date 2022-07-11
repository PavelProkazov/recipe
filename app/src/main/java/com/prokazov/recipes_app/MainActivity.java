package com.prokazov.recipes_app;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView logo;
    private DatabaseReference  fdb = FirebaseDatabase.getInstance().getReference();
    private EditText input_product;
    private Button add_button;
    private TextView product_list;
    private TextView recipes;
    private DBHelper dbHelper;
    private HashSet setProduct = new HashSet();

    private Button clear_button;
    private HashMap<String, String> foodRecipesMap = new HashMap<>();

    private HashMap<String,String> ii_map = new HashMap<>();
    private String RECIPES_KEY = "Recipes";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren())
                {

                    foodRecipesMap.put(ds.getKey(), Objects.requireNonNull(ds.getValue()).toString().replaceAll("\\\\n","\n").replaceAll("\\\\b","\b").replaceAll("\\+",""));

                  System.out.println("string b  "+ds);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                recipes.setText(R.string.no_db);
            }

        }

        );







        input_product = findViewById(R.id.input_product);
        add_button = findViewById(R.id.add_button);
        product_list = findViewById(R.id.product_list);
        recipes = findViewById(R.id.recipes);
       // dbHelper = new DBHelper(this);
        clear_button = findViewById(R.id.clear_button);
        Set<SpannableStringBuilder> values = new HashSet();
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("Рецепты\n");




        /*foodRecipesMap.put("Овсяные хлопья Вода Изюм и другие сухофрукты Белок Яблоко Какао-порошок Кокосовая стружка",

                "Шоколадное овсяное печенье\n" +
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
*/

        add_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (input_product.getText().toString().trim().equals("")) {

                    Toast.makeText(MainActivity.this, R.string.no_text_input, Toast.LENGTH_LONG).show();
                   /* String tok = "ttt www ooo ccc";
                    Spannable span = new SpannableString(tok);

                    SpannableStringBuilder builder = new SpannableStringBuilder();

                    String content = tok.replace("ooo", "eee");
                    span.setSpan(new StyleSpan(Typeface.BOLD), 0, tok.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.append(span);
                    builder.append(content);

                    recipes.setText(builder);*/
                }
                else {
                    setProduct.add(input_product.getText().toString().trim());

                    ArrayList<String> arr_Product = new ArrayList(setProduct);

                    product_list.setText("Список продуктов: \n" + setProduct.toString().replaceAll("^\\[|\\]$", ""));
                    values.clear();


                        //System.out.println(arr_Product);


                   for (Map.Entry<String, String> entry : foodRecipesMap.entrySet()) {
                        String key = entry.getKey().toLowerCase();
                        String value = entry.getValue();
                        String productsSets = setProduct.toString().toLowerCase();
                        Iterator<String> it_set = setProduct.iterator();
                        String[] tokens;






                        if (setProduct.size() == 1) {
                            if (key.contains(arr_Product.get(0).toLowerCase())) {

                                tokens = value.split("\n");
                                String tok = tokens[0]+"\n";
                                Spannable span = new SpannableString(tok);



                                    String content = value.replaceFirst(tok,"").trim().replaceAll("^\\[|\\]$", "")+"\n\n";
                                span.setSpan(new StyleSpan(Typeface.BOLD),0,tok.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    builder.append(span);
                                builder.append(content);


                                values.add(builder);


                                recipes.setText(builder);
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText(builder);
                        }
                        } else if (setProduct.size() == 2) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase())) {

                                tokens = value.split("\n");
                                String tok = tokens[0]+"\n";
                                Spannable span = new SpannableString(tok);



                                String content = value.replaceFirst(tok,"").trim().replaceAll("^\\[|\\]$", "")+"\n\n";
                                span.setSpan(new StyleSpan(Typeface.BOLD),0,tok.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                builder.append(span);
                                builder.append(content);

                                values.add(builder);
                                recipes.setText(R.string.no_data);
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText(builder);}
                        } else if (setProduct.size() == 3) {
                            if (key.contains((arr_Product.get(0).toLowerCase())) &&( key.contains(arr_Product.get(1).toLowerCase())) && (key.contains(arr_Product.get(2).toLowerCase()))) {

                                tokens = value.split("\n");
                                String tok = tokens[0]+"\n";
                                Spannable span = new SpannableString(tok);



                                String content = value.replaceFirst(tok,"").trim().replaceAll("^\\[|\\]$", "")+"\n\n";
                                span.setSpan(new StyleSpan(Typeface.BOLD),0,tok.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                builder.append(span);
                                builder.append(content);

                                values.add(builder);
                                recipes.setText(builder);
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText(builder);
                            }
                        } else if (setProduct.size() == 4) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase()) && key.contains(arr_Product.get(2).toLowerCase()) && key.contains(arr_Product.get(3).toLowerCase())) {

                                tokens = value.split("\n");
                                String tok = tokens[0]+"\n";
                                Spannable span = new SpannableString(tok);



                                String content = value.replaceFirst(tok,"").trim().replaceAll("^\\[|\\]$", "")+"\n\n";
                                span.setSpan(new StyleSpan(Typeface.BOLD),0,tok.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                builder.append(span);
                                builder.append(content);

                                values.add(builder);
                                recipes.setText(builder);

                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText(builder);
                            }
                        } else if (setProduct.size() == 5) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase()) && key.contains(arr_Product.get(2).toLowerCase())&& key.contains(arr_Product.get(3).toLowerCase())&& key.contains(arr_Product.get(4).toLowerCase())) {


                                tokens = value.split("\n");
                                String tok = tokens[0]+"\n";
                                Spannable span = new SpannableString(tok);



                                String content = value.replaceFirst(tok,"").trim().replaceAll("^\\[|\\]$", "")+"\n\n";
                                span.setSpan(new StyleSpan(Typeface.BOLD),0,tok.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                builder.append(span);
                                builder.append(content);

                                values.add(builder);
                                recipes.setText(builder);
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText(builder);
                            }
                        } else if (setProduct.size() == 6) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase()) && key.contains(arr_Product.get(2).toLowerCase()) && key.contains(arr_Product.get(3).toLowerCase())&& key.contains(arr_Product.get(4).toLowerCase())&& key.contains(arr_Product.get(5).toLowerCase())) {

                                tokens = value.split("\n");
                                String tok = tokens[0]+"\n";
                                Spannable span = new SpannableString(tok);



                                String content = value.replaceFirst(tok,"").trim().replaceAll("^\\[|\\]$", "")+"\n\n";
                                span.setSpan(new StyleSpan(Typeface.BOLD),0,tok.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                builder.append(span);
                                builder.append(content);

                                values.add(builder);
                                recipes.setText(builder);
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText(builder);
                            }
                        } else if (setProduct.size() == 7) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase()) && key.contains(arr_Product.get(2).toLowerCase())&& key.contains(arr_Product.get(3).toLowerCase())&& key.contains(arr_Product.get(4).toLowerCase())&& key.contains(arr_Product.get(5).toLowerCase())&& key.contains(arr_Product.get(6).toLowerCase())) {

                                tokens = value.split("\n");
                                String tok = tokens[0]+"\n";
                                Spannable span = new SpannableString(tok);



                                String content = value.replaceFirst(tok,"").trim().replaceAll("^\\[|\\]$", "")+"\n\n";
                                span.setSpan(new StyleSpan(Typeface.BOLD),0,tok.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                builder.append(span);
                                builder.append(content);

                                values.add(builder);
                                recipes.setText(builder);
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText(builder);
                            }
                        } else if (setProduct.size() == 8) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase()) && key.contains(arr_Product.get(2).toLowerCase()) && key.contains(arr_Product.get(3).toLowerCase())&& key.contains(arr_Product.get(4).toLowerCase())&& key.contains(arr_Product.get(5).toLowerCase())&& key.contains(arr_Product.get(6).toLowerCase())&& key.contains(arr_Product.get(7).toLowerCase())) {


                                tokens = value.split("\n");
                                String tok = tokens[0]+"\n";
                                Spannable span = new SpannableString(tok);



                                String content = value.replaceFirst(tok,"").trim().replaceAll("^\\[|\\]$", "")+"\n\n";
                                span.setSpan(new StyleSpan(Typeface.BOLD),0,tok.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                builder.append(span);
                                builder.append(content);

                                values.add(builder);
                                recipes.setText(builder);
                            }

                        } else if (setProduct.size() == 9) {
                            Toast.makeText(MainActivity.this, R.string.max_of_product, Toast.LENGTH_LONG).show();
                            values.clear();
                            builder.clear();
                            recipes.setText(R.string.max_of_product);
                            input_product.setEnabled(false);
                        }

                    }
                        /*while (it_set.hasNext()) {

                            if (key.contains(it_set.next().toLowerCase())) {

                                values.add(value + "\n\n");
                                ii_map.put(key,value);

                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));

                            } else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                        }*/
                    }


                    values.clear();
                    builder.clear();
                    input_product.setText("");


            }
        });
       // dbHelper.close();


        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_product.setText("");
                product_list.setText("");
                recipes.setText("");
                setProduct.clear();
                values.clear();
                builder.clear();

                input_product.setEnabled(true);



            }
        });


    }

}
