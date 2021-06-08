package com.alumnus.zebra.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alumnus.zebra.R;
import com.alumnus.zebra.pojo.AccelerationStringData;

import java.util.ArrayList;

public class AccelerationDataAdapter extends RecyclerView.Adapter<AccelerationDataAdapter.ViewHolder> {

    private ArrayList<AccelerationStringData> accelerations;
    Context context;

    public AccelerationDataAdapter(ArrayList<AccelerationStringData> accelerations) {
        this.accelerations = accelerations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_acceleration_data, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTs.setText(accelerations.get(position).ts);
        holder.tvX.setText(accelerations.get(position).x);
        holder.tvY.setText(accelerations.get(position).y);
        holder.tvZ.setText(accelerations.get(position).z);
        if (position == 0) {
            holder.tvTs.setBackground(context.getDrawable(R.drawable.bg_black_with_border));
            holder.tvX.setBackground(context.getDrawable(R.drawable.bg_black_with_border));
            holder.tvY.setBackground(context.getDrawable(R.drawable.bg_black_with_border));
            holder.tvZ.setBackground(context.getDrawable(R.drawable.bg_black_with_border));
        } else if (position % 2 == 0) {
            holder.tvTs.setBackground(context.getDrawable(R.drawable.bg_gray_with_border));
            holder.tvX.setBackground(context.getDrawable(R.drawable.bg_gray_with_border));
            holder.tvY.setBackground(context.getDrawable(R.drawable.bg_gray_with_border));
            holder.tvZ.setBackground(context.getDrawable(R.drawable.bg_gray_with_border));
        } else {
            holder.tvTs.setBackground(context.getDrawable(R.drawable.bg_white_with_border));
            holder.tvX.setBackground(context.getDrawable(R.drawable.bg_white_with_border));
            holder.tvY.setBackground(context.getDrawable(R.drawable.bg_white_with_border));
            holder.tvZ.setBackground(context.getDrawable(R.drawable.bg_white_with_border));
        }
        if (position == 0) {
            holder.tvTs.setTextColor(context.getColor(R.color.white));
            holder.tvX.setTextColor(context.getColor(R.color.white));
            holder.tvY.setTextColor(context.getColor(R.color.white));
            holder.tvZ.setTextColor(context.getColor(R.color.white));
        } else {
            holder.tvTs.setTextColor(context.getColor(R.color.black));
            holder.tvX.setTextColor(context.getColor(R.color.black));
            holder.tvY.setTextColor(context.getColor(R.color.black));
            holder.tvZ.setTextColor(context.getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return accelerations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTs, tvX, tvY, tvZ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTs = itemView.findViewById(R.id.tvTs);
            tvX = itemView.findViewById(R.id.tvX);
            tvY = itemView.findViewById(R.id.tvY);
            tvZ = itemView.findViewById(R.id.tvZ);
        }
    }
}
