package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CarSelectionDialog.CarSelectionListener {

    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private CarD carD;
    private boolean isDeleteAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        carD = new CarD(this);
        List<Car> carList = carD.getAllCars();
        carAdapter = new CarAdapter(carList, this);
        recyclerView.setAdapter(carAdapter);

        Button btnAddCar = findViewById(R.id.btn_add_car);
        btnAddCar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddCarActivity.class);
            startActivity(intent);
        });

        Button btnDeleteCar = findViewById(R.id.btn_delete_car);
        btnDeleteCar.setOnClickListener(v -> {
            isDeleteAction = true;
            showCarSelectionDialog();
        });

        Button btnEditCar = findViewById(R.id.btn_edit_car);
        btnEditCar.setOnClickListener(v -> {
            isDeleteAction = false;
            showCarSelectionDialog();
        });
    }

    private void showCarSelectionDialog() {
        List<Car> carList = carD.getAllCars();
        if (carList.isEmpty()) {
            Toast.makeText(this, "Список автомобилей пуст", Toast.LENGTH_SHORT).show();
            return;
        }
        CarSelectionDialog dialog = new CarSelectionDialog(carList);
        dialog.show(getSupportFragmentManager(), "carSelectionDialog");
    }

    @Override
    public void onCarSelected(long carId) {
        if (carId != -1) {
            if (isDeleteAction) {
                carD.deleteCarById(carId);
                List<Car> carList = carD.getAllCars();
                carAdapter.setCarList(carList);
                carAdapter.notifyDataSetChanged();
            } else {
                Intent intent = new Intent(MainActivity.this, EditCarActivity.class);
                intent.putExtra("carId", carId);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Car> carList = carD.getAllCars();
        carAdapter.setCarList(carList);
        carAdapter.notifyDataSetChanged();
    }
}
