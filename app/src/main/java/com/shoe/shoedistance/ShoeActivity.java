package com.shoe.shoedistance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShoeActivity extends AppCompatActivity {

    private ArrayList<Shoe> shoes;
    private TextView brand;
    private TextView model;
    private TextView distance;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        loadData();
        Intent intent = getIntent();
        id = intent.getIntExtra("position", 0);

        brand = (TextView) findViewById(R.id.brand);
        model = (TextView) findViewById(R.id.model);
        distance = (TextView) findViewById(R.id.distance);

        brand.setText(shoes.get(id).getBrand());
        model.setText(shoes.get(id).getModel());
        distance.setText(shoes.get(id).getDistance() + "");

        final Button add_distance_button = (Button) findViewById(R.id.add_distance);
        final Button remove_shoe_button = (Button) findViewById(R.id.remove_shoe);


        add_distance_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ShoeActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.add_distance_dialog, null);

                final NumberPicker add_distance_picker = (NumberPicker) alertView.findViewById(R.id.numberPicker);
                add_distance_picker.setMinValue(1);
                add_distance_picker.setMaxValue(100);

                Button add_distance_ok = (Button) alertView.findViewById(R.id.buttonDialog_ok);
                Button add_distance_cancel = (Button) alertView.findViewById(R.id.buttonDialog_cancel);


                alertBuilder.setView(alertView);
                final AlertDialog dialog = alertBuilder.create();

                add_distance_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // take the old distance
                        int oldDistance = shoes.get(id).getDistance();
                        // add the new distance to the old
                        int newDistance = oldDistance + add_distance_picker.getValue();
                        // set the speficic arraylist index to the newDistance
                        shoes.get(id).setDistance(newDistance);
                        updateDistanceText();
                        dialog.dismiss();
                    }
                });

                add_distance_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });

        remove_shoe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ShoeActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.remove_shoe_dialog, null);
                Button remove_shoe_ok = (Button) alertView.findViewById(R.id.buttonDialog_ok);
                Button remove_shoe_cancel = (Button) alertView.findViewById(R.id.buttonDialog_cancel);

                alertBuilder.setView(alertView);
                final AlertDialog dialog = alertBuilder.create();

                remove_shoe_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        shoes.remove(id);
                        onSupportNavigateUp();
                    }
                });

                remove_shoe_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }

    // Used for saving the shoe arraylist to json
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(shoes);
        editor.putString("list", json);
        editor.apply();
    }

    // Used for loading the shoe arraylist from json
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<Shoe>>() {}.getType();
        shoes = gson.fromJson(json, type);

        if(shoes == null) {
            shoes = new ArrayList<>();
        }
    }


    // used for updating the distance when pressing the add distance button
    public void updateDistanceText() {
        distance = (TextView) findViewById(R.id.distance);
        distance.setText(shoes.get(id).getDistance() + "");
    }

    // back button listener
    @Override
    public boolean onSupportNavigateUp() {
        saveData();
        onBackPressed();
        return true;
    }

}
