package com.example.tourmate.activity.tour;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.tourmate.R;
import com.example.tourmate.databinding.ActivityEditTourBinding;
import com.example.tourmate.helper.TourDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditTourActivity extends AppCompatActivity {
    private ActivityEditTourBinding binding;
    private TourDatabase helper;
    private int tourId, updatedID;

    private String tourTitle,tourLocation, startDate, endDate, tourDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tour);

        helper = new TourDatabase(this);

        if(getIntent().getExtras() != null){
            tourId = Integer.parseInt(getIntent().getStringExtra("tourId"));
            tourTitle = getIntent().getStringExtra("tourTitle");
            tourLocation = getIntent().getStringExtra("tourLocation");
            startDate = getIntent().getStringExtra("startDate");
            endDate = getIntent().getStringExtra("endDate");
            tourDesc = getIntent().getStringExtra("tourDesc");

            binding.tourTitleET.setText(tourTitle);
            binding.tourLocationET.setText(tourLocation);
            binding.startDateET.setText(startDate);
            binding.endDateET.setText(endDate);
            binding.tourDescET.setText(tourDesc);
        }


        binding.startDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStartDatePicker();
            }
        });
        binding.endDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEndDatePicker();
            }
        });

        tourDataUpdate();

    }

    private void openStartDatePicker() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String currentDate = year+"/"+month+"/"+dayOfMonth+" 00:00:00";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                try {
                    date = simpleDateFormat.parse(currentDate);
                }catch (ParseException e){
                    e.printStackTrace();
                }

                long dateInMiniSec = date.getTime();
                binding.startDateET.setText(simpleDateFormat.format(date));
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void openEndDatePicker() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String currentDate = year+"/"+month+"/"+dayOfMonth+" 00:00:00";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                try {
                    date = simpleDateFormat.parse(currentDate);
                }catch (ParseException e){
                    e.printStackTrace();
                }

                long dateInMiniSec = date.getTime();
                binding.endDateET.setText(simpleDateFormat.format(date));
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void tourDataUpdate() {
        binding.updateTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tourTitle = binding.tourTitleET.getText().toString();
                tourLocation = binding.tourLocationET.getText().toString();
                startDate = binding.startDateET.getText().toString();
                endDate = binding.endDateET.getText().toString();
                tourDesc = binding.tourDescET.getText().toString();

                if(tourTitle.equals("")){
                    Toast.makeText(EditTourActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                }else if(tourLocation.equals("")){
                    Toast.makeText(EditTourActivity.this, "Please enter location", Toast.LENGTH_SHORT).show();
                }else if(startDate.equals("")){
                    Toast.makeText(EditTourActivity.this, "Please pickup start date", Toast.LENGTH_SHORT).show();
                }else if(endDate.equals("")){
                    Toast.makeText(EditTourActivity.this, "Please pickup end date", Toast.LENGTH_SHORT).show();
                }else{
                    if (tourDesc.equals("")){
                        long id = helper.updateData(tourId, tourTitle, tourLocation, startDate, endDate);
                        Toast.makeText(EditTourActivity.this, "Tour updated"+id, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditTourActivity.this, ViewTourActivity.class));
                    }else{
                        long id = helper.updateData(tourId, tourTitle, tourLocation, startDate, endDate, tourDesc);
                        Toast.makeText(EditTourActivity.this, "Tour updated"+id, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditTourActivity.this, ViewTourActivity.class));
                    }

                }
            }
        });
    }




}
