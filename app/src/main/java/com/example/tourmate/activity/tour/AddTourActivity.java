package com.example.tourmate.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tourmate.R;
import com.example.tourmate.model.Tour;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTourActivity extends AppCompatActivity {
    private EditText tripNameEt,tripDescripEt;
    private TextView fromDateTv,toDateTv;
    private Button addTripBtn;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private String userId,tripName,tripDescript,toDate,fromDate;
    final Calendar myCalendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour);

        init();

        fromDatePicker();

        toDatePicker();


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
        DatabaseReference tourRef = databaseReference.child("users").child(userId).child("tours").child("tourInfo");
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



    }

    private void fromDatePicker() {
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                formatDate();
            }
        };


        fromDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddTourActivity.this, dateSetListener, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void formatDate() {

        String myFormat = "dd/MMM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        fromDateTv.setText(sdf.format(myCalendar.getTime()));
    }

    private void toDatePicker() {
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                formatToDate();
            }
        };


        toDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddTourActivity.this, dateSetListener, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void formatToDate() {

        String myFormat = "dd/MMM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        toDateTv.setText(sdf.format(myCalendar.getTime()));
    }




}

