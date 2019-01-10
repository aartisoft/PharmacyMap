package com.pharmacy.meds.activities;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pharmacy.meds.R;
import com.pharmacy.meds.db.entities.Pharmacy;
import com.pharmacy.meds.viewmodel.PharmacyListViewModel;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private List<? extends Pharmacy> pharmacies;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(this);

        mMap = googleMap;

        LatLng granada = new LatLng(37.176221, -3.597749);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(granada));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(granada, 15.0f));
        initData();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        int pharmacyId = getPharmacyId(marker.getTitle());
        startActivity(new Intent(MapsActivity.this, PharmacyActivity.class)
            .putExtra("pharmacyId", pharmacyId));
        return true;
    }

    private void subscribeUi(LiveData<List<Pharmacy>> liveData) {
        // Update the list when the data changes
        liveData.observe(this, pharmacies -> {
            if (pharmacies != null) {
                addMarkers(pharmacies);
            } else {
                Toast.makeText(MapsActivity.this, "Loading", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initData() {
        final PharmacyListViewModel viewModel = ViewModelProviders.of(this).get(PharmacyListViewModel.class);
        subscribeUi(viewModel.getPharmacies());
    }

    private void addMarkers(final List<? extends Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
        Pharmacy pharmacy; LatLng position; String name;
        for (int i = 0; i < pharmacies.size(); i++) {
            pharmacy = pharmacies.get(i);
            name = pharmacy.name;
            position = new LatLng(Double.valueOf(pharmacy.latitude), Double.valueOf(pharmacy.longitude));
            mMap.addMarker(new MarkerOptions().position(position).title(name));
        }
    }

    private int getPharmacyId (final String name) {
        for (int i = 0; i < pharmacies.size(); i++) {
            if (pharmacies.get(i).name.equals(name)) {
                return pharmacies.get(i).pId;
            }
        }
        return 0;
    }
}
