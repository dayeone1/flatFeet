package com.example.flatfeet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {

    ListView productList;
    DBHelper dbHelper = new DBHelper(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.products);

        productList = findViewById(R.id.productList);

        List<Integer> productImages = new ArrayList<Integer>();
        productImages.add(R.drawable.adidas_sports1);
        productImages.add(R.drawable.buddy_sports);
        productImages.add(R.drawable.demin_sports);
        productImages.add(R.drawable.newbalance_sports);
        productImages.add(R.drawable.nike_sports1);

        dbHelper.addProduct(0, "지은이의 운동화", "10,000,000");
        dbHelper.addProduct(1, "시연이의 날아라 슈퍼보드", "4,500,000");
        dbHelper.addProduct(2, "예지의 우동사리", "700,000");
        dbHelper.addProduct(3, "수민 운동화2106", "5,780,000");
        dbHelper.addProduct(4, "효주's 수제화", "70,000");

        ProductAdapter adapter = new ProductAdapter(this, productImages);
        productList.setAdapter(adapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProductDetail.class);
                intent.putExtra("num", position);
                startActivity(intent);
            }
        });

    } // onCreate

    public class ProductAdapter extends BaseAdapter {

        Context context;
        List<Integer> image;

        public ProductAdapter(Context context, List<Integer> image) {
            this.context = context;
            this.image = image;
        }

        @Override
        public int getCount() {
            return image.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = View.inflate(context, R.layout.activity_item_products, null);
            }
            ImageView itemImage = convertView.findViewById(R.id.itemImage);
            itemImage.setImageResource(image.get(position));

            TextView itemText = convertView.findViewById(R.id.itemText);
            itemText.setText(dbHelper.getName(position));

            TextView itemPrice = convertView.findViewById(R.id.itemPrice);
            itemPrice.setText(dbHelper.getPrice(position));

            return convertView;
        }
    }



}
