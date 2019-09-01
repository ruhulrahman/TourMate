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
import com.example.tourmate.adapter.ExpenseAdapter;
import com.example.tourmate.databinding.ActivityEditExpenseBinding;
import com.example.tourmate.helper.ExpenseDatabase;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditExpenseActivity extends AppCompatActivity {
    private ActivityEditExpenseBinding binding;
    private ExpenseDatabase helper;
    private ExpenseAdapter adapter;
    private Double amount;
    private int id, updatedID, tourId;
    private String payment, date, time, desc, costType, updateId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_expense);
        init();



        if(getIntent().getExtras() != null){
            //updateId = getIntent().getStringExtra("expenseId");
            updatedID = Integer.parseInt(getIntent().getStringExtra("expenseId"));

            amount = getIntent().getDoubleExtra("amount",0);
            payment = getIntent().getStringExtra("payment");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");
            desc = getIntent().getStringExtra("desc");
            costType = getIntent().getStringExtra("costType");
            tourId = getIntent().getIntExtra("tourId", 0);

            binding.addAMountET.setText(String.valueOf(amount));
            //binding.paymentSpinner.setSelected(String.valueOf(payment));
            binding.dateET.setText(date);
            binding.timeET.setText(time);
            binding.descriptionET.setText(desc);

        }


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

        updateExpenseData();
    }

    private void init() {
        helper = new ExpenseDatabase(this);

    }

    private void openDatePicker() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String currentDate = year+"/"+month+"/"+dayOfMonth+" 00:00:00";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
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



    private void updateExpenseData() {
        binding.updateExpenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Double.parseDouble(binding.addAMountET.getText().toString());
                payment = binding.paymentSpinner.getSelectedItem().toString();
                date = binding.dateET.getText().toString();
                time = binding.timeET.getText().toString();
                desc = binding.descriptionET.getText().toString();
                costType = binding.costSpinner.getSelectedItem().toString();

                if(amount.equals("")){
                    Toast.makeText(EditExpenseActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                }else if(date.equals("")){
                    Toast.makeText(EditExpenseActivity.this, "Please pickup date", Toast.LENGTH_SHORT).show();
                }else if(time.equals("")){
                    Toast.makeText(EditExpenseActivity.this, "Please pickup time", Toast.LENGTH_SHORT).show();
                }else{
                    if (desc.equals("")){
                        long id = helper.updateData(updatedID, amount, payment, date, time, costType, tourId);
                        Toast.makeText(EditExpenseActivity.this, "Data inserted"+id, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditExpenseActivity.this, ExpenseDetailsActivity.class));
                    }else{
                        long id = helper.updateData(updatedID, amount, payment, date, time, desc, costType, tourId);
                        Toast.makeText(EditExpenseActivity.this, "Data inserted"+id, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditExpenseActivity.this, ExpenseDetailsActivity.class));
                    }
                }
            }
        });
    }



}
