package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarD {

    private DatabaseHelper databaseHelper;

    public CarD(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void addCar(Car car) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, car.getCar_Name());
        values.put(DatabaseHelper.COLUMN_MODEL, car.getCar_Model());
        db.insert(DatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();
        String[] columns = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_MODEL
        };

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                Car car = new Car(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MODEL))
                );
                carList.add(car);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return carList;
    }

    public int deleteCarById(long carId) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int result = db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(carId)});
        db.close();
        return result;
    }

    public int updateCar(Car car) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, car.getCar_Name());
        values.put(DatabaseHelper.COLUMN_MODEL, car.getCar_Model());
        int result = db.update(DatabaseHelper.TABLE_NAME, values, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(car.getID_Car())});
        db.close();
        return result;
    }

    @SuppressLint("Range")
    public Car getCarById(long carId) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] columns = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_MODEL
        };
        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(carId)};
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Car car = null;
        if (cursor.moveToFirst()) {
            car = new Car(
                    cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MODEL))
            );
        }

        cursor.close();
        db.close();
        return car;
    }
}
