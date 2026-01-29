package com.example.listycity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    // state variables
    int selectedCityPos = -1;   // stores index of selected city

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // get the view for cityList
        cityList = findViewById(R.id.city_list);
        String[] cities = {"Karachi", "Lahore", "Islamabad", "Multan", "Sukkur", "Hyderabad", "Peshawar", "Sialkot"};

        // define a dataList; add all cities as a list
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<String>(this, R.layout.content, dataList) {
            // LLM generated
            @NonNull
            @SuppressLint("SetTextI18n")
            @Override
            public android.view.View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {
                android.view.View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;

                // 2. Get the clean city name from your dataList
                String cityName = dataList.get(position);

                if (position == selectedCityPos) {
                    textView.setText(cityName + " ✅");
                } else {
                    textView.setText(cityName);
                    view.setBackgroundColor(android.graphics.Color.TRANSPARENT);
                }

                return view;
            }
        };
        cityList.setAdapter(cityAdapter);

        // get the controls
        EditText cityNameInput = findViewById(R.id.city_name_input);
        Button addCityButton = findViewById(R.id.add_city_button);
        Button deleteCityButton = findViewById(R.id.delete_city_button);

        // add on-click listeners for each element

        // selecting a city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCityPos = position;

            // run getView on every click
            cityAdapter.notifyDataSetChanged();
        });

        // addition logic
        addCityButton.setOnClickListener(v -> {
            // get input
            String name = cityNameInput.getText().toString();

            // validation
            if (!name.isEmpty())
            {
                // add a name to the dataList
                dataList.add(name);

                // refresh
                cityAdapter.notifyDataSetChanged();

                // clear the input
                cityNameInput.setText("");

            }
        });

        // deletion logic
        deleteCityButton.setOnClickListener(v -> {
            if (selectedCityPos != -1)
            {
                // check if a valid row is selected
                if (selectedCityPos < dataList.size())
                {
                    dataList.remove(selectedCityPos);
                    cityAdapter.notifyDataSetChanged();

                    // reset the selection
                    selectedCityPos = -1;
                }
            }

        });


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}