package com.example.autoparkapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "cars")
public class CarEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String brand;

    @NonNull
    private String model;

    private int year;

    private int mileage;

    @NonNull
    private String status = "В наявності";

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @NonNull
    public String getBrand() { return brand; }
    public void setBrand(@NonNull String brand) { this.brand = brand; }

    @NonNull
    public String getModel() { return model; }
    public void setModel(@NonNull String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) {
        if (year >= 1950) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Рік повинен бути не менше 1950");
        }
    }

    public int getMileage() { return mileage; }
    public void setMileage(int mileage) {
        if (mileage >= 0) {
            this.mileage = mileage;
        } else {
            throw new IllegalArgumentException("Пробіг не може бути від’ємним");
        }
    }

    @NonNull
    public String getStatus() { return status; }
    public void setStatus(@NonNull String status) {
        if (status.equals("В наявності") || status.equals("Орендовано") || status.equals("В ремонті")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Недопустимий статус: " + status);
        }
    }
}
