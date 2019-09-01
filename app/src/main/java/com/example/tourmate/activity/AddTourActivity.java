package com.example.tourmate.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tourmate.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTourActivity extends AppCompatActivity {
    private EditText tripNameEt,tripDescripEt;
    private TextView fromDateTv,toDateTv;
    private Button addTripBtn;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private String userId,tripName,tripDescript,toDate,fromDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour);

        init();
        userId = firebaseAuth.getCurrentUser().getUid();

        addTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tripName = tripNameEt.getText().toString();
                tripDescript = tripDescripEt.getText().toString();
                fromDate = fromDateTv.getText().toString();
                toDate = toDateTv.getText().toString();

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
    }


}

