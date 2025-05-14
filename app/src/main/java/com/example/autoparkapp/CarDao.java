package com.example.autoparkapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarDao {

    @Query("SELECT * FROM cars ORDER BY id DESC")
    LiveData<List<CarEntity>> getAllCars();

    @Insert
    void insertCar(CarEntity car);

    @Update
    void updateCar(CarEntity car);

    @Delete
    void deleteCar(CarEntity car);

    @Query("SELECT * FROM cars WHERE id = :carId LIMIT 1")
    CarEntity getCarById(int carId);
}
