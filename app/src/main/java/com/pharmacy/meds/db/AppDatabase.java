package com.pharmacy.meds.db;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.pharmacy.meds.AppExecutors;
import com.pharmacy.meds.db.daos.MedicamentDao;
import com.pharmacy.meds.db.daos.PharmacyDao;
import com.pharmacy.meds.db.entities.Medicament;
import com.pharmacy.meds.db.entities.Pharmacy;

@Database(entities = {Pharmacy.class, Medicament.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "pharmacy_database";

    public abstract PharmacyDao pharmacyDao();
    public abstract MedicamentDao medicamentDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private static AppDatabase sInstance;

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context appContext, final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
            .addCallback(new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    executors.diskIO().execute(() -> {
                        // Add a delay to simulate a long-running operation
                        addDelay();
                        // Generate the data for pre-population
                        AppDatabase database = AppDatabase.getInstance(appContext, executors);
                        List<Pharmacy> pharmacies = DataGenerator.generatePharmacies();
                        List<Medicament> meds = DataGenerator.generateMedsForPharmacies(pharmacies);



                        insertData(database, pharmacies, meds);
                        // notify that the database was created and it's ready to be used
                        database.setDatabaseCreated();
                    });
                }
            })
            .build();
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    private static void insertData(final AppDatabase database, final List<Pharmacy> pharmacies,
                                   final List<Medicament> meds) {
        database.runInTransaction(() -> {
            database.pharmacyDao().insertAll(pharmacies);
            database.medicamentDao().insertAll(meds);
        });
    }
}
