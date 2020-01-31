package com.shoe.shoedistance;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class ShoeListAdapter extends ArrayAdapter<Shoe> {

    private Context mContext;
    private int mResource;

    public ShoeListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Shoe> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String brand = getItem(position).getBrand();
        String model = getItem(position).getModel();
        int distance = getItem(position).getDistance();

        Shoe shoe = new Shoe(model, brand, distance);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvBrand = (TextView) convertView.findViewById(R.id.brand);
        TextView tvModel = (TextView) convertView.findViewById(R.id.model);
        TextView tvDistance = (TextView) convertView.findViewById(R.id.kilometers);

        tvBrand.setText(brand);
        tvModel.setText(model);
        tvDistance.setText("" + distance);

        return convertView;
    }
}

