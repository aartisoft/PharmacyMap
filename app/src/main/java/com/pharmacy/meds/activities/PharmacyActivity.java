package com.pharmacy.meds.activities;

import java.util.ArrayList;
import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pharmacy.meds.R;
import com.pharmacy.meds.adapters.MedsAdapter;
import com.pharmacy.meds.db.entities.Medicament;
import com.pharmacy.meds.viewmodel.PharmacyViewModel;

public class PharmacyActivity extends AppCompatActivity {

    private List<Medicament> medsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MedsAdapter mAdapter;

    int pharmacyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new MedsAdapter(medsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        pharmacyId = getIntent().getExtras().getInt("pharmacyId");

        initData(pharmacyId);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(PharmacyActivity.this, HomeActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        });
    }

    private void initData(int pharmacyId) {
        PharmacyViewModel.Factory factory = new PharmacyViewModel.Factory(getApplication(), pharmacyId);
        final PharmacyViewModel viewModel = ViewModelProviders.of(this, factory).get(PharmacyViewModel.class);
        subscribeUi(viewModel.getMeds());
    }

    private void subscribeUi(LiveData<List<Medicament>> liveData) {
        // Update the list when the data changes
        liveData.observe(this, meds -> {
            if (meds != null) {
                mAdapter.UpdateAdapterList(meds);
            } else {
                Toast.makeText(PharmacyActivity.this, "Loading", Toast.LENGTH_LONG).show();
            }
        });
    }
}
