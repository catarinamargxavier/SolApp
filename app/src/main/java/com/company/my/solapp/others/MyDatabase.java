package com.company.my.solapp.others;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.company.my.solapp.data.Local;
import com.company.my.solapp.data.Prevision;
import com.company.my.solapp.data.Weather;
import com.company.my.solapp.data.WindSpeed;

@Database(entities = {Local.class, Prevision.class, Weather.class, WindSpeed.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {

    private static volatile MyDatabase INSTANCE;
    public abstract EntitiesDao EntitiesDao();

    static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "weather_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
