package com.pharmacy.meds.db;

import java.util.ArrayList;
import java.util.List;

import com.pharmacy.meds.db.entities.Medicament;
import com.pharmacy.meds.db.entities.Pharmacy;

class DataGenerator {

    static List<Pharmacy> generatePharmacies() {
        List<Pharmacy> pharmacies = new ArrayList<>();
        pharmacies.add(new Pharmacy("Farmacia María Antonia Rojas Gómez","37.1747038","-3.5999424"));
        pharmacies.add(new Pharmacy("Farmacia Rivers Pharmacy Zarco","37.1735608","-3.6003369"));
        pharmacies.add(new Pharmacy("Farmacia Josefa Alvarez Mendoza","37.1749","-3.5998774"));
        pharmacies.add(new Pharmacy("Farmacia Nestares Mesones","37.1749128","-3.600624"));

        return pharmacies;
    }

    static List<Medicament> generateMedsForPharmacies(final List<Pharmacy> pharmacies) {
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
//        meds.add(new Medicament(pharmacies.get(0).pId, "DOLIPRANE", 9));
//        meds.add(new Medicament(pharmacies.get(0).pId, "DAFALGAN", 11));
//        meds.add(new Medicament(pharmacies.get(0).pId, "PREVISCAN", 14));
//        meds.add(new Medicament(pharmacies.get(1).pId, "DOLIPRANE", 3));
//        meds.add(new Medicament(pharmacies.get(1).pId, "GINKOR", 4));
//        meds.add(new Medicament(pharmacies.get(1).pId, "SPASFON", 5));
//        meds.add(new Medicament(pharmacies.get(1).pId, "SPEDIFEN", 7));
//        meds.add(new Medicament(pharmacies.get(1).pId, "VOLTARENE", 1));
//        meds.add(new Medicament(pharmacies.get(2).pId, "DOLIPRANE", 5));
//        meds.add(new Medicament(pharmacies.get(2).pId, "DAFALGAN", 10));
//        meds.add(new Medicament(pharmacies.get(2).pId, "PREVISCAN", 10));
//        meds.add(new Medicament(pharmacies.get(2).pId, "GINKOR", 18));
//        meds.add(new Medicament(pharmacies.get(2).pId, "SPASFON", 15));
//        meds.add(new Medicament(pharmacies.get(2).pId, "SPEDIFEN", 15));
//        meds.add(new Medicament(pharmacies.get(2).pId, "VOLTARENE", 15));
//        meds.add(new Medicament(pharmacies.get(3).pId, "SPASFON", 13));
//        meds.add(new Medicament(pharmacies.get(3).pId, "DOLIPRANE", 1));
//        meds.add(new Medicament(pharmacies.get(3).pId, "SPEDIFEN", 3));

        return meds;
    }
}
