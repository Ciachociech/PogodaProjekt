package pl.zzpwj.data;

import java.util.Date;

public interface WeatherDataListInterface {
    public Integer millisecondsInSecond = 1000;
    public WeatherData getWeatherDataByDate(Date date);
    public boolean addWeatherData(WeatherData data);
}
