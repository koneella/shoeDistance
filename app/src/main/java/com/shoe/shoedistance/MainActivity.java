package com.shoe.shoedistance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    Shoe adidas = new Shoe("ub", "adidas", 22);
    Shoe adidas = new Shoe("ub1", "adidas1", 25);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
