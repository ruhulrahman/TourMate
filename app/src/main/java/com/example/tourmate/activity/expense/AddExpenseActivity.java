package com.example.tourmate.activity.expense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tourmate.R;
import com.example.tourmate.activity.tour.DetailsTourActivity;
import com.example.tourmate.databinding.ActivityAddExpenseBinding;
import com.example.tourmate.helper.ExpenseDatabase;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddExpenseActivity extends AppCompatActivity {
    private ActivityAddExpenseBinding binding;
    private ExpenseDatabase helper;
    private Double amount;
    private String payment, date, time, desc, costType;
    private boolean result;
    private String tourId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_expense);
        helper = new ExpenseDatabase(this);

        binding.dateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });
        binding.timeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimePicker();
            }
        });

        expenseDataInsert();
    }

    private void openDatePicker() {
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
                binding.dateET.setText(simpleDateFormat.format(date));
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void openTimePicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.custom_time_picker, null);
        final TimePicker timePicker = view.findViewById(R.id.timePicker);
        Button done = view.findViewById(R.id.doneBtn);
        builder.setView(view);
        final Dialog dialog = builder.create();
        dialog.show();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint({"NewApi", "LocalSuppress"}) int hour = timePicker.getHour();
                @SuppressLint({"NewApi", "LocalSuppress"}) int min = timePicker.getMinute();
                Time time = new Time(hour, min, 0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss aa");
                binding.timeET.setText(simpleDateFormat.format(time));
                dialog.dismiss();
            }
        });
    }


    private void expenseDataInsert() {
        result = false;
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Double.parseDouble(binding.addAMountET.getText().toString());
                payment = binding.paymentSpinner.getSelectedItem().toString();
                date = binding.dateET.getText().toString();
                time = binding.timeET.getText().toString();
                desc = binding.descriptionET.getText().toString();
                costType = binding.costSpinner.getSelectedItem().toString();
                tourId = "1";

                if(amount.equals("")){
                    Toast.makeText(AddExpenseActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                }else if(date.equals("")){
                    Toast.makeText(AddExpenseActivity.this, "Please pickup date", Toast.LENGTH_SHORT).show();
                }else if(time.equals("")){
                    Toast.makeText(AddExpenseActivity.this, "Please pickup time", Toast.LENGTH_SHORT).show();
                }else{
                    if (desc.equals("")){
                        long id = helper.insertData(amount, payment, date, time, costType, tourId);
                        result = true;
                        if(result == true){
                            binding.addAMountET.setText("");
                            binding.dateET.setText("");
                            binding.timeET.setText("");
                            binding.descriptionET.setText("");
                            binding.paymentSpinner.setSelection(0);
                            binding.costSpinner.setSelection(0);
                        }
                        Toast.makeText(AddExpenseActivity.this, "Data inserted"+id, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddExpenseActivity.this, DetailsTourActivity.class));
                    }else{
                        long id = helper.insertData(amount, payment, date, time, desc, costType, tourId);
                        result = true;
                        if(result == true){
                            binding.addAMountET.setText("");
                            binding.dateET.setText("");
                            binding.timeET.setText("");
                            binding.descriptionET.setText("");
                            binding.paymentSpinner.setSelection(0);
                            binding.costSpinner.setSelection(0);
                        }
                        Toast.makeText(AddExpenseActivity.this, "Data inserted"+id, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddExpenseActivity.this, DetailsTourActivity.class));
                    }



                }


            }
        });
    }

}
