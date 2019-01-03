package com.company.my.solapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "wind", primaryKeys = {"id"})
public class WindSpeed {

    @NonNull
    @SerializedName("classWindSpeed")
    @Expose
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("descClassWindSpeedDailyPT")
    @Expose
    @ColumnInfo(name = "class_pt")
    private String classPT;

    @SerializedName("descClassWindSpeedDailyEN")
    @Expose
    @ColumnInfo(name = "class_en")
    private String classEN;


    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getClassPT() {
        return classPT;
    }

    public void setClassPT(String classPT) {
        this.classPT = classPT;
    }

    public String getClassEN() {
        return classEN;
    }

    public void setClassEN(String classEN) {
        this.classEN = classEN;
    }
}
