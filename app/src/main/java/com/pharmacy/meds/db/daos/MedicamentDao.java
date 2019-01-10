package com.pharmacy.meds.db.daos;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import com.pharmacy.meds.db.entities.Medicament;

@Dao
public interface MedicamentDao {
    @Query("SELECT * FROM medicament")
    LiveData<List<Medicament>> getAll();

    @Query("SELECT * FROM medicament WHERE mId IN (:medicamentIds)")
    LiveData<List<Medicament>> loadAllByIds(int[] medicamentIds);

    @Query("SELECT * FROM medicament WHERE ownerId = :pId")
    LiveData<List<Medicament>> loadMeds(int pId);

    @Query("SELECT * FROM medicament WHERE name LIKE :name LIMIT 1")
    LiveData<Medicament> findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Medicament> meds);

    @Insert
    void insert(Medicament... medicament);

    @Delete
    void delete(Medicament medicament);

    @Query("SELECT * FROM medicament LIMIT 1")
    LiveData<Medicament> getAnyMedicament();
}
