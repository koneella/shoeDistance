package com.shoe.shoedistance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listShoes = (ListView) findViewById(R.id.list_shoes);


        Shoe adidas = new Shoe("ub", "adidas", 22);
        Shoe adidas1 = new Shoe("ub1", "adidas1", 25);
        Shoe adidas2 = new Shoe("ub11", "adidas1", 25);

        ArrayList<Shoe> shoes = new ArrayList<Shoe>();

        shoes.add(adidas);
        shoes.add(adidas1);
        shoes.add(adidas2);

        //ArrayAdapter<Shoe> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shoes);

        ShoeListAdapter adapter = new ShoeListAdapter(this, R.layout.adapter_view_layout, shoes);
        listShoes.setAdapter(adapter);


    }
}
