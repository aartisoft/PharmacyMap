package com.pharmacy.meds.db.daos;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import com.pharmacy.meds.db.entities.Pharmacy;

@Dao
public interface PharmacyDao {
    @Query("SELECT * FROM pharmacy")
    LiveData<List<Pharmacy>> getAll();

    @Query("SELECT * FROM pharmacy WHERE pId IN (:pharmacyIds)")
    LiveData<List<Pharmacy>> loadAllByIds(int[] pharmacyIds);

    @Query("SELECT * FROM pharmacy WHERE pId = :id")
    LiveData<Pharmacy> loadPharmacy(int id);

    @Query("SELECT * FROM pharmacy WHERE name LIKE :name LIMIT 1")
    LiveData<Pharmacy> loadPharmacyByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Pharmacy> pharmacies);

    @Insert
    void insert(Pharmacy... pharmacy);

    @Delete
    void delete(Pharmacy pharmacy);

    @Query("SELECT * FROM pharmacy LIMIT 1")
    LiveData<Pharmacy> getAnyPharmacy();
}
