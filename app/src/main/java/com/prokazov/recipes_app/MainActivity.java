package com.prokazov.recipes_app;

import android.os.Bundle;
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
        HashSet values = new HashSet();




        foodRecipesMap.put("Овсяные хлопья www Вода Изюм и другие сухофрукты Белок Яблоко Какао-порошок Кокосовая стружка",

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

        foodRecipesMap.put("www", "testtesttesttesttes\nttesttesttesttesttesttesttesttestte\nsttesttesttesttesttesttesttestte\nsttesttesttesttest\ntesttesttesttesttesttesttest");
        foodRecipesMap.put("Ингредиенты:", "Самрецепт в любой виде форматирования ");
        foodRecipesMap.put("Банан тыквенные семечки кунжут коричневый лён",

                "\nПеченье из семян\n" +
                        "Ингредиенты:\n " +
                        "Банан 1шт, \n" +
                        "тыквенные семечки 100гр, \n" +
                        "кунжут 100гр, \n" +
                        "коричневый лён 50гр>:\n" +
                        "     Приготовление: 1.Нарезаем банан, измельчаем блендером. \n" +
                        "2. В чашку насыпаем тыквенные семечки, кунжут, лён\n" +
                        "3. Добавляем банан и перемешиваем\n" +
                        "4. Формируем печенье и выкладываем на смазанный маслом противень\n" +
                        "5. Запекаем в духовке 15-20 минут при 180С\n");
        foodRecipesMap.put("Маш картофель www морковь лук имбирь куркума помидор", "\n Суп из маша Ингредиенты:Маш 200гр" + "Картофель 3шт," +
                "Морковь 1шт,\n " +
                "Лук 1шт,\n " +
                "Имбирь - 2ст.л.,\n " +
                "Куркума - 0,5ч.л.,\n " +
                "Помидор 1шт\n" +
                "\nПриготовление:\n" +
                "1. Маш промыть и замочить на несколько часов\n" +
                "2. Затем поменять воду и поставить на медленный огонь минут 30, чтобы маш разварился\n" +
                "3. Добавить картофель \n" +
                "4. Обжаренный лук с морковью добавляем минут через 5-7\n" +
                "5. Через 3 минуты добавляем мелконарезанный имбирь\n" +
                "6. Когда картошка сварится добавляем помидор,соль, перец - по вкусу\n" +
                "7. Когда снимаем с огня добавляем куркуму\n");
        foodRecipesMap.put("Мука зеленой гречки ореховая мука кокосовый сахар какао-порошок без сахара (кэроб) сода соль ", "Шоколадные кексы с жидким центром Ингредиенты: Сухие ингредиенты\n" +
                "•  100 гр мука зеленой гречки (или любой другой муки без глютена, можно смесь)\n" +
                "•  50 гр ореховая мука (можно смолоть орехи самостоятельно)\n" +
                "•  70 гр кокосовый сахар\n" +
                "•  20 гр какао-порошок без сахара (а также можно заменить какао на кэроб)\n" +
                "•  0,5 ч.л сода\n" +
                "•  щепотка соль\n" +
                "Жидкие ингредиенты\n" +
                "•  80 гр яблочное пюре (с мякотью банана, получилось тоже великолепно)\n" +
                "•  180 мл кокосовое молоко (или любого другого растительного молока)\n" +
                "•  1 ст.л яблочный уксус или сок лимона\n" +
                "Начинка\n" +
                "•  60 гр тёмный веганский шоколад\n" + "\nПриготовление: 1.  В чашу блендера или комбайна поместите оба вида муки, кокосовый сахар, какао-порошок, соду и щепотку соли. Перемешайте данные ингредиенты.\n" +
                "2.  Тесто получится довольно жидким и липким. Разложите тесто по формочкам, заранее смазанным каплей кокосового масла. Наполняйте формочки примерно на 3/4. В серединку каждого кекса положите кусочек шоколада и утопите его в тесте.\n" +
                "3.  Выпекайте кексы в заранее разогретой до 180 °С духовке в течение 20 минут.\n" +
                "4.  Готовые кексы остудите около 10-15 минут при комнатной температуре. Выньте из формочек и подавайте.");


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (input_product.getText().toString().trim().equals(""))
                    Toast.makeText(MainActivity.this, R.string.no_text_input, Toast.LENGTH_LONG).show();
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




                        if (setProduct.size() == 1) {
                            if (key.contains(arr_Product.get(0).toLowerCase())) {

                                values.add(value + "\n\n");
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                        }
                        } else if (setProduct.size() == 2) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase())) {

                                values.add(value + "\n\n");
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }
                        } else if (setProduct.size() == 3) {
                            if (key.contains((arr_Product.get(0).toLowerCase())) &&( key.contains(arr_Product.get(1).toLowerCase())) && (key.contains(arr_Product.get(2).toLowerCase()))) {

                                values.add(value + "\n\n");
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }
                        } else if (setProduct.size() == 4) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase()) && key.contains(arr_Product.get(2).toLowerCase()) && key.contains(arr_Product.get(3).toLowerCase())) {

                                values.add(value + "\n\n");
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }
                        } else if (setProduct.size() == 5) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase()) && key.contains(arr_Product.get(2).toLowerCase())&& key.contains(arr_Product.get(3).toLowerCase())&& key.contains(arr_Product.get(4).toLowerCase())) {

                                values.add(value + "\n\n");
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }
                        } else if (setProduct.size() == 6) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase()) && key.contains(arr_Product.get(2).toLowerCase()) && key.contains(arr_Product.get(3).toLowerCase())&& key.contains(arr_Product.get(4).toLowerCase())&& key.contains(arr_Product.get(5).toLowerCase())) {

                                values.add(value + "\n\n");
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }
                        } else if (setProduct.size() == 7) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase()) && key.contains(arr_Product.get(2).toLowerCase())&& key.contains(arr_Product.get(3).toLowerCase())&& key.contains(arr_Product.get(4).toLowerCase())&& key.contains(arr_Product.get(5).toLowerCase())&& key.contains(arr_Product.get(6).toLowerCase())) {

                                values.add(value + "\n\n");
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }else if (values.isEmpty()) {
                                Log.d("mLog", "No DATA");

                                recipes.setText(R.string.no_data);

                            } else {
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }
                        } else if (setProduct.size() == 8) {
                            if (key.contains(arr_Product.get(0).toLowerCase()) && key.contains(arr_Product.get(1).toLowerCase()) && key.contains(arr_Product.get(2).toLowerCase()) && key.contains(arr_Product.get(3).toLowerCase())&& key.contains(arr_Product.get(4).toLowerCase())&& key.contains(arr_Product.get(5).toLowerCase())&& key.contains(arr_Product.get(6).toLowerCase())&& key.contains(arr_Product.get(7).toLowerCase())) {

                                values.add(value + "\n\n");
                                recipes.setText("\nРецепты:\n" + values.toString().trim().replaceAll("^\\[|\\]$", ""));
                            }

                        } else if (setProduct.size() == 9) {
                            Toast.makeText(MainActivity.this, R.string.max_of_product, Toast.LENGTH_LONG).show();
                            values.clear();

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

                input_product.setEnabled(true);



            }
        });


    }

    public class RecipesData extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("recipe")) {

                foodRecipesMap.put(attributes.getValue("products"), attributes.getValue("frecipe"));

            }


        }

    }
}
