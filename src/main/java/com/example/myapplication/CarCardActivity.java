package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class CarCardActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewModel;
    private Button buttonDelete;
    private Button buttonEdit;
    private CarD carD;
    private long carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_card);

        textViewName = findViewById(R.id.textViewName);
        textViewModel = findViewById(R.id.textViewModel);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);

        carD = new CarD(this);

        carId = getIntent().getLongExtra("carId", -1);
        if (carId != -1) {
            Car car = carD.getCarById(carId);
            if (car != null) {
                textViewName.setText(car.getCar_Name());
                textViewModel.setText(car.getCar_Model());
            }
        }

        buttonDelete.setOnClickListener(v -> {
            if (carId != -1) {
                carD.deleteCarById(carId);
                finish();
            }
        });

        buttonEdit.setOnClickListener(v -> {
            if (carId != -1) {
                Intent intent = new Intent(CarCardActivity.this, EditCarActivity.class);
                intent.putExtra("carId", carId);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (carId != -1) {
            Car car = carD.getCarById(carId);
            if (car != null) {
                textViewName.setText(car.getCar_Name());
                textViewModel.setText(car.getCar_Model());
            }
        }
    }
}
