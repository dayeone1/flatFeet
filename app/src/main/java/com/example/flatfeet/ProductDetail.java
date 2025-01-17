package com.example.flatfeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductDetail extends AppCompatActivity {

    ImageView detailImage;
    TextView detailName, detailPrice;
    Button orderButton;
    DBHelper dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.product_detail);
        dbHelper = new DBHelper(this);

        detailImage = findViewById(R.id.datailImage);
        detailName = findViewById(R.id.detailName);
        detailPrice = findViewById(R.id.detailPrice);



        List<Integer> productImages = new ArrayList<Integer>();
        productImages.add(R.drawable.adidas_sports1);
        productImages.add(R.drawable.buddy_sports);
        productImages.add(R.drawable.demin_sports);
        productImages.add(R.drawable.newbalance_sports);
        productImages.add(R.drawable.nike_sports1);

        Intent intent = getIntent();
        int num = intent.getIntExtra("num", -1);


        detailImage.setImageResource(productImages.get(num));
        detailName.setText(dbHelper.getName(num));
        detailPrice.setText(dbHelper.getPrice(num));

        orderButton = findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Confirm.class);
                intent.putExtra("num", num);
                startActivity(intent);
            }
        });
    }
}
