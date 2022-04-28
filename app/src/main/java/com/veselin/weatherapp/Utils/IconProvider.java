package com.veselin.weatherapp.Utils;


import com.veselin.weatherapp.R;

public class IconProvider {

    public static int getImageIcon(String weatherDescription){
        int weatherIcon ;
        switch(weatherDescription) {
            case "thunderstorm":
                weatherIcon = R.mipmap.ic_atmosphere;
                break;
            case "drizzle":
                weatherIcon = R.mipmap.ic_drizzle;
                break;
            case "rain":
                weatherIcon = R.mipmap.ic_rain;
                break;
            case "snow":
                weatherIcon = R.mipmap.ic_snow;
                break;
            case "atmosphere":
                weatherIcon = R.mipmap.ic_atmosphere;
                break;
            case "clear":
                weatherIcon = R.mipmap.ic_clear;
                break;
            case "clouds":
                weatherIcon = R.mipmap.ic_cloudy;
                break;
            case "overcast clouds":
                weatherIcon = R.mipmap.ic_cloudy;
                break;
            case "broken clouds":
                weatherIcon = R.mipmap.ic_cloudy;
                break;
            case "extreme":
                weatherIcon = R.mipmap.ic_extreme;
                break;
            default:
                weatherIcon = R.mipmap.ic_clear;
        }
        return weatherIcon;

    }

}
