package com.example.tourmate.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmate.R;
import com.example.tourmate.adapter.TourAdapter;
import com.example.tourmate.model.Tour;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddTourActivity extends AppCompatActivity {
    private EditText tripNameEt,tripDescripEt;
    private TextView fromDateTv,toDateTv;
    private Button addTripBtn;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private String userId,tripName,tripDescript,toDate,fromDate;
    private RecyclerView recyclerView;
    private List<Tour> tours;
    private TourAdapter tourAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour);

        init();

        configRv();


        userId = firebaseAuth.getCurrentUser().getUid();

        addTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tripName = tripNameEt.getText().toString();
                tripDescript = tripDescripEt.getText().toString();
                fromDate = fromDateTv.getText().toString();
                toDate = toDateTv.getText().toString();
                
                saveTour(tripName,tripDescript,fromDate,toDate);

            }
        });
    }

    private void saveTour(String tripName, String tripDescript, String fromDate, String toDate) {
        DatabaseReference tourRef = databaseReference.child("users").child(userId).child("tours");
        Tour tour = new Tour(tripName,tripDescript,fromDate,toDate);
        tourRef.push().setValue(tour).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddTourActivity.this,"Added",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        tripNameEt = findViewById(R.id.tripNameEtId);
        tripDescripEt = findViewById(R.id.descriptionEtId);
        fromDateTv = findViewById(R.id.tripStartDateTvId);
        toDateTv = findViewById(R.id.tripEndDateTvId);
        addTripBtn = findViewById(R.id.addTripBtnId);
        tours = new ArrayList<>();
        tourAdapter = new TourAdapter(tours,this);
        recyclerView = findViewById(R.id.addTourRVId);


    }

    private void configRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tourAdapter);
    }


}

