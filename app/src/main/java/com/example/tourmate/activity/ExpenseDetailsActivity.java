package com.example.tourmate.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tourmate.R;
import com.example.tourmate.model.Expense;

import java.util.List;

public class ExpenseDetailsActivity extends AppCompatActivity {
    private List<Expense> expenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);
    }

    public void back(View view) {
        onBackPressed();
    }
}
