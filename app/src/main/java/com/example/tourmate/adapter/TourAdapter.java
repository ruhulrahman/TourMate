package com.example.tourmate.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmate.R;
import com.example.tourmate.activity.expense.ExpenseDetailsActivity;
import com.example.tourmate.activity.tour.DetailsTourActivity;
import com.example.tourmate.helper.ExpenseDatabase;
import com.example.tourmate.helper.TourDatabase;
import com.example.tourmate.model.Expense;
import com.example.tourmate.model.Tour;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> {

    private List<Tour> tours;
    private TourDatabase helper;
    private boolean result=false;
    private Context context;

    public TourAdapter(List<Tour> tours, TourDatabase helper, Context context) {
        this.tours = tours;
        this.helper = helper;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Tour tour = tours.get(position);
        holder.tourTitleTV.setText(tour.getTourTitle());
        holder.tourLocationTV.setText(tour.getTourLocation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsTourActivity.class);
                intent.putExtra("tourId", String.valueOf(tour.getId()));
                intent.putExtra("tourTitle", tour.getTourTitle());
                intent.putExtra("tourLocation", tour.getTourLocation());
                intent.putExtra("startDate", tour.getStartDate());
                intent.putExtra("endDate", tour.getEndDate());
                intent.putExtra("tourDesc", tour.getTourDesc());
                context.startActivity(intent);
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                helper = new TourDatabase(context);
                helper.deleteData(tour.getId());
                tours.remove(position);
                notifyDataSetChanged();
                result = true;
                if(result == true){
                    Toast.makeText(context, "Tour Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tourTitleTV, tourLocationTV;
        private Button deleteBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tourTitleTV = itemView.findViewById(R.id.tourTitleTV);
            tourLocationTV = itemView.findViewById(R.id.tourLocationTV);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
