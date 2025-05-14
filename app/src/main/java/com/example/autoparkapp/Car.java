package com.example.autoparkapp;

public class Car {
    private String brand;
    private String model;
    private int year;
    private int mileage;
    private String status;

    public Car(String brand, String model, int year, int mileage, String status) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.status = status;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getMileage() { return mileage; }
    public void setMileage(int mileage) { this.mileage = mileage; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
