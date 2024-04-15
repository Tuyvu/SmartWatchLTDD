package com.example.smartwatch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CardView DHC,DHTM,DHDT;
    TextView tv_DHC,tv_DHTM,tv_DHDT;
    public static final String DHC_KEY = "DHC_key";
    public static final String DHTM_KEY = "DHTM_key";
    public static final String DHDT_KEY = "DHDT_key";


    public static String name_data ="";

    Mydatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DHC = findViewById(R.id.DHC);
        DHDT = findViewById(R.id.DHDT);
        DHTM = findViewById(R.id.DHTM);
        tv_DHC = findViewById(R.id.tv_electronics_card);
        tv_DHDT = findViewById(R.id.tv_laptop_card);
        tv_DHTM = findViewById(R.id.tv_mobile_card);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Products");


        db = new Mydatabase(this);
//        Products p = new Products(R.drawable.python,"python",32.0,"Electronic Book",23,"Python is a computer programming language often used to build websites and software, automate tasks, and conduct data analysis.",0);
//
//        db.insertProduct(p,ShoppingDatabase.TB_BOOK);

        DHC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_data = tv_DHC.getText().toString();
                Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
                intent.putExtra(DHC_KEY,tv_DHC.getText().toString());
                startActivity(intent);
            }
        });

        DHDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_data = tv_DHDT.getText().toString();
                Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
                intent.putExtra(DHDT_KEY,tv_DHDT.getText().toString());
                startActivity(intent);
            }
        });

        DHTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_data = tv_DHTM.getText().toString();
                Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
                intent.putExtra(DHTM_KEY,tv_DHTM.getText().toString());
                startActivity(intent);
            }
        });

    }
}