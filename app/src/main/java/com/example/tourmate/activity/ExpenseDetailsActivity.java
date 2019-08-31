package com.example.tourmate.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tourmate.R;
import com.example.tourmate.model.Expense;

import java.util.List;

public class ExpenseDetailsActivity extends AppCompatActivity {
    private List<Expense> expenses;
    private TextView amountTV, paymentTypeTV, dateTV, timeTV, descriptionTV, costTV;
    private Double amount;
    private String payment, date, time, desc, costType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        init();

        if(getIntent().getExtras() != null){
            amount = getIntent().getDoubleExtra("amount",0);
            payment = getIntent().getStringExtra("payment");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");
            desc = getIntent().getStringExtra("desc");
            costType = getIntent().getStringExtra("costType");

            amountTV.setText(String.valueOf(amount));
            paymentTypeTV.setText(payment);
            dateTV.setText(date);
            timeTV.setText(time);
            descriptionTV.setText(desc);
            costTV.setText(costType);

        }
    }

    private void init() {
        amountTV = findViewById(R.id.amountTV);
        paymentTypeTV = findViewById(R.id.paymentTypeTV);
        dateTV = findViewById(R.id.dateTV);
        timeTV = findViewById(R.id.timeTV);
        descriptionTV = findViewById(R.id.descriptionTV);
        costTV = findViewById(R.id.costTV);
    }

    public void back(View view) {
        onBackPressed();
    }
}
