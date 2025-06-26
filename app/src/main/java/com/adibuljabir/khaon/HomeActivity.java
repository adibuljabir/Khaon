package com.adibuljabir.khaon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;



public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Disable night mode


        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);

        LinearLayout pizzaItem = findViewById(R.id.recomended);
        if (pizzaItem != null) {
            pizzaItem.setOnClickListener(v -> {

                Intent intent = new Intent(HomeActivity.this, OrderActivity.class);


                intent.putExtra("foodName", "BBQ Chicken Pizza");
                intent.putExtra("basePrice", 10.99); // This double value will be passed


                startActivity(intent);
            });
        } else {

            Toast.makeText(this, "Error: 'pizza_item' view not found.", Toast.LENGTH_LONG).show();
        }
    }
}
