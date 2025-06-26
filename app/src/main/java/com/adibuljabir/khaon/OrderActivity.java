package com.adibuljabir.khaon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    RadioButton sizeSmall, sizeMedium, sizeLarge;
    CheckBox cheeseBox, mushroomBox, mayoBox;
    Button addToCartButton;
    TextView quantityText;

    int quantity = 1;
    double basePrice = 10.99; // default to Medium

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_screen);
        //back button
        ImageView backButton1 = findViewById(R.id.backButton1);
        backButton1.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {finish();}});
        //back button

        // Find views
        sizeSmall = findViewById(R.id.sizeSmall);
        sizeMedium = findViewById(R.id.sizeMedium);
        sizeLarge = findViewById(R.id.sizeLarge);

        cheeseBox = findViewById(R.id.cheeseBox);
        mushroomBox = findViewById(R.id.mushroomBox);
        mayoBox = findViewById(R.id.mayoBox);

        quantityText = findViewById(R.id.quantityText);
        addToCartButton = findViewById(R.id.addToCartButton);

        // Set default selected size to Medium
        sizeMedium.setChecked(true);
        cheeseBox.setChecked(false);
        mushroomBox.setChecked(false);
        mayoBox.setChecked(false);



        // Quantity control
        findViewById(R.id.plusButton).setOnClickListener(v -> {
            updateQuantity(1);
        });

        findViewById(R.id.minusButton).setOnClickListener(v -> {
            updateQuantity(-1);
        });

        // Update total price when user changes size or ingredients
        CompoundButton.OnCheckedChangeListener recalculateListener = (buttonView, isChecked) -> updateTotalPrice();
        sizeSmall.setOnCheckedChangeListener(recalculateListener);
        sizeMedium.setOnCheckedChangeListener(recalculateListener);
        sizeLarge.setOnCheckedChangeListener(recalculateListener);
        cheeseBox.setOnCheckedChangeListener(recalculateListener);
        mushroomBox.setOnCheckedChangeListener(recalculateListener);
        mayoBox.setOnCheckedChangeListener(recalculateListener);

        // Also update when quantity changes
        updateTotalPrice();

        // Handle Add to Cart button
        addToCartButton.setOnClickListener(v -> {
            double total = calculateTotalPrice();
            Intent intent = new Intent(OrderActivity.this, TrackingActivity.class);
            intent.putExtra("totalPrice", total);
            startActivity(intent);
        });
    }

    private void updateQuantity(int change) {
        quantity += change;
        if (quantity < 1) quantity = 1;
        quantityText.setText(String.valueOf(quantity));
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double total = calculateTotalPrice();
        addToCartButton.setText("Add to Cart ($" + String.format("%.2f", total) + ")");
    }

    private double calculateTotalPrice() {
        double sizePrice = getSelectedSizePrice();
        double ingredients = getSelectedAddOnPrice();
        return (sizePrice + ingredients) * quantity;
    }

    private double getSelectedSizePrice() {
        if (sizeSmall.isChecked()) return 9.99;
        if (sizeMedium.isChecked()) return 10.99;
        if (sizeLarge.isChecked()) return 12.99;
        return 10.99; // Default fallback
    }

    private double getSelectedAddOnPrice() {
        double extra = 0.0;
        if (cheeseBox.isChecked()) extra += 1.56;
        if (mushroomBox.isChecked()) extra += 2.25;
        if (mayoBox.isChecked()) extra += 0.99;
        return extra;
    }
}
