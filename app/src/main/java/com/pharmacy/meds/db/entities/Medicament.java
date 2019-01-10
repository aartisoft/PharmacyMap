package com.pharmacy.meds.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Pharmacy.class,
    parentColumns = "pId",
    childColumns = "ownerId",
    onDelete = ForeignKey.CASCADE))
public class Medicament {
    @PrimaryKey(autoGenerate = true)
    public int mId;

    @ColumnInfo(name = "ownerId")
    public int ownerId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "quantity")
    public int quantity;

    public Medicament(int ownerId, String name, int quantity) {
        this.ownerId = ownerId;
        this.name = name;
        this.quantity = quantity;
    }
}