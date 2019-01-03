package com.company.my.solapp.others;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.company.my.solapp.data.Local;
import com.company.my.solapp.data.Prevision;
import com.company.my.solapp.data.Weather;
import com.company.my.solapp.data.WindSpeed;

import java.util.Date;
import java.util.List;

@Dao
public interface EntitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save (Prevision prevision);

    @Query("SELECT * FROM prevision WHERE prevision.id_local = :id AND prevision.date = :date")
    LiveData<Prevision> getPrevision (int id, String date);

    @Query("SELECT * FROM prevision WHERE prevision.id_local = :id AND prevision.date = :date AND prevision.last_update > :lastUpdateMax LIMIT 1")
    boolean hasPrevision (int id, String date, Date lastUpdateMax);

    @Query("SELECT local.id FROM local WHERE local.name = :name")
    LiveData<Integer> getIdLocalByName (String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save (Local local);

    @Query("SELECT * FROM local")
    LiveData<List<Local>> getAllLocal ();

    @Query("SELECT * FROM local")
    List<Local> getAllLocalNoLiveData ();

    @Query("SELECT * FROM local")
    boolean hasLocal ();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save (WindSpeed windSpeed);

    @Query("SELECT wind.class_pt FROM wind WHERE wind.id = :id")
    LiveData<String> getWindClassification (int id);

    @Query("SELECT * FROM wind")
    boolean hasWind();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save (Weather weather);

    @Query("SELECT weather.class_pt FROM weather WHERE weather.id = :id")
    LiveData<String> getWeatherClassification (int id);

    @Query("SELECT * FROM weather")
    boolean hasWeather();

}
