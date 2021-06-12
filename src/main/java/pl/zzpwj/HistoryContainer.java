package pl.zzpwj;

import java.util.ArrayList;
import java.util.Date;

import lombok.*;

@Getter
public class HistoryContainer implements WeatherDataListInterface
{
    ArrayList<WeatherData> history = new ArrayList<>();

    @Override
    public WeatherData getWeatherDataByDate(Date date) {
        for (WeatherData data : history) {
            if(data.getActualTime().equals(date.getTime() / millisecondsInSecond)) {
                return data;
            }
        }
        return null;
    }

    public ArrayList<WeatherData> getHistoryOfPoint(Point point) {
        ArrayList<WeatherData> pointHistory = new ArrayList<>();
        for (WeatherData data : history) {
            if(data.getPoint().equals(point)) {
                pointHistory.add(data);
            }
        }
        return pointHistory;
    }

    @Override
    public boolean addHistoryData(WeatherData data) {
        if(getWeatherDataByDate(data.getActualTimeAsDate()) == null) {
            history.add(data);
            return true;
        }
        return false;
    }
}
