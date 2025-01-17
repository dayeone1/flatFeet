package com.example.flatfeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Confirm extends AppCompatActivity {

    ImageView image;
    DBHelper dbHelper = new DBHelper(this);
    TextView textName, textPrice, textDay;
    Button btnProduct, btnLogin;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.confirm);

        textName = findViewById(R.id.textName);
        textPrice = findViewById(R.id.textPrice);
        textDay = findViewById(R.id.textDay);
        btnLogin = findViewById(R.id.btnLogin);
        btnProduct = findViewById(R.id.btnProduct);
        image = findViewById(R.id.image);

        List<Integer> productImages = new ArrayList<Integer>();
        productImages.add(R.drawable.adidas_sports1);
        productImages.add(R.drawable.buddy_sports);
        productImages.add(R.drawable.demin_sports);
        productImages.add(R.drawable.newbalance_sports);
        productImages.add(R.drawable.nike_sports1);

        Intent intent = getIntent();
        int num = intent.getIntExtra("num", -1);

        image.setImageResource(productImages.get(num));
        textName.setText(dbHelper.getName(num));
        textPrice.setText(dbHelper.getPrice(num) + "원");

        long time = System.currentTimeMillis() + (5*24*60*60*1000);
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        String getTime = sdf.format(date);

        textDay.setText("상품의 예상 도착일은 \n" + getTime + "입니다.");

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Products.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    } // onCreate
}
