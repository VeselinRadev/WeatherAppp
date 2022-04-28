package com.veselin.weatherapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.veselin.weatherapp.Utils.IconProvider;
import com.veselin.weatherapp.models.API;
import com.veselin.weatherapp.R;
import com.veselin.weatherapp.WeatherService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherDetails extends AppCompatActivity {
    private String city;
    @BindView(R.id.textViewCardCityName) TextView textViewCityName;
    @BindView(R.id.textViewCardWeatherDescription) TextView textViewWeatherDescription;
    @BindView(R.id.textViewCardCurrentTemp) TextView textViewCurrentTemp;
    @BindView(R.id.textViewCardMaxTemp) TextView textViewMaxTemp;
    @BindView(R.id.textViewCardMinTemp) TextView  textViewMinTemp;
    @BindView(R.id.imageViewCardWeatherIcon) ImageView imageViewWeatherIcon;


    @BindView(R.id.textViewHumidity ) TextView textViewHumidity;
    @BindView(R.id.textViewWind) TextView textViewWind;
    @BindView(R.id.textViewCloudiness) TextView textViewCloudiness;
    @BindView(R.id.textViewPressure) TextView textViewPressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        ButterKnife.bind(this);
        city = getIntent().getStringExtra("city");
        getCurrentData();
    }
    private void getCurrentData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(city, "metric",API.KEY);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                Log.d("TAG", "onResponse: " + response.toString());
                if (response.code() == 200) {

                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;
                    textViewCityName.setText(weatherResponse.name );
                    textViewCurrentTemp.setText((float) weatherResponse.main.temp + "°");
                    textViewMaxTemp.setText((float) weatherResponse.main.temp_min +"°");
                    textViewMinTemp.setText((float) weatherResponse.main.temp_max+"°");
                    textViewWeatherDescription.setText(weatherResponse.weather.get(0).description);
                    textViewHumidity.setText((float) weatherResponse.main.humidity +"%");
                    textViewWind.setText((float) weatherResponse.wind.speed+" m/s");
                    textViewCloudiness.setText((float) weatherResponse.clouds.all +"%");
                    textViewPressure.setText((float) weatherResponse.main.pressure+" hPa");
                    Picasso.get().load(IconProvider.getImageIcon(weatherResponse.weather.get(0).description)).into(imageViewWeatherIcon);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Toast.makeText(WeatherDetails.this, "Failed fetching data", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + t.toString());
            }
        });
    }
}