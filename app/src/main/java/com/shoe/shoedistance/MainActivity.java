package com.shoe.shoedistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


//todo add shoes button
//todo snackbar for adding shoes
//todo delete shoe
//todo graph for shoe distances
//todo dark mode option


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
        Shoe adidas11 = new Shoe("ub", "adidas", 22);
        Shoe adidas111 = new Shoe("ub1", "adidas1", 25);
        Shoe adidas21 = new Shoe("ub11", "adidas1", 25);
        Shoe adidas31 = new Shoe("ub", "adidas", 22);
        Shoe adidas22 = new Shoe("ub11", "adidas1", 25);
        Shoe adidas23 = new Shoe("ub11", "adidas1", 25);

        final ArrayList<Shoe> shoes = new ArrayList<Shoe>();

        shoes.add(adidas);
        shoes.add(adidas1);
        shoes.add(adidas2);
        shoes.add(adidas11);
        shoes.add(adidas111);
        shoes.add(adidas1);
        shoes.add(adidas31);
        shoes.add(adidas11);
        shoes.add(adidas22);
        shoes.add(adidas21);
        shoes.add(adidas23);

        ShoeListAdapter adapter = new ShoeListAdapter(this, R.layout.adapter_view_layout, shoes);
        listShoes.setAdapter(adapter);

        // listener for shoelist clicks
        listShoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // open shoeactivity and pass the shoe arraylist to it
                Intent intent = new Intent(MainActivity.this, ShoeActivity.class);

                // pass the location from the listview
                intent.putExtra("position", i);

                // pass the shoe arraylist
                intent.putExtra("shoe", shoes);
                startActivity(intent);
            }
        });

    }




}
