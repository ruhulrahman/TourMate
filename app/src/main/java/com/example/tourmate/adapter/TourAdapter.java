package com.example.tourmate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmate.R;
import com.example.tourmate.model.Tour;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> {
    private List<Tour> tours;
    private Context context;

    public TourAdapter(List<Tour> tours, Context context) {
        this.tours = tours;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_tour,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tour tour = tours.get(position);
         holder.tripName.setText(tour.getTripName());
         holder.tripDescript.setText(tour.getTripDescrip());
         holder.fromDate.setText(tour.getFromDate());
         holder.toDate.setText(tour.getToDate());
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tripName,tripDescript,fromDate,toDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tripName = itemView.findViewById(R.id.demoTripNameTvId);
            tripDescript = itemView.findViewById(R.id.demoTripDescriptTvId);
            fromDate = itemView.findViewById(R.id.demoTripFromDateTvId);
            toDate = itemView.findViewById(R.id.demoTriptoDateTvId);
        }
    }
}
