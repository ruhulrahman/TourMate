package com.example.tourmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SpashActivity extends AppCompatActivity {
    private ImageView logoIV;
    private TextView tourmateTV;
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

        logoIV = findViewById(R.id.logoIV);
        tourmateTV = findViewById(R.id.tourmateTV);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logoIV.startAnimation(animation);
        tourmateTV.startAnimation(animation);
    }
}
