package com.pharmacy.meds.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
    @ForeignKey(entity = Pharmacy.class,
        parentColumns = "pId",
        childColumns = "pharId",
        onDelete = ForeignKey.CASCADE),
    @ForeignKey(entity = Medicament.class,
        parentColumns = "mId",
        childColumns = "medId",
        onDelete = ForeignKey.CASCADE)
})
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int oId;

    @ColumnInfo(name = "pharId")
    public int pharId;

    @ColumnInfo(name = "medId")
    public int medId;

//    @ColumnInfo(name = "name")
//    public String name;
//
//    @ColumnInfo(name = "quantity")
//    public int quantity;

//    public Order(int medId, String name, int quantity) {
//        this.medId = medId;
//        this.name = name;
//        this.quantity = quantity;
//    }

    public Order(int pharId, int medId) {
        this.pharId = pharId;
        this.medId = medId;
    }
}