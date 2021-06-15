package pl.zzpwj.data;

import java.util.ArrayList;
import java.util.Date;

import lombok.*;

@Getter
public class ForecastContainer implements WeatherDataListInterface
{
    ArrayList<WeatherData> forecast = new ArrayList<>();

    @Override
    public WeatherData getWeatherDataByDate(Date date) {
        for (WeatherData data : forecast) {
            if(data.getActualTime().equals(date.getTime() / millisecondsInSecond)) {
                return data;
            }
        }
        return new WeatherData.NullWeatherData();
    }

    @Override
    public boolean addHistoryData(WeatherData data) {
        if(forecast.isEmpty()) {
            forecast.add(data);
            return true;
        }
        if(getWeatherDataByDate(data.getActualTimeAsDate()).getClass() == WeatherData.NullWeatherData.class && checkPointValidityWithListObjects(data.getPoint())) {
            forecast.add(data);
            return true;
        }
        return false;
    }

    private boolean checkPointValidityWithListObjects(Point point) {
        for(WeatherData data : forecast) {
            if(!data.getPoint().equals(point)) {
                return false;
            }
        }
        return true;
    }
}
