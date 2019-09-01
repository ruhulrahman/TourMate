package com.example.tourmate.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.tourmate.R;
import com.example.tourmate.fragment.MemoriesFragment;
import com.example.tourmate.fragment.NearbyFragment;
import com.example.tourmate.fragment.ProfileFragment;
import com.example.tourmate.fragment.TripFragment;
import com.example.tourmate.fragment.WalletFragment;
import com.example.tourmate.fragment.WeatherFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.trip:
                    replaceFragment(new TripFragment());
                    return true;
                case R.id.weather:
                    replaceFragment(new WeatherFragment());
                    return true;
                case R.id.nearByMap:
                    replaceFragment(new NearbyFragment());
                    return true;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    return true;
            }

            return false;
        }
    };

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId, fragment).commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        replaceFragment(new TripFragment());;
    }
}
