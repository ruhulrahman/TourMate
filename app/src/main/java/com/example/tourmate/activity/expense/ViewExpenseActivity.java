package com.example.tourmate.activity.expense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tourmate.R;
import com.example.tourmate.adapter.ExpenseAdapter;
import com.example.tourmate.helper.ExpenseDatabase;
import com.example.tourmate.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class ViewExpenseActivity extends AppCompatActivity {
    private List<Expense> expenses;
    private RecyclerView expenseRV;
    private ExpenseDatabase helper;
    private Context context;
    private ExpenseAdapter adapter;
    private TextView totalAmountTV;

    private int id;
    private Double amount, totalAmount = 0.00;
    private String payment, date, time, desc, costType, tourId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);

        init();
        getData();
        totalAmountTV.setText("Total Expense: "+String.valueOf(totalAmount));
    }

    private void init() {
        expenseRV = findViewById(R.id.expenseRV);
        totalAmountTV = findViewById(R.id.totalAmountTV);

        expenses = new ArrayList<>();
        helper = new ExpenseDatabase(this);
        adapter = new ExpenseAdapter(expenses, helper, this);

        expenseRV.setLayoutManager(new LinearLayoutManager(this));
        expenseRV.setAdapter(adapter);
    }

    private void getData() {
        Cursor cursor = helper.showData();

        while (cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            amount = cursor.getDouble(cursor.getColumnIndex(helper.COL_AMOUNT));
            payment = cursor.getString(cursor.getColumnIndex(helper.COL_PAYMENT_TYPE));
            date = cursor.getString(cursor.getColumnIndex(helper.COL_DATE));
            time = cursor.getString(cursor.getColumnIndex(helper.COL_TIME));
            desc = cursor.getString(cursor.getColumnIndex(helper.COL_DESCRIPTION));
            costType = cursor.getString(cursor.getColumnIndex(helper.COL_COST_TYPE));
            tourId = cursor.getString(cursor.getColumnIndex(helper.COL_TOUR_ID));
            totalAmount += amount;
            expenses.add(new Expense(id, amount, payment, date, time, desc, costType, tourId));
            adapter.notifyDataSetChanged();

        }
    }


    public void back(View view) {
        onBackPressed();
    }
}
