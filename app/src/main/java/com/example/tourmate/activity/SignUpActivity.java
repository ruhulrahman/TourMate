package com.example.tourmate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tourmate.R;

import com.example.tourmate.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends AppCompatActivity {
    private String name,email,password;
    private ActivitySignUpBinding binding;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        if (firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(SignUpActivity.this,MainActivity.class));
            finish();
        }

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = binding.nameEt.getText().toString();
                email = binding.emailEt.getText().toString();
                password = binding.passwordEt.getText().toString();

                signUpRegister(name,email,password);
            }
        });
    }

    public void loginClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    private void signUpRegister(final String name, final String email, String password) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    String userId = firebaseAuth.getCurrentUser().getUid();
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("name", name);
                    userMap.put("email", email);

                    DatabaseReference userRef = databaseReference.child("users").child(userId);
                    userRef.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "SignUp successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                                finish();
                            }
                        }
                    });

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
