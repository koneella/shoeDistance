package com.shoe.shoedistance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;



//todo snackbar for adding shoes
//todo delete shoe
//todo graph for shoe distances
//todo dark mode option


public class MainActivity extends AppCompatActivity {
    ArrayList<Shoe> shoes = new ArrayList<Shoe>();
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load the saved arraylist from json
        loadData();

        FloatingActionButton addShoeFloat = findViewById(R.id.addShoeButton);
        ListView listShoes = (ListView) findViewById(R.id.list_shoes);

        final ShoeListAdapter adapter = new ShoeListAdapter(this, R.layout.adapter_view_layout, shoes);
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

        // listener for floating action button used for adding shoes
        addShoeFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.addshoe_dialog, null);
                final EditText brandDialog = (EditText) alertView.findViewById(R.id.brandDialog);
                final EditText modelDialog = (EditText) alertView.findViewById(R.id.modelDialog);
                final EditText distanceDialog = (EditText) alertView.findViewById(R.id.distanceDialog);
                Button confirmButton = (Button) alertView.findViewById(R.id.buttonDialog_ok);
                Button cancelButton = (Button) alertView.findViewById(R.id.buttonDialog_cancel);
                final TextInputLayout brand_layout = (TextInputLayout) alertView.findViewById(R.id.brand_layout);
                final TextInputLayout model_layout = (TextInputLayout) alertView.findViewById(R.id.model_layout);
                final TextInputLayout distance_layout = (TextInputLayout) alertView.findViewById(R.id.distance_layout);

                alertBuilder.setView(alertView);
                final AlertDialog dialog = alertBuilder.create();

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(brandDialog.getText().toString().isEmpty()) {
                            brand_layout.setError("Please enter a brand.");
                        } else if(modelDialog.getText().toString().isEmpty()) {
                            model_layout.setError("Please enter a model.");
                        } else if(distanceDialog.getText().toString().isEmpty()){
                            distance_layout.setError("Please enter starting distance.");
                        } else {
                            // get the info from the editTexts
                            String model = modelDialog.getText().toString();
                            String brand = brandDialog.getText().toString();
                            int distance = Integer.parseInt(distanceDialog.getText().toString());

                            // create newshoe
                            Shoe newshoe = new Shoe(model, brand, distance);

                            // pass it to the adapter
                            adapter.add(newshoe);
                            saveData();
                            dialog.dismiss();
                        }
                    }
                });

                // listener for closing the shoedialog
                cancelButton.setOnClickListener(new View.OnClickListener() {
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
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(shoes);
        editor.putString("list", json);
        editor.apply();
    }

    // Used for loading the shoe arraylist from json
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<Shoe>>() {}.getType();
        shoes = gson.fromJson(json, type);

        if(shoes == null) {
            shoes = new ArrayList<>();
        }
    }


}
