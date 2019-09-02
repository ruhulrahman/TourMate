package com.example.tourmate.activity.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tourmate.R;
import com.example.tourmate.activity.expense.AddExpenseActivity;
import com.example.tourmate.activity.expense.ExpenseDetailsActivity;
import com.example.tourmate.activity.expense.ViewExpenseActivity;
import com.example.tourmate.model.Tour;

import java.util.List;

public class DetailsTourActivity extends AppCompatActivity {
    private List<Tour> tours;
    private Button editTourBtn;
    private TextView tourTitleTV, tourLocationTV, startDateTV, endDateTV, tourDescTV ;

    private int tourId;
    private String tourTitle,tourLocation, startDate, endDate, tourDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_tour);
        init();

        if(getIntent().getExtras() != null){
            tourId = Integer.parseInt(getIntent().getStringExtra("tourId"));
            tourTitle = getIntent().getStringExtra("tourTitle");
            tourLocation = getIntent().getStringExtra("tourLocation");
            startDate = getIntent().getStringExtra("startDate");
            endDate = getIntent().getStringExtra("endDate");
            tourDesc = getIntent().getStringExtra("tourDesc");

            tourTitleTV.setText("Tour Title: "+tourTitle);
            tourLocationTV.setText("Tour Location: "+tourLocation);
            startDateTV.setText("Tour Start Date: "+startDate);
            endDateTV.setText("Tour Start Date: "+endDate);
            tourDescTV.setText("Tour Description: "+tourDesc);
        }

        editTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsTourActivity.this, EditTourActivity.class);
                intent.putExtra("tourId", String.valueOf(tourId));
                intent.putExtra("tourTitle", tourTitle);
                intent.putExtra("tourLocation", tourLocation);
                intent.putExtra("startDate", startDate);
                intent.putExtra("endDate", endDate);
                intent.putExtra("tourDesc", tourDesc);
                startActivity(intent);
            }
        });
    }

    private void init() {
        tourTitleTV = findViewById(R.id.tourTitleTV);
        tourLocationTV = findViewById(R.id.tourLocationTV);
        startDateTV = findViewById(R.id.startDateTV);
        endDateTV = findViewById(R.id.endDateTV);
        tourDescTV = findViewById(R.id.tourDescTV);
        editTourBtn = findViewById(R.id.editTourBtn);
    }

    public void back(View view) {
        onBackPressed();
    }

    public void ViewExpenseClick(View view) {
        startActivity(new Intent(DetailsTourActivity.this, ViewExpenseActivity.class));
    }

    public void AddExpenseClick(View view) {
        startActivity(new Intent(DetailsTourActivity.this, AddExpenseActivity.class));
    }
}
