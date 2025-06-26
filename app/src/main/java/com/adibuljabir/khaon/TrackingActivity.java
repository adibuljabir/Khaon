package com.adibuljabir.khaon;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TrackingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        //back button
        ImageView backButton2 = findViewById(R.id.backButton2);
        backButton2.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {finish();}});
        //back button

        double totalPrice = getIntent().getDoubleExtra("totalPrice", 0.0);

        // Optional: Show price as toast
        Toast.makeText(this, "Total: $" + totalPrice, Toast.LENGTH_LONG).show();
    }
}
