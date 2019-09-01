package com.example.tourmate.activity.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tourmate.R;
import com.example.tourmate.model.Expense;

import java.util.List;

public class ExpenseDetailsActivity extends AppCompatActivity {
    private List<Expense> expenses;
    private Button editExpenseBtn;
    private TextView amountTV, paymentTypeTV, dateTV, timeTV, descriptionTV, costTV;
    private Double amount;
    private int expenseId;
    private String payment, date, time, desc, costType, tourId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        init();

       if(getIntent().getExtras() != null){
            expenseId = Integer.parseInt(getIntent().getStringExtra("expenseId"));
            amount = getIntent().getDoubleExtra("amount",0);
            payment = getIntent().getStringExtra("payment");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");
            desc = getIntent().getStringExtra("desc");
            costType = getIntent().getStringExtra("costType");
            tourId = getIntent().getStringExtra("tourId");

            amountTV.setText("Cost: "+String.valueOf(amount));
            paymentTypeTV.setText("payment Type: "+payment);
            dateTV.setText("Date: "+date);
            timeTV.setText("Time: "+time);
            descriptionTV.setText("Description: "+desc);
            costTV.setText("Cost Type: "+costType);
       }

        editExpenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpenseDetailsActivity.this, EditExpenseActivity.class);
                intent.putExtra("expenseId",String.valueOf(expenseId));
                intent.putExtra("amount",amount);
                intent.putExtra("payment",payment);
                intent.putExtra("date",date);
                intent.putExtra("time",time);
                intent.putExtra("desc",desc);
                intent.putExtra("costType",costType);
                intent.putExtra("tourId",tourId);
                startActivity(intent);
            }
        });
    }

    private void init() {
        amountTV = findViewById(R.id.amountTV);
        paymentTypeTV = findViewById(R.id.paymentTypeTV);
        dateTV = findViewById(R.id.dateTV);
        timeTV = findViewById(R.id.timeTV);
        descriptionTV = findViewById(R.id.descriptionTV);
        costTV = findViewById(R.id.costTV);
        editExpenseBtn = findViewById(R.id.editExpenseBtn);
    }

    public void back(View view) {
        onBackPressed();
    }
}
