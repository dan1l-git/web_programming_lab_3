package com.example.autoparkapp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import com.google.android.material.textfield.TextInputEditText;

public class AddCarDialog {

    public interface AddCarCallback {
        void onCarAdded(CarEntity car);
    }

    public static void show(Context context, AddCarCallback callback, CarEntity carToEdit) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_car, null);

        TextInputEditText editBrand = dialogView.findViewById(R.id.editBrand);
        TextInputEditText editModel = dialogView.findViewById(R.id.editModel);
        TextInputEditText editYear = dialogView.findViewById(R.id.editYear);
        TextInputEditText editMileage = dialogView.findViewById(R.id.editMileage);
        Spinner spinnerStatus = dialogView.findViewById(R.id.spinnerStatus);

        String[] statuses = {"В наявності", "Орендовано", "В ремонті"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, statuses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);

        if (carToEdit != null) {
            editBrand.setText(carToEdit.getBrand());
            editModel.setText(carToEdit.getModel());
            editYear.setText(String.valueOf(carToEdit.getYear()));
            editMileage.setText(String.valueOf(carToEdit.getMileage()));
            int statusPosition = adapter.getPosition(carToEdit.getStatus());
            spinnerStatus.setSelection(statusPosition);
        }

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(carToEdit == null ? "Додати авто" : "Редагувати авто")
                .setView(dialogView)
                .setPositiveButton(carToEdit == null ? "Додати" : "Оновити", null)
                .setNegativeButton("Скасувати", null)
                .create();

        dialog.setOnShowListener(dialogInterface -> {
            Button addButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            addButton.setOnClickListener(v -> {
                try {
                    CarEntity car = new CarEntity();
                    car.setBrand(editBrand.getText().toString());
                    car.setModel(editModel.getText().toString());
                    car.setYear(Integer.parseInt(editYear.getText().toString()));
                    car.setMileage(Integer.parseInt(editMileage.getText().toString()));
                    car.setStatus(spinnerStatus.getSelectedItem().toString());
                    callback.onCarAdded(car);
                    dialog.dismiss();
                } catch (Exception e) {
                    editBrand.setError("Введіть коректні дані");
                }
            });
        });

        dialog.show();
    }

}
