package com.company.my.solapp.others;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.company.my.solapp.data.Local;
import com.company.my.solapp.data.ObjectLocal;
import com.company.my.solapp.data.ObjectPrevision;
import com.company.my.solapp.data.ObjectWeather;
import com.company.my.solapp.data.ObjectWindSpeed;
import com.company.my.solapp.data.Prevision;
import com.company.my.solapp.data.Weather;
import com.company.my.solapp.data.WindSpeed;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyRepository {

    private static final String BASE_URL = "https://api.ipma.pt/open-data/";
    private MyDatabase db;
    private EndpointInterface apiService;
    private Executor executor;
    private Gson gson;
    private Retrofit retrofit;


    public MyRepository(Application application) {
        db = MyDatabase.getDatabase(application);
        gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();
        retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        apiService = retrofit.create(EndpointInterface.class);
        executor = Executors.newSingleThreadExecutor();
    }


    public LiveData<Prevision> getPrevision(int id, String date) {
        refreshPrevision(id, date);
        return db.EntitiesDao().getPrevision(id, date);
    }


    private void refreshPrevision(final int id, final String date) {
        executor.execute(() -> {
            boolean hasPrevision = db.EntitiesDao().hasPrevision(id, date, getMaxRefreshTime(new Date()));
            if (!hasPrevision) {
                apiService.getPrevisionByLocal(id).enqueue(new Callback<ObjectPrevision>() {
                    @Override
                    public void onResponse(Call<ObjectPrevision> call, Response<ObjectPrevision> response) {
                        ObjectPrevision obj = response.body();
                        if (obj != null) {
                            for (Prevision aux : obj.getData()) {
                                aux.setIdLocal(id);
                                aux.setLastUpdate(new Date());
                                executor.execute(() -> {
                                    db.EntitiesDao().save(aux);
                                });
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ObjectPrevision> call, Throwable t) {
                    }
                });
            }
        });
    }


    private Date getMaxRefreshTime(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -15);
        return cal.getTime();
    }


    public LiveData<List<Local>> getAllLocal() {
        refreshLocal();
        return db.EntitiesDao().getAllLocal();
    }


    private void refreshLocal() {
        executor.execute(() -> {
            boolean hasLocal = db.EntitiesDao().hasLocal();
            if (!hasLocal) {
                apiService.getLocal().enqueue(new Callback<ObjectLocal>() {
                    @Override
                    public void onResponse(Call<ObjectLocal> call, Response<ObjectLocal> response) {
                        ObjectLocal obj = response.body();
                        for (Local aux: obj.getData()) {
                            executor.execute(() -> {
                                db.EntitiesDao().save(aux);
                            });
                        }
                    }
                    @Override
                    public void onFailure(Call<ObjectLocal> call, Throwable t) {
                    }
                });
            }
        });
    }


    public LiveData<Integer> getIdLocal (String name) {
        return db.EntitiesDao().getIdLocalByName(name);
    }


    public void getAllPrevision () {
        executor.execute(() -> {
            List<Local> locals = db.EntitiesDao().getAllLocalNoLiveData();
            for (Local local: locals) {
                apiService.getPrevisionByLocal(local.getId()).enqueue(new Callback<ObjectPrevision>() {
                    @Override
                    public void onResponse(Call<ObjectPrevision> call, Response<ObjectPrevision> response) {
                        ObjectPrevision obj = response.body();
                        if (obj != null) {
                            for (Prevision aux : obj.getData()) {
                                aux.setIdLocal(local.getId());
                                aux.setLastUpdate(new Date());
                                executor.execute(() -> {
                                    db.EntitiesDao().save(aux);
                                });
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ObjectPrevision> call, Throwable t) {
                    }
                });
            }
        });
    }


    public LiveData<String> getWindById(int id) {
        refreshWind();
        return db.EntitiesDao().getWindClassification(id);
    }


    public void refreshWind() {
        executor.execute(() -> {
            boolean hasWind = db.EntitiesDao().hasWind();
            if (!hasWind) {
                apiService.getWind().enqueue(new Callback<ObjectWindSpeed>() {
                    @Override
                    public void onResponse(Call<ObjectWindSpeed> call, Response<ObjectWindSpeed> response) {
                        ObjectWindSpeed obj = response.body();
                        for (WindSpeed aux: obj.getData()) {
                            executor.execute(() -> {
                                db.EntitiesDao().save(aux);
                            });
                        }
                    }
                    @Override
                    public void onFailure(Call<ObjectWindSpeed> call, Throwable t) {
                    }
                });
            }
        });
    }


    public LiveData<String> getWeatherById(int id) {
        refreshWeather();
        return db.EntitiesDao().getWeatherClassification(id);
    }


    public void refreshWeather() {
        executor.execute(() -> {
            boolean hasWeather = db.EntitiesDao().hasWeather();
            if (!hasWeather) {
                apiService.getWeather().enqueue(new Callback<ObjectWeather>() {
                    @Override
                    public void onResponse(Call<ObjectWeather> call, Response<ObjectWeather> response) {
                        ObjectWeather obj = response.body();
                        for (Weather aux: obj.getData()) {
                            executor.execute(() -> {
                                db.EntitiesDao().save(aux);
                            });
                        }
                    }
                    @Override
                    public void onFailure(Call<ObjectWeather> call, Throwable t) {
                    }
                });
            }
        });
    }


}
