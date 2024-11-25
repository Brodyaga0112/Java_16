package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class EditCarActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextModel;
    private Button buttonSave;
    private CarD carD;
    private long carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);

        editTextName = findViewById(R.id.editTextName);
        editTextModel = findViewById(R.id.editTextModel);
        buttonSave = findViewById(R.id.save);

        carD = new CarD(this);

        carId = getIntent().getLongExtra("carId", -1);
        if (carId != -1) {
            Car car = carD.getCarById(carId);
            if (car != null) {
                editTextName.setText(car.getCar_Name());
                editTextModel.setText(car.getCar_Model());
            }
        }

        buttonSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String model = editTextModel.getText().toString();
            if (!name.isEmpty() && !model.isEmpty()) {
                Car car = new Car((int) carId, name, model);
                carD.updateCar(car);
                finish();
            } else {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
