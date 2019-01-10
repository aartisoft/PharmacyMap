package com.pharmacy.meds;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.util.Log;
import android.widget.Toast;

import com.pharmacy.meds.db.AppDatabase;
import com.pharmacy.meds.db.entities.Medicament;
import com.pharmacy.meds.db.entities.Pharmacy;

public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<Pharmacy>> mObservablePharmacies;
    private MediatorLiveData<List<Medicament>> mObservableMedicament;

    public DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservablePharmacies = new MediatorLiveData<>();
        mObservableMedicament = new MediatorLiveData<>();

        mObservablePharmacies.addSource(mDatabase.pharmacyDao().getAll(),
            pharmacyList -> {
                if (mDatabase.getDatabaseCreated().getValue() != null) {
                    mObservablePharmacies.postValue(pharmacyList);
                }
            });

        mObservableMedicament.addSource(mDatabase.medicamentDao().getAll(),
            medicamentList -> {
                if (mDatabase.getDatabaseCreated().getValue() != null) {
                    mObservableMedicament.postValue(medicamentList);
                }
            });
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<Pharmacy>> getPharmacies() {
        return mObservablePharmacies;
    }

    public LiveData<List<Medicament>> getMeds() {
        return mObservableMedicament;
    }

    public LiveData<Pharmacy> loadPharmacy(final int pharmacyId) {
        return mDatabase.pharmacyDao().loadPharmacy(pharmacyId);
    }

    public LiveData<Pharmacy> loadPharmacyByName(final String pharmacyName) {
        return mDatabase.pharmacyDao().loadPharmacyByName(pharmacyName);
    }

    public LiveData<List<Medicament>> loadMeds(final int pharmacyId) {
        return mDatabase.medicamentDao().loadMeds(pharmacyId);
    }
}
