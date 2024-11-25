package com.example.myapplication;

public class Car {
    private int ID_Car;
    private String Car_Name;
    private String Car_Model;

    public Car(int ID_Car, String Car_Name, String Car_Model) {
        this.ID_Car = ID_Car;
        this.Car_Name = Car_Name;
        this.Car_Model = Car_Model;
    }

    public int getID_Car() {
        return ID_Car;
    }

    public void setID_Car(int ID_Car) {
        this.ID_Car = ID_Car;
    }

    public String getCar_Name() {
        return Car_Name;
    }

    public void setCar_Name(String Car_Name) {
        this.Car_Name = Car_Name;
    }

    public String getCar_Model() {
        return Car_Model;
    }

    public void setCar_Model(String Car_Model) {
        this.Car_Model = Car_Model;
    }
}
