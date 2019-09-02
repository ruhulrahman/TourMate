package com.example.tourmate.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tourmate.R;
import com.example.tourmate.activity.tour.AddTourActivity;
import com.example.tourmate.adapter.TourAdapter;
import com.example.tourmate.helper.TourDatabase;
import com.example.tourmate.model.Tour;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripFragment extends Fragment {
    private List<Tour> tours;
    private RecyclerView tourRV;
    private FloatingActionButton AddTourBtn;
    private TourDatabase helper;
    private Context context;
    private TourAdapter adapter;

    private int id;
    private String tourTitle, tourLocation, startDate, endDate, tourDesc;

    public TripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip, container, false);



        tourRV = view.findViewById(R.id.tourRV);
        AddTourBtn = view.findViewById(R.id.AddTourBtn);

        AddTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddTourActivity.class));
            }
        });

        tours = new ArrayList<>();
        helper = new TourDatabase(getContext());
        adapter = new TourAdapter(tours, helper, getContext());

        tourRV.setLayoutManager(new LinearLayoutManager(getContext()));
        tourRV.setAdapter(adapter);

        getData();

        return view;
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

}
