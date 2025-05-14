package com.example.autoparkapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CarViewModel carViewModel;
    private CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        carAdapter = new CarAdapter(new ArrayList<>());
        carAdapter.setOnCarClickListener(car -> {
            AddCarDialog.show(this, updatedCar -> {
                car.setBrand(updatedCar.getBrand());
                car.setModel(updatedCar.getModel());
                car.setYear(updatedCar.getYear());
                car.setMileage(updatedCar.getMileage());
                car.setStatus(updatedCar.getStatus());
                carViewModel.update(car);
            }, car);
        });

        recyclerView.setAdapter(carAdapter);

        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);
        carViewModel.getAllCars().observe(this, carEntities -> {
            carAdapter.setCarList(carEntities);
        });

        FloatingActionButton buttonAddCar = findViewById(R.id.buttonAddCar);
        buttonAddCar.setOnClickListener(v -> {
            AddCarDialog.show(this, car -> carViewModel.insert(car), null);
        });

        carAdapter.setOnCarLongClickListener(car -> {
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Видалити авто?")
                    .setMessage(car.getBrand() + " " + car.getModel())
                    .setPositiveButton("Видалити", (dialog, which) -> {
                        carViewModel.delete(car);
                    })
                    .setNegativeButton("Скасувати", null)
                    .show();
        });



    }
}
