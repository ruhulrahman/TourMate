package com.example.tourmate.fragment;


import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourmate.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment implements OnMapReadyCallback {
 private SupportMapFragment supportMapFragment;

    public NearbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_nearby, container, false);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (supportMapFragment == null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            supportMapFragment= SupportMapFragment.newInstance();
            ft.replace(R.id.map,supportMapFragment).commit();

        }
        supportMapFragment.getMapAsync(this);
        getPermission();
        
        return v;
    }

    private void getPermission() {

        String[] permission= {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            requestPermissions(permission,1);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
