package com.example.tourmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SpashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }

                finally {
                    Intent intent = new Intent(SpashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        };

        thread.start();
    }
}
