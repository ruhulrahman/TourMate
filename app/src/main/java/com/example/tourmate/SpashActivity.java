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
    private TextView tourmateTV, txtTV;
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
        txtTV = findViewById(R.id.txtTV);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        Animation zoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        logoIV.startAnimation(zoomOut);
        tourmateTV.startAnimation(slideUp);
        txtTV.startAnimation(fadeIn);
    }
}
