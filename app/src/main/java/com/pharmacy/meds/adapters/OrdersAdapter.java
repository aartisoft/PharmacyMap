package com.pharmacy.meds.adapters;

import java.util.List;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pharmacy.meds.R;
import com.pharmacy.meds.db.DataGenerator;
import com.pharmacy.meds.db.entities.Medicament;
import com.pharmacy.meds.db.entities.Order;
import com.pharmacy.meds.viewmodel.OrderViewModel;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    private FragmentActivity mContext;
    private Application mApplication;
    private List<Order> ordersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pharmacy, medicament;

        public MyViewHolder(View view) {
            super(view);
            pharmacy = view.findViewById(R.id.pharmacy);
            medicament = view.findViewById(R.id.medicament);
        }
    }


    public OrdersAdapter(Application application, FragmentActivity context, List<Order> ordersList) {
        this.mApplication = application;
        this.mContext = context;
        this.ordersList = ordersList;
        notifyDataSetChanged();
    }

    public void UpdateAdapterList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.order_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Order order = ordersList.get(position);
        holder.pharmacy.setText(DataGenerator.getPharmacyName(order.pharId));
        holder.medicament.setText(DataGenerator.getMedicamentName(order.medId));
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }
}
