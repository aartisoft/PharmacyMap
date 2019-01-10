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
import com.pharmacy.meds.db.entities.Medicament;

public class PharmacyViewModel extends AndroidViewModel {

    private final int mPharmacyId;

    private LiveData<List<Medicament>> mObservableMedicament;

    public PharmacyViewModel(@NonNull Application application, DataRepository mRepository, final int pharmacyId) {
        super(application);
        mPharmacyId = pharmacyId;

        mObservableMedicament = mRepository.loadMeds(mPharmacyId);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<Medicament>> getMeds() {
        return mObservableMedicament;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mProductId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, int productId) {
            mApplication = application;
            mProductId = productId;
            mRepository = ((BasicApp) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PharmacyViewModel(mApplication, mRepository, mProductId);
        }
    }
}
