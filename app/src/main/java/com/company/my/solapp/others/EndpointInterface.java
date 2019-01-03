package com.company.my.solapp.others;

import com.company.my.solapp.data.ObjectLocal;
import com.company.my.solapp.data.ObjectPrevision;
import com.company.my.solapp.data.ObjectWeather;
import com.company.my.solapp.data.ObjectWindSpeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndpointInterface {

    @GET("forecast/meteorology/cities/daily/{globalIdLocal}.json")
    Call<ObjectPrevision> getPrevisionByLocal (@Path("globalIdLocal") int globalIdLocal);

    @GET("distrits-islands.json")
    Call<ObjectLocal> getLocal();

    @GET("weather-type-classe.json")
    Call<ObjectWeather> getWeather();

    @GET("wind-speed-daily-classe.json")
    Call<ObjectWindSpeed> getWind();

}
