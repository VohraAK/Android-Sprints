package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {
    public CityArrayAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
    }

    // overriding getView()
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // creating our own View object
        View myView;

        // check if view from convertView could be used...
        if (convertView == null) {
            // no? inflate and create a brand new view
            myView = LayoutInflater.from(getContext()).inflate(R.layout.content, parent, false);
        }

        // if not, reuse the scrap view
        else {
            myView = convertView;
        }

        // get city and province TextViews (by reference)
        City city = getItem(position);
        TextView cityName = myView.findViewById(R.id.city_text);
        TextView provinceName = myView.findViewById(R.id.province_text);

        // setText with names, populating myView
        cityName.setText(city.getName() + ",");
        provinceName.setText(city.getProvince());

        return myView;
    }
}
