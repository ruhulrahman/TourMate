package com.example.tourmate.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmate.R;
import com.example.tourmate.activity.ExpenseDetailsActivity;
import com.example.tourmate.model.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private List<Expense> expenses;
    private Context context;

    public ExpenseAdapter(List<Expense> expenses, Context context) {
        this.expenses = expenses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Expense expense = expenses.get(position);
        holder.amount.setText(String.valueOf(expense.getAmount()));
        holder.payment.setText(String.valueOf(expense.getPayment()));
        holder.date.setText(String.valueOf(expense.getDate()));
        holder.time.setText(String.valueOf(expense.getTime()));
        holder.desc.setText(String.valueOf(expense.getDesc()));
        holder.costType.setText(String.valueOf(expense.getCostType()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExpenseDetailsActivity.class);
                intent.putExtra("amount", expense.getAmount());
                intent.putExtra("payment", expense.getPayment());
                intent.putExtra("date", expense.getDate());
                intent.putExtra("time", expense.getTime());
                intent.putExtra("desc", expense.getDesc());
                intent.putExtra("costType", expense.getCostType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView amount, payment, date, time, desc, costType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amountTV);
            payment = itemView.findViewById(R.id.paymentTypeTV);
            date = itemView.findViewById(R.id.dateTV);
            time = itemView.findViewById(R.id.timeTV);
            desc = itemView.findViewById(R.id.descriptionTV);
            costType = itemView.findViewById(R.id.costTV);
        }
    }
}
