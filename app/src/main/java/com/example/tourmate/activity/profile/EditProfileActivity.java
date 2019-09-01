package com.example.tourmate.activity.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tourmate.R;
import com.example.tourmate.adapter.ExpenseAdapter;
import com.example.tourmate.databinding.ActivityEditProfileBinding;
import com.example.tourmate.helper.ExpenseDatabase;

public class EditProfileActivity extends AppCompatActivity {
    private ActivityEditProfileBinding binding;
    private ExpenseDatabase helper;
    private Double amount;
    private int id, updatedID;
    private String payment, date, time, desc, costType, updateId, tourId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }
}
