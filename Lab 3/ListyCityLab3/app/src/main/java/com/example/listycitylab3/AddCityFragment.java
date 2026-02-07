package com.example.listycitylab3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    public static AddCityFragment newInstance(City city, int position) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        args.putInt("position", position);

        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // defining an interface for this
    interface AddCityDialogListener {
        void onCityListClick(City city, int position);
    }

    private AddCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // check if the context implements the interface
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        }

        else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // get view
        View view = getLayoutInflater().inflate(R.layout.fragment_add_city, null);

        // get the edit texts for city and province
        EditText cityEdit = view.findViewById(R.id.edit_text_city_text);
        EditText provinceEdit = view.findViewById(R.id.edit_text_province_text);

        // check if the current action is to edit a city
        // get args
        Bundle args = getArguments();
        City existingCity = null;
        int pos = -1;

        // check if args are not null
        if (args != null) {
            existingCity = (City) args.getSerializable("city");
            pos = args.getInt("position");
        }

        final int finalPos = pos;

        // check if the city is not null
        if (existingCity != null) {
            // populate the edit texts
            cityEdit.setText(existingCity.getName());
            provinceEdit.setText(existingCity.getProvince());
        }

        // create a builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        return builder
                .setView(view)
                .setTitle("Add / edit city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", (dialog, id) -> {
                    String city = cityEdit.getText().toString();
                    String province = provinceEdit.getText().toString();

                    listener.onCityListClick(new City(city, province), finalPos);
                })
                .create();
    }
}
