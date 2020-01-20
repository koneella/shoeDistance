package com.shoe.shoedistance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShoeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        ArrayList<Shoe> shoeList = (ArrayList<Shoe>) intent.getSerializableExtra("shoe");
        int id = intent.getIntExtra("position", 0);

        TextView brand = (TextView) findViewById(R.id.brand);
        TextView model = (TextView) findViewById(R.id.model);
        TextView distance = (TextView) findViewById(R.id.distance);

        brand.setText(shoeList.get(id).getBrand());
        model.setText(shoeList.get(id).getModel());
        distance.setText(shoeList.get(id).getDistance() + " kilometers");


        Log.d("kenkätesti", "" + shoeList.get(id).getBrand());


    }


    // back button listener
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
