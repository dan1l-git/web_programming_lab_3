package com.example.autoparkapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<CarEntity> carList;
    public interface OnCarClickListener {
        void onCarClick(CarEntity car);
    }
    private OnCarClickListener listener;

    public interface OnCarLongClickListener {
        void onCarLongClick(CarEntity car);
    }

    private OnCarLongClickListener longClickListener;

    public CarAdapter(List<CarEntity> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        CarEntity car = carList.get(position);
        holder.carInfoText.setText(car.getBrand() + " " + car.getModel());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCarClick(car);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onCarLongClick(car);
                return true;
            }
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public void setCarList(List<CarEntity> carList) {
        this.carList = carList;
        notifyDataSetChanged();
    }
    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView carInfoText;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            carInfoText = itemView.findViewById(R.id.textCarInfo);
        }
    }

    public void setOnCarClickListener(OnCarClickListener listener) {
        this.listener = listener;
    }

    public void setOnCarLongClickListener(OnCarLongClickListener listener) {
        this.longClickListener = listener;
    }

}
