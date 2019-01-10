package com.pharmacy.meds.viewmodel;

import java.util.List;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.pharmacy.meds.BasicApp;
import com.pharmacy.meds.DataRepository;
import com.pharmacy.meds.db.entities.Medicament;
import com.pharmacy.meds.db.entities.Pharmacy;

public class PharmacyListViewModel extends AndroidViewModel {

    private DataRepository mRepository;

    private final MediatorLiveData<List<Pharmacy>> mObservablePharmacies;

    public PharmacyListViewModel(Application application) {
        super(application);

        mObservablePharmacies = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservablePharmacies.setValue(null);

        mRepository = ((BasicApp) application).getRepository();
        LiveData<List<Pharmacy>> pharmacies = mRepository.getPharmacies();

        // observe the changes of the products from the database and forward them
        mObservablePharmacies.addSource(pharmacies, mObservablePharmacies::setValue);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<Pharmacy>> getPharmacies() {
        return mObservablePharmacies;
    }
}
