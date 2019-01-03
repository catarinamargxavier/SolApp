package com.company.my.solapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjectWeather {

    @SerializedName("owner")
    private String owner;

    @SerializedName("country")
    private String country;

    @SerializedName("data")
    private List<Weather> data;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Weather> getData() {
        return data;
    }

    public void setData(List<Weather> data) {
        this.data = data;
    }

}
