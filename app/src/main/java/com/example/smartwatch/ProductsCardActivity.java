package com.example.smartwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductsCardActivity extends AppCompatActivity {

    RecyclerView rv;
    private ProductAdapter adapter;
    public static final String PRODUCT_ID_KEY = "product_key";
    public static final String TABLE_NAME_KEY = "table_name_key";
    TextView tv_product_name;
    Mydatabase db;
    private String table_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_card);

        rv = findViewById(R.id.rv_products);
        tv_product_name = findViewById(R.id.tv_product_name);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.zoom_element);
        tv_product_name.setAnimation(animation);

        db = new Mydatabase(this);

        Intent intent = getIntent();
        String name;
        switch (MainActivity.name_data){
            case "Đồng hồ cơ":
                name = intent.getStringExtra(MainActivity.DHC_KEY);
                tv_product_name.setText(name);
                table_name = Mydatabase.TB_DHC;
                break;
            case "SmartWatch":
                name = intent.getStringExtra(MainActivity.DHDT_KEY);
                tv_product_name.setText(name);
                table_name = Mydatabase.TB_DHTM;
                break;
            case "Đông hồ điện tử":
                name = intent.getStringExtra(MainActivity.DHTM_KEY);
                tv_product_name.setText(name);
                table_name = Mydatabase.TB_DHDT;
                break;
        }
        MainActivity.name_data = "";

        ArrayList<Products> products = new ArrayList<>();
        products = db.getAllProducts(table_name);
        adapter = new ProductAdapter(products, new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(int productId) {
                Intent i = new Intent(getBaseContext(),DisplayProductsActivity.class);
                i.putExtra(PRODUCT_ID_KEY,productId);
                i.putExtra(TABLE_NAME_KEY,table_name);
                HomeActivity.flag = false;
                startActivity(i);
            }
        });
        RecyclerView.LayoutManager lm = new GridLayoutManager(this,2);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

    }

    @SuppressLint("ResourceAsColor")
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.main_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Products> product = db.getProductForSearch(query,table_name);
                adapter.setProducts(product);
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Products> product = db.getProductForSearch(newText,table_name);
                adapter.setProducts(product);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ArrayList<Products> product = db.getAllProducts(table_name);
                adapter.setProducts(product);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }

}