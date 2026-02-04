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

    public AddCityFragment(AddCityDialogListener listener) {
        this.listener = listener;
    }

    // defining an interface for this
    interface AddCityDialogListener {
        void addCity(City city);
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

        // create a builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        return builder
                .setView(view)
                .setTitle("Add a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, id) -> {
                    String city = cityEdit.getText().toString();
                    String province = provinceEdit.getText().toString();

                    listener.addCity(new City(city, province));
                })
                .create();
    }
}
