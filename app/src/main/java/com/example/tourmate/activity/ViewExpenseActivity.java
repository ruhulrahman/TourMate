package com.example.tourmate.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.tourmate.R;
import com.example.tourmate.adapter.ExpenseAdapter;
import com.example.tourmate.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class ViewExpenseActivity extends AppCompatActivity {
    private List<Expense> expenses;
    private RecyclerView expenseRV;
    private Context context;
    private ExpenseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);

        init();
        getExpense();
        configRecyclerView();
    }

    private void init() {
        expenseRV = findViewById(R.id.expenseRV);
        expenses = new ArrayList<>();
        adapter = new ExpenseAdapter(expenses, context);
    }
    private void getExpense() {
        
    }

    private void configRecyclerView() {
        expenseRV.setLayoutManager(new LinearLayoutManager(this));
        expenseRV.setAdapter(adapter);
    }



    public void back(View view) {
        onBackPressed();
    }
}
