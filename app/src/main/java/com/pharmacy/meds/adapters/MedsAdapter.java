package com.pharmacy.meds.adapters;

import java.util.List;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pharmacy.meds.R;
import com.pharmacy.meds.db.entities.Medicament;

public class MedsAdapter extends RecyclerView.Adapter<MedsAdapter.MyViewHolder> {

    private List<Medicament> medsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, quantity;
        public ImageView add, delete;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            quantity = view.findViewById(R.id.quantity);

            add = view.findViewById(R.id.add_order);
            delete = view.findViewById(R.id.delete_order);
        }
    }


    public MedsAdapter(List<Medicament> medsList) {
        this.medsList = medsList;
        notifyDataSetChanged();
    }

    public void UpdateAdapterList(List<Medicament> medsList) {
        this.medsList = medsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.medicament_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Medicament medicament = medsList.get(position);
        holder.name.setText(medicament.name);
        holder.quantity.setText(String.valueOf(medicament.quantity));

        holder.add.setOnClickListener(v -> {
            Snackbar.make(v, "Medicament Added To Basket ", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
            if (Integer.valueOf(holder.quantity.getText().toString()) > 0) {
                holder.quantity.setText(String.valueOf(Integer.valueOf(holder.quantity.getText().toString()) - 1));
            }
        });

        holder.delete.setOnClickListener(v -> {
            Snackbar.make(v, "Medicament Deleted From Basket ", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
            holder.quantity.setText(String.valueOf(Integer.valueOf(holder.quantity.getText().toString()) + 1));
        });
    }

    @Override
    public int getItemCount() {
        return medsList.size();
    }
}
