package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import com.example.listycitylab3.AddCityFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;

//    @Override
//    public void addCity(City city) {
//        // add the new city into the ArrayAdapter<City> instance
//        cityAdapter.add(city);
//        cityAdapter.notifyDataSetChanged();
//    }


    @Override
    public void onCityListClick(City city, int position) {
        // if position is -1, then the function is Add City
        // else, the function is Update City
        if (position == -1) {
            dataList.add(city);
        }

        else {
            dataList.set(position, city);
        }

        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {"Karachi", "Lahore", "Islamabad", "Multan", "Sukkur", "Hyderabad", "Peshawar", "Sialkot", "Quetta"};
        String[] provinces = {"Sindh", "Punjab", "Punjab", "Punjab", "Sindh", "Sindh", "KPK", "Punjab", "Balochistan"};

        dataList = new ArrayList<City>();

        // initialise City objects
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        // FAB logic (Add City function)
        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(view -> {
            AddCityFragment.newInstance(null, -1).show(getSupportFragmentManager(), "Add City");
        });

        // ListView logic (Update City function)
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            // get the city clicked
            City editingCity = dataList.get(position);

            // pass args to fragment
            AddCityFragment.newInstance(editingCity, position).show(getSupportFragmentManager(), "Edit City");
        });

    }
}