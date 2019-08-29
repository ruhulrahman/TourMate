package com.example.tourmate.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tourmate.R;
import com.example.tourmate.databinding.ActivityAddExpenseBinding;
import com.example.tourmate.helper.ExpenseDatabase;

public class AddExpenseActivity extends AppCompatActivity {
    private ActivityAddExpenseBinding binding;
    private ExpenseDatabase helper;
    private int amount;
    private String payment, date, time, desc, costType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_expense);
        helper = new ExpenseDatabase(this);

        enpenseDataInsert();
    }

    private void enpenseDataInsert() {
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                amount = binding.addAMountET.getText().toString();
//                payment = binding.paymentSpinner.getText().toString();
//                date = binding.dateET.getText().toString();
//                time = binding.timeET.getText().toString();
//                desc = binding.descriptionET.getText().toString();
//                costType = binding.costSpinner.getText().toString();
//                long id = helper.insertData(amount, payment, date, time, desc, costType);
//                Toast.makeText(AddExpenseActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
