package com.pharmacy.meds.viewmodel;


import java.util.List;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.pharmacy.meds.BasicApp;
import com.pharmacy.meds.DataRepository;
import com.pharmacy.meds.db.entities.Order;

public class OrderViewModel extends AndroidViewModel {

    private final int mMedId;
    private final int mPharmacyId;
    private final DataRepository mRepository;

    private LiveData<List<Order>> mObservableOrder;

    public OrderViewModel(@NonNull Application application, DataRepository repository, final int pharmacyId,
                          final int medId) {
        super(application);
        mMedId = medId;
        mPharmacyId = pharmacyId;
        mRepository = repository;
        if (pharmacyId != 0) {
            mRepository.insertOrder(new Order(mPharmacyId, mMedId));
        }
    }

    public LiveData<List<Order>> getOrders() {
        mObservableOrder = mRepository.getOrders();
        return mObservableOrder;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mMedId;

        private final int mPharmacyId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, int pharmacyId, int medId) {
            mApplication = application;
            mMedId = medId;
            mPharmacyId = pharmacyId;
            mRepository = ((BasicApp) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new OrderViewModel(mApplication, mRepository, mPharmacyId, mMedId);
        }
    }
}
