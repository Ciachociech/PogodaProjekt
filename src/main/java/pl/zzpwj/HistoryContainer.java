package pl.zzpwj;

import java.util.ArrayList;
import java.util.Date;

import lombok.*;

@Getter
public class HistoryContainer
{
    ArrayList<WeatherData> history = new ArrayList<>();

    public WeatherData getWeatherDataByDate(Date date) {
        for (WeatherData data : history) {
            if(data.getActualTime() == date.getTime()) {
                return data;
            }
        }
        return null;
    }

    public ArrayList<WeatherData> getHistoryOfPoint(Point point) {
        ArrayList<WeatherData> pointHistory = new ArrayList<>();
        for (WeatherData data : history) {
            if(data.getPoint() == point) {
                pointHistory.add(data);
            }
        }
        return pointHistory;
    }

    public boolean addHistoryData(WeatherData data) {
        if(getWeatherDataByDate(data.getActualTimeAsDate()) != null) {
            history.add(data);
            return true;
        }
        return false;
    }
}
