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
import com.example.tourmate.activity.expense.ExpenseDetailsActivity;
import com.example.tourmate.helper.ExpenseDatabase;
import com.example.tourmate.model.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private List<Expense> expenses;
    private ExpenseDatabase helper;
    private Context context;

    public ExpenseAdapter(List<Expense> expenses, ExpenseDatabase helper, Context context) {
        this.expenses = expenses;
        this.helper = helper;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Expense expense = expenses.get(position);
        holder.amount.setText(String.valueOf(expense.getAmount()));
        holder.payment.setText(expense.getPayment());
        holder.date.setText(expense.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExpenseDetailsActivity.class);
                intent.putExtra("expenseId", String.valueOf(expense.getId()));
                intent.putExtra("amount", expense.getAmount());
                intent.putExtra("payment", expense.getPayment());
                intent.putExtra("date", expense.getDate());
                intent.putExtra("time", expense.getTime());
                intent.putExtra("desc", expense.getDesc());
                intent.putExtra("costType", expense.getCostType());
                intent.putExtra("tourId", expense.getTourId());
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                helper = new ExpenseDatabase(context);
                helper.deleteData(expense.getId());
                expenses.remove(position);
                notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView amount, payment, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amountTV);
            payment = itemView.findViewById(R.id.paymentTypeTV);
            date = itemView.findViewById(R.id.dateTV);
        }
    }
}
