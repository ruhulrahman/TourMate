package com.example.tourmate.activity.tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.tourmate.R;
import com.example.tourmate.databinding.ActivityAddTourBinding;
import com.example.tourmate.helper.TourDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTourActivity extends AppCompatActivity {
    private ActivityAddTourBinding binding;
    private TourDatabase helper;
    private String tourTitle,tourLocation, startDate, endDate, tourDesc;
    private boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil. setContentView(this, R.layout.activity_add_tour);

        helper = new TourDatabase(this);

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

        tourDataInsert();
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

    private void tourDataInsert() {
        result = false;
        binding.AddTourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tourTitle = binding.tourTitleET.getText().toString();
                tourLocation = binding.tourLocationET.getText().toString();
                startDate = binding.startDateET.getText().toString();
                endDate = binding.endDateET.getText().toString();
                tourDesc = binding.tourDescET.getText().toString();

                if(tourTitle.equals("")){
                    Toast.makeText(AddTourActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                }else if(tourLocation.equals("")){
                    Toast.makeText(AddTourActivity.this, "Please enter location", Toast.LENGTH_SHORT).show();
                }else if(startDate.equals("")){
                    Toast.makeText(AddTourActivity.this, "Please pickup start date", Toast.LENGTH_SHORT).show();
                }else if(endDate.equals("")){
                    Toast.makeText(AddTourActivity.this, "Please pickup end date", Toast.LENGTH_SHORT).show();
                }else{
                    if (tourDesc.equals("")){
                        long id = helper.insertData(tourTitle, tourLocation, startDate, endDate);
                        result = true;
                        if(result == true){
                            binding.tourTitleET.setText("");
                            binding.tourLocationET.setText("");
                            binding.startDateET.setText("");
                            binding.endDateET.setText("");
                            binding.tourDescET.setText("");
                        }
                        Toast.makeText(AddTourActivity.this, "Tour inserted"+id, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddTourActivity.this, ViewTourActivity.class));
                    }else{
                        long id = helper.insertData(tourTitle, tourLocation, startDate, endDate, tourDesc);
                        result = true;
                        if(result == true){
                            binding.tourTitleET.setText("");
                            binding.tourLocationET.setText("");
                            binding.startDateET.setText("");
                            binding.endDateET.setText("");
                            binding.tourDescET.setText("");
                        }
                        Toast.makeText(AddTourActivity.this, "Tour inserted"+id, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddTourActivity.this, ViewTourActivity.class));
                    }



                }


            }
        });
    }





}
