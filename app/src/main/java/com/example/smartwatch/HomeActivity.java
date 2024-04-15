package com.example.smartwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    RecyclerView rv;
    public static final String PRODUCT_KEY = "product_key";
    private static final int PERMISSION_REQ_COD = 1;
    public static final String TABLE_NAME_KEY = "table_key";
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;
    SharedPreferences shp_id;
    SharedPreferences.Editor shpEditor_id;
    public static boolean flag;
    Mydatabase db;
    private NetworkReceiver networkReceiver;
    private BatteryReceiver batteryReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        flag = false;
        db = new Mydatabase(this);
        // int image, String name, double price, String brand, int pieces, String description, double discount, float rating
        Products p = new Products(R.drawable.sp1,"Rolex ",2500,"sony",25,"computer noc Barnes & Noble Retail and Samsung Electronics launched the new Samsung Galaxy Tab 4 Noc tablet in New York.",10,(float) 3.5);
        db.insertProduct(p, Mydatabase.TB_DHC);

        Products p1 = new Products(R.drawable.sp2,"Rolex ",7600,"sony",81,"Sony PlayStation 5 Console + 2 DualSense Wireless Controller+ fifa 21 standard edition All prices include VAT..",25,(float) 3.5);
        db.insertProduct(p1, Mydatabase.TB_DHC);

        Products p2 = new Products(R.drawable.sp3,"Rolex ",4900,"Konami",45,"AUTHENTIC LEAGUES - Fully licensed leagues are coming to PES 2012. Details to be revealed soon!",0,(float) 3.5);
        db.insertProduct(p2, Mydatabase.TB_DHC);

        Products p3 = new Products(R.drawable.sp4,"Rolex ",5850,"acer",32,"Acer Predator Thronos Air, Gaming Throne with Massage Pad and Gaming Chair, Up to 3 Displays, 130 Degrees Recline, LED Lighting, PC Landing Pad, Stabilizing Arm (PC and Monitors Sold Separately)",0,(float) 3.5);
        db.insertProduct(p3, Mydatabase.TB_DHC);

        Products p4 = new Products(R.drawable.sp5,"Zenith ",6000,"acer",51,"different colors gaming Thermaltake U-Fit Black-Red Gaming Chair GGC-UFT-BRMWDS-01 GGC-UFT-BRMWDS-01",0,(float) 3.5);
        db.insertProduct(p4, Mydatabase.TB_DHC);

        Products p5 = new Products(R.drawable.sp6,"Zenith ",800," Powerextra",26,"Powerextra Xbox 360 Controllers,USB Gamepad Wired Controller Improved Ergonomic Joystick Dual Vibration,Compatible with Xbox 360 Slim/Xbox 360/PC(Windows / 7/8.1/10),Black",0,(float) 3.5);
        db.insertProduct(p5, Mydatabase.TB_DHC);
        Products p6 = new Products(R.drawable.sp7,"Zenith ",270,"TOSHIBA",92,"Microwave is an electric device used to heat various types of foods, and this type of oven is different from traditional ovens",0,(float) 3.5);
        db.insertProduct(p6, Mydatabase.TB_DHC);
        Products p7 = new Products(R.drawable.sp8,"Zenith ",210,"TOSHIBA",32,"Hoover Vacuum Cleaner - 2000W, Black - Red TCP2010020 Buy with installments and pay EGP 51.06 for 48 months with select banks.",10,(float) 3.5);
        db.insertProduct(p7, Mydatabase.TB_DHC);
        Products p8 = new Products(R.drawable.sp9,"Washer",690,"TOSHIBA",81,"TOSHIBA Washing Machine Half Automatic Capacity : 12 Kg Max Spin Speed : 1400 RPM With 2 Motors Washing Machine Giant Super Size Works With All Kinds of Regular Powders Vortexes System",0,(float) 3.5);
        db.insertProduct(p8, Mydatabase.TB_DHC);
        Products p9 = new Products(R.drawable.sp11,"Zenith ",45,"Zara",52,"Black t_shirt size XL.",0,(float) 3.5);
        db.insertProduct(p9, Mydatabase.TB_DHTM);
        Products d1 = new Products(R.drawable.sp12,"Zenith ",45,"Zara",52,"Black t_shirt size XL.",0,(float) 3.5);
        db.insertProduct(d1, Mydatabase.TB_DHTM);
        Products d2 = new Products(R.drawable.sp13,"Zenith ",45,"Zara",52,"Black t_shirt size XL.",0,(float) 3.5);
        db.insertProduct(d2, Mydatabase.TB_DHTM);
        Products d3 = new Products(R.drawable.sp14,"Zenith ",45,"Zara",52,"Black t_shirt size XL.",0,(float) 3.5);
        db.insertProduct(d3, Mydatabase.TB_DHTM);
        Products p11 = new Products(R.drawable.d1,"Zenith ",800," Powerextra",26,"Powerextra Xbox 360 Controllers,USB Gamepad Wired Controller Improved Ergonomic Joystick Dual Vibration,Compatible with Xbox 360 Slim/Xbox 360/PC(Windows / 7/8.1/10),Black",0,(float) 3.5);
        db.insertProduct(p11, Mydatabase.TB_DHDT);
        Products p16 = new Products(R.drawable.d2,"Zenith ",800," Powerextra",26,"Powerextra Xbox 360 Controllers,USB Gamepad Wired Controller Improved Ergonomic Joystick Dual Vibration,Compatible with Xbox 360 Slim/Xbox 360/PC(Windows / 7/8.1/10),Black",0,(float) 3.5);
        db.insertProduct(p16, Mydatabase.TB_DHDT);
        Products p12 = new Products(R.drawable.d3,"Zenith ",270,"TOSHIBA",92,"Microwave is an electric device used to heat various types of foods, and this type of oven is different from traditional ovens",0,(float) 3.5);
        db.insertProduct(p12, Mydatabase.TB_DHDT);
        Products p13 = new Products(R.drawable.d4,"Zenith ",210,"TOSHIBA",32,"Hoover Vacuum Cleaner - 2000W, Black - Red TCP2010020 Buy with installments and pay EGP 51.06 for 48 months with select banks.",10,(float) 3.5);
        db.insertProduct(p13, Mydatabase.TB_DHDT);
        Products p14 = new Products(R.drawable.d5,"Washer",690,"TOSHIBA",81,"TOSHIBA Washing Machine Half Automatic Capacity : 12 Kg Max Spin Speed : 1400 RPM With 2 Motors Washing Machine Giant Super Size Works With All Kinds of Regular Powders Vortexes System",0,(float) 3.5);
        db.insertProduct(p14, Mydatabase.TB_DHDT);
        Products p15 = new Products(R.drawable.sp11,"Zenith ",45,"Zara",52,"Black t_shirt size XL.",0,(float) 3.5);
        db.insertProduct(p15, Mydatabase.TB_DHTM);

