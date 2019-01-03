package com.company.my.solapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity (tableName = "local", primaryKeys = {"id"})
public class Local {

    @SerializedName("idRegiao")
    @Expose
    @ColumnInfo(name = "id_region")
    private int idRegion;

    @SerializedName("idAreaAviso")
    @Expose
    @ColumnInfo(name = "id_warning")
    private String idWarning;

    @SerializedName("idConcelho")
    @Expose
    @ColumnInfo(name = "county")
    private int county;

    @SerializedName("globalIdLocal")
    @Expose
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("latitude")
    @Expose
    @ColumnInfo(name = "latitude")
    private double latitude;

    @SerializedName("longitude")
    @Expose
    @ColumnInfo(name = "longitude")
    private double longitude;

    @SerializedName("local")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("idDistrito")
    @Expose
    @ColumnInfo(name = "id_district")
    private int idDistrict;


    public int getIdRegion() {
        return idRegion;
    }


    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }


    public String getIdWarning() {
        return idWarning;
    }


    public void setIdWarning(String idWarning) {
        this.idWarning = idWarning;
    }


    public int getCounty() {
        return county;
    }


    public void setCounty(int county) {
        this.county = county;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public double getLatitude() {
        return latitude;
    }


    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public double getLongitude() {
        return longitude;
    }


    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getIdDistrict() {
        return idDistrict;
    }


    public void setIdDistrict(int idDistrict) {
        this.idDistrict = idDistrict;
    }

}
