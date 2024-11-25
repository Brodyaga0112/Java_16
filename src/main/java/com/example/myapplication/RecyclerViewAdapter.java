package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Car;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Car> carArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Car> carArrayList) {
        this.context = context;
        this.carArrayList = carArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.car_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Car car = carArrayList.get(position);
        holder.carName.setText(car.getCar_Name());
        holder.carModel.setText(car.getCar_Model());
    }

    @Override
    public int getItemCount() {
        return carArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView carName;
        TextView carModel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.c_name);
            carModel = itemView.findViewById(R.id.c_model);
        }
    }
}
