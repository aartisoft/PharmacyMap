package com.pharmacy.meds.db;

import java.util.ArrayList;
import java.util.List;

import com.pharmacy.meds.db.entities.Medicament;
import com.pharmacy.meds.db.entities.Pharmacy;

public class DataGenerator {

    public  static List<Pharmacy> generatePharmacies() {
        List<Pharmacy> pharmacies = new ArrayList<>();
        pharmacies.add(new Pharmacy("Farmacia María Antonia Rojas Gómez","37.1747038","-3.5999424"));
        pharmacies.add(new Pharmacy("Farmacia Rivers Pharmacy Zarco","37.1735608","-3.6003369"));
        pharmacies.add(new Pharmacy("Farmacia Josefa Alvarez Mendoza","37.1749","-3.5998774"));
        pharmacies.add(new Pharmacy("Farmacia Nestares Mesones","37.1749128","-3.600624"));

        return pharmacies;
    }

    public  static List<Medicament> generateMedsForPharmacies(final List<Pharmacy> pharmacies) {
        List<Medicament> meds = new ArrayList<>();
        meds.add(new Medicament(1, "DOLIPRANE", 9));
        meds.add(new Medicament(1, "DAFALGAN", 11));
        meds.add(new Medicament(1, "PREVISCAN", 14));
        meds.add(new Medicament(2, "DOLIPRANE", 3));
        meds.add(new Medicament(2, "GINKOR", 4));
        meds.add(new Medicament(2, "SPASFON", 5));
        meds.add(new Medicament(2, "SPEDIFEN", 7));
        meds.add(new Medicament(2, "VOLTARENE", 1));
        meds.add(new Medicament(3, "DOLIPRANE", 5));
        meds.add(new Medicament(3, "DAFALGAN", 10));
        meds.add(new Medicament(3, "PREVISCAN", 10));
        meds.add(new Medicament(3, "GINKOR", 18));
        meds.add(new Medicament(3, "SPASFON", 15));
        meds.add(new Medicament(3, "SPEDIFEN", 15));
        meds.add(new Medicament(3, "VOLTARENE", 15));
        meds.add(new Medicament(4, "SPASFON", 13));
        meds.add(new Medicament(4, "DOLIPRANE", 1));
        meds.add(new Medicament(4, "SPEDIFEN", 3));
        return meds;
    }

    public static String getPharmacyName(int id) {
        List<Pharmacy> pharmacies = generatePharmacies();
        switch (id) {
            case 1:
                return pharmacies.get(0).name;
            case 2:
                return pharmacies.get(1).name;
            case 3:
                return pharmacies.get(2).name;
            case 4:
                return pharmacies.get(3).name;
        }
        return "";
    }

    public static String getMedicamentName(int id) {
        List<Medicament> meds = new ArrayList<>();
        switch (id) {
            case 1:
                return meds.get(0).name;
            case 2:
                return meds.get(1).name;
            case 3:
                return meds.get(2).name;
            case 4:
                return meds.get(3).name;
            case 5:
                return meds.get(4).name;
            case 6:
                return meds.get(5).name;
            case 7:
                return meds.get(6).name;
            case 8:
                return meds.get(7).name;
            case 9:
                return meds.get(8).name;
            case 10:
                return meds.get(9).name;
            case 11:
                return meds.get(10).name;
            case 12:
                return meds.get(11).name;
            case 13:
                return meds.get(12).name;
            case 14:
                return meds.get(13).name;
            case 15:
                return meds.get(14).name;
            case 16:
                return meds.get(15).name;
            case 17:
                return meds.get(16).name;
            case 18:
                return meds.get(17).name;
        }
        return "";
    }
}
