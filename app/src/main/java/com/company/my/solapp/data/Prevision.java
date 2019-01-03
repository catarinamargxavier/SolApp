package com.company.my.solapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "prevision", primaryKeys = {"date", "id_local"}/*, foreignKeys = {
        @ForeignKey(
                entity = Local.class,
                parentColumns = "id",
                childColumns = "id_local",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = WindSpeed.class,
                parentColumns = "id",
                childColumns = "class_wind_speed",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = Weather.class,
                parentColumns = "id",
                childColumns = "id_type",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
}*/)
public class Prevision {

    @SerializedName("precipitaProb")
    @Expose
    @ColumnInfo(name = "prob_rain")
    private double probRain;

    @SerializedName("tMin")
    @Expose
    @ColumnInfo(name = "temp_min")
    private double tempMin;

    @SerializedName("tMax")
    @Expose
    @ColumnInfo(name = "temp_max")
    private double tempMax;

    /*
    @SerializedName("predWindDir")
    @Expose
    @ColumnInfo(name = "wind_direction")
    private String windDirection;
    */

    @SerializedName("idWeatherType")
    @Expose
    @ColumnInfo(name = "id_type")
    private int idType;

    @SerializedName("classWindSpeed")
    @Expose
    @ColumnInfo(name = "class_wind_speed")
    private int classWindSpeed;

    /*
    @SerializedName("longitude")
    @Expose
    @ColumnInfo(name = "longitude")
    private double longitude;
    */

    /*
    @SerializedName("latitude")
    @Expose
    @ColumnInfo(name = "latitude")
    private double latitude;
    */

    @SerializedName("forecastDate")
    @Expose
    @ColumnInfo(name = "date")
    @NonNull
    private String date;

    @ColumnInfo(name = "id_local")
    @NonNull
    private int idLocal;

    @ColumnInfo(name = "last_update")
    private Date lastUpdate;


    public double getProbRain() {
        return probRain;
    }


    public void setProbRain(double probRain) {
        this.probRain = probRain;
    }


    public double getTempMin() {
        return tempMin;
    }


    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }


    public double getTempMax() {
        return tempMax;
    }


    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    /*
    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }
    */

    public int getIdType() {
        return idType;
    }


    public void setIdType(int idType) {
        this.idType = idType;
    }


    public int getClassWindSpeed() {
        return classWindSpeed;
    }


    public void setClassWindSpeed(int classWindSpeed) {
        this.classWindSpeed = classWindSpeed;
    }

    /*
    public double getLongitude() {
        return longitude;
    }
    */

    /*
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    */

    /*
    public double getLatitude() {
        return latitude;
    }
    */

    /*
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    */

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public int getIdLocal() {
        return idLocal;
    }


    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    /*
    public String getWindDirection() {
        return windDirection;
    }
    */

    public Date getLastUpdate() {
        return lastUpdate;
    }


    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}