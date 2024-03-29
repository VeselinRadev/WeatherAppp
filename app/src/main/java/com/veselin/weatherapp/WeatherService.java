package com.veselin.weatherapp;

import com.veselin.weatherapp.activities.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mushtaq on 05-11-2018.
 */

public interface WeatherService {
    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("q") String city, @Query("units") String units ,@Query("APPID") String app_id);
}

