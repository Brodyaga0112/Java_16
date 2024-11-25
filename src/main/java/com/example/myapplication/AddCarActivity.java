package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddCarActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextModel;
    private Button buttonAdd;
    private CarD carD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        editTextName = findViewById(R.id.editTextName);
        editTextModel = findViewById(R.id.editTextModel);
        buttonAdd = findViewById(R.id.add);

        carD = new CarD(this);

        buttonAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String model = editTextModel.getText().toString();
            if (!name.isEmpty() && !model.isEmpty()) {
                Car car = new Car(0, name, model);
                carD.addCar(car);
                finish(); // Return to MainActivity
            } else {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
