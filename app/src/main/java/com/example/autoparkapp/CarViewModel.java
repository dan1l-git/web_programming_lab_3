package com.example.autoparkapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarViewModel extends AndroidViewModel {

    private final CarDao carDao;
    private final LiveData<List<CarEntity>> allCars;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CarViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        carDao = db.carDao();
        allCars = carDao.getAllCars();
    }

    public LiveData<List<CarEntity>> getAllCars() {
        return allCars;
    }

    public void insert(CarEntity car) {
        executorService.execute(() -> carDao.insertCar(car));
    }

    public void update(CarEntity car) {
        executorService.execute(() -> carDao.updateCar(car));
    }

    public void delete(CarEntity car) {
        executorService.execute(() -> carDao.deleteCar(car));
    }
}
