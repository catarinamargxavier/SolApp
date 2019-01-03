package com.company.my.solapp.others;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.company.my.solapp.data.Local;
import com.company.my.solapp.data.Prevision;

import java.util.List;

public class PrevisionViewModel extends AndroidViewModel {

    private MyRepository repository;


    public PrevisionViewModel(Application application) {
        super(application);
        repository = new MyRepository(application);
    }


    public LiveData<Prevision> getPrevision(int id, String date) {
        return repository.getPrevision(id, date);
    }


    public LiveData<List<Local>> getAllLocal() {
        return repository.getAllLocal();
    }


    public LiveData<Integer> getIdLocal(String name) {
        return repository.getIdLocal(name);
    }


    public void getAllPrevisionData() {
        repository.getAllPrevision();
    }


    public LiveData<String> getWindClassification (int id) {
        return repository.getWindById(id);
    }


    public LiveData<String> getWeatherClassification (int id) {
        return repository.getWeatherById(id);
    }

}
