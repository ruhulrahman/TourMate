package com.example.tourmate.activity.tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.tourmate.R;
import com.example.tourmate.adapter.TourAdapter;
import com.example.tourmate.helper.TourDatabase;
import com.example.tourmate.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class ViewTourActivity extends AppCompatActivity {
    private List<Tour> tours;
    private RecyclerView tourRV;
    private TourDatabase helper;
    private Context context;
    private TourAdapter adapter;

    private int id;
    private String tourTitle, tourLocation, startDate, endDate, tourDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tour);

        init();
        getData();
    }

    private void init() {
        tourRV = findViewById(R.id.tourRV);

        tours = new ArrayList<>();
        helper = new TourDatabase(this);
        adapter = new TourAdapter(tours, helper, this);

        tourRV.setLayoutManager(new LinearLayoutManager(this));
        tourRV.setAdapter(adapter);
    }

    private void getData() {
        Cursor cursor = helper.showData();

        while (cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            tourTitle = cursor.getString(cursor.getColumnIndex(helper.COL_TOUT_TITLE));
            tourLocation = cursor.getString(cursor.getColumnIndex(helper.COL_TOUR_LOCATION));
            startDate = cursor.getString(cursor.getColumnIndex(helper.COL_SATRT_DATE));
            endDate = cursor.getString(cursor.getColumnIndex(helper.COL_END_DATE));
            tourDesc = cursor.getString(cursor.getColumnIndex(helper.COL_TOUR_DESC));

            tours.add(new Tour(id, tourTitle, tourLocation, startDate, endDate, tourDesc));
            adapter.notifyDataSetChanged();

        }
    }

    public void back(View view) {
        onBackPressed();
    }
}