//        Products p = new Products(15);
//        db.deleteProduct(p,ShoppingDatabase.TB_FASHION);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQ_COD);
        }



        rv = findViewById(R.id.rv_home);
        shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
        shp_id = getSharedPreferences("Preferences_id", MODE_PRIVATE);

        ArrayList<Products> products1 = new ArrayList<>();
        products1 = db.getAllProducts(Mydatabase.TB_PRODUCT_DISCOUNT);
        HomeAdabter adapter = new HomeAdabter(products1, new OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(int productId) {
                Intent i = new Intent(getBaseContext(),DisplayProductsActivity.class);
                i.putExtra(PRODUCT_KEY,productId);
                i.putExtra(TABLE_NAME_KEY, Mydatabase.TB_PRODUCT_DISCOUNT);
                flag = true;
                startActivity(i);
            }
        });
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        bnv = findViewById(R.id.BottomNavigationView);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent1 = new Intent(getBaseContext(),HomeActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.products:
                        Intent intent2 = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.profile:
                        Intent intent3 = new Intent(getBaseContext(),ProfileActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.basket:
                        Intent intent4 = new Intent(getBaseContext(),PurchasesActivity.class);
                        startActivity(intent4);
                        break;
                }

                return true;
            }
        });
        IntentFilter filter;
        networkReceiver = new NetworkReceiver();
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
        // dk pin yeu
        batteryReceiver = new BatteryReceiver();
        filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_up_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                logout();
                break;
            case R.id.settings:
                Intent intent = new Intent(this,SettingActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        try {
            if (shp == null){
                shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
            }
            shpEditor = shp.edit();
            shpEditor.putInt("user", 0);
            shpEditor.commit();

            if (shp_id == null){
                shp_id = getSharedPreferences("Preferences_id", MODE_PRIVATE);
            }
            shpEditor_id = shp_id.edit();
            shpEditor_id.putInt("user_id",0);
            shpEditor_id.commit();

            startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            finish();

        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQ_COD:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                }else{

                }
        }
    }
    public class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo == null || !networkInfo.isConnected()) {
                // Hiển thị cảnh báo mất kết nối internet
                Toast.makeText(context, "Mất kết nối internet!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public class BatteryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryPct = (level / (float)scale) * 100;

            if (batteryPct < 20) {
                // Hiển thị cảnh báo pin yếu
                Toast.makeText(context, "Pin yếu!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}