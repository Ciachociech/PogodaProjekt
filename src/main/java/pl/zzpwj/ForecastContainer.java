package pl.zzpwj;

import java.util.ArrayList;
import lombok.*;

@Getter
public class ForecastContainer
{
    ArrayList<WeatherData> forecast = new ArrayList<>();
}
