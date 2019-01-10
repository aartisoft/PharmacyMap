package com.pharmacy.meds.db.daos;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import com.pharmacy.meds.db.entities.Medicament;
import com.pharmacy.meds.db.entities.Order;

@Dao
public interface OrderDao {
    @Query("SELECT * FROM `order`")
    LiveData<List<Order>> getAll();

    @Query("SELECT * FROM `order` WHERE medId IN (:medicamentIds)")
    LiveData<List<Order>> loadAllByIds(int[] medicamentIds);

    @Query("SELECT * FROM `order` WHERE pharId = :pId")
    LiveData<List<Order>> loadOrders(int pId);

    @Query("SELECT * FROM `order` WHERE oId LIKE :id LIMIT 1")
    LiveData<Order> findByName(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Order> orders);

    @Insert
    void insert(Order order);

    @Delete
    void delete(Order order);

    @Query("SELECT * FROM `order` LIMIT 1")
    LiveData<Order> getAnyOrder();
}
