package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import com.example.listycitylab3.AddCityFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;
    private FirebaseFirestore db;
    private CollectionReference citiesRef;

    @Override
    public void onCityListClick(City city, int position) {
        // if position is -1, then the function is Add City
        // else, the function is Update City
        if (position == -1) {
            dataList.add(city);
            cityAdapter.notifyDataSetChanged();

            DocumentReference docRef = citiesRef.document(city.getName());
            docRef.set(city);
        }
        else {
            dataList.set(position, city);

            citiesRef.document(city.getName()).set(city);
            cityAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<>();
        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        // init the db
        db = FirebaseFirestore.getInstance();
        citiesRef = db.collection("cities");

        citiesRef.addSnapshotListener((value, error) -> {
            if (error != null) {
                Log.e("Firestore", "Error getting documents", error);
                return;
            }

            if (value != null) {
                dataList.clear();
                for (QueryDocumentSnapshot document : value) {
                    String name = document.getString("name");
                    String province = document.getString("province");
                    dataList.add(new City(name, province));
                }
                cityAdapter.notifyDataSetChanged();
            }
        });

        // long click delete
        cityList.setOnItemLongClickListener((parent, view, position, id) -> {
            City cityToDelete = dataList.get(position);

            citiesRef.document(cityToDelete.getName()).delete()
                    .addOnFailureListener(e -> Log.e("Firestore", "Error deleting document", e));

            return true;
        });

        // FAB logic (Add City function)
        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(view -> {
            AddCityFragment.newInstance(null, -1).show(getSupportFragmentManager(), "Add City");
        });

        // ListView logic (Update City function)
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City editingCity = dataList.get(position);
            AddCityFragment.newInstance(editingCity, position).show(getSupportFragmentManager(), "Edit City");
        });
    }
}