package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import java.util.List;

public class CarSelectionDialog extends DialogFragment {

    public interface CarSelectionListener {
        void onCarSelected(long carId);
    }

    private List<Car> carList;
    private CarSelectionListener listener;

    public CarSelectionDialog(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (CarSelectionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement CarSelectionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите автомобиль");

        String[] carNames = new String[carList.size()];
        for (int i = 0; i < carList.size(); i++) {
            carNames[i] = carList.get(i).getCar_Name();
        }

        builder.setItems(carNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onCarSelected(carList.get(which).getID_Car());
            }
        });

        return builder.create();
    }
}
