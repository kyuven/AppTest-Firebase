package com.example.appfood_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductInfo extends AppCompatActivity {

    TextView nameFood, foodPrice, description, txtRating;
    ImageView imgFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        nameFood = findViewById(R.id.txt_nameFoodClose);
        txtRating = findViewById(R.id.txt_ratingDesc);
        foodPrice = findViewById(R.id.price);
        description = findViewById(R.id.txt_descriptionFood);
        imgFood = findViewById(R.id.imageFood_desc);

        // get the information on the other activity
        nameFood.setText(getIntent().getExtras().getString("Food"));
        description.setText(getIntent().getExtras().getString("Description"));
        txtRating.setText(getIntent().getExtras().getString("Rating"));
        foodPrice.setText(getIntent().getExtras().getString("Price"));

        int my_int_image = getIntent().getIntExtra("Image", 0);
        imgFood.setImageResource(my_int_image);
    }
}