package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> carList;
    private Context context;

    public CarAdapter(List<Car> carList, Context context) {
        this.carList = carList;
        this.context = context;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_card, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.textViewModel.setText(car.getCar_Model());
        holder.textViewName.setText(car.getCar_Name());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CarCardActivity.class);
            intent.putExtra("carId", car.getID_Car());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView textViewModel;
        TextView textViewName;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewModel = itemView.findViewById(R.id.c_model);
            textViewName = itemView.findViewById(R.id.c_name);
        }
    }
}
