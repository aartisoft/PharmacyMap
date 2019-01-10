package com.pharmacy.meds.activities;

import java.util.ArrayList;
import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.pharmacy.meds.R;
import com.pharmacy.meds.adapters.OrdersAdapter;
import com.pharmacy.meds.db.entities.Order;
import com.pharmacy.meds.viewmodel.OrderViewModel;

public class HomeActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private List<Order> ordersList = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrdersAdapter mAdapter;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() == null) {
                startActivity(new Intent(HomeActivity.this, SignInActivity.class));
                finish();
            }
        };

        recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new OrdersAdapter(getApplication(), this, ordersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        initData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, MapsActivity.class));
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            mAuth.signOut();
            this.finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initData() {
        OrderViewModel.Factory factory = new OrderViewModel.Factory(getApplication(), 0, 0);
        final OrderViewModel viewModel = ViewModelProviders.of(this, factory).get(OrderViewModel.class);
        subscribeUi(viewModel.getOrders());
    }

    private void subscribeUi(LiveData<List<Order>> liveData) {
        // Update the list when the data changes
        liveData.observe(this, orders -> {
            if (orders != null) {
                mAdapter.UpdateAdapterList(orders);
            } else {
                Toast.makeText(HomeActivity.this, "No Orders", Toast.LENGTH_LONG).show();
            }
        });
    }
}
