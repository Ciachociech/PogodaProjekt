package pl.zzpwj.data;

import lombok.*;

import java.util.Date;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
@AllArgsConstructor (access = AccessLevel.PUBLIC)
@Builder (access = AccessLevel.PUBLIC)
@Getter
@ToString
@EqualsAndHashCode
public class WeatherData implements WeatherDataInterface {
    private Point point;
    private Float actualTemperature;
    private Float feelTemperature;
    private Float pressure;
    private Float humidity;
    private Float windVelocity;
    private Float windDirection;
    private Long actualTime;
    private Long timeZone;
    private Long sunriseTime;
    private Long sunsetTime;

    public Date getActualTimeAsDate() {
        return new Date(actualTime * 1000);
    }

    public String toStringPointDate() {
        return new StringBuilder().append(point.getName()).append(" (").append(getActualTimeAsDate()).append(")").toString();
    }

    public String toStringTextArea() {
        return new StringBuilder().append(point.toStringNameWithCoords())
                .append("Time: ").append(getActualTimeAsDate())
                .append("\nTemperature: ").append(String.format(java.util.Locale.US, "%.2f", kelvinToCelciusTemperature(actualTemperature)))
                .append("°C\nFeel temperature: ").append(String.format(java.util.Locale.US, "%.2f", kelvinToCelciusTemperature(feelTemperature)))
                .append("°C\nPressure: ").append(String.format(java.util.Locale.US, "%.1f", pressure))
                .append("hPa\nHumidity: ").append(String.format(java.util.Locale.US, "%.1f", humidity))
                .append("%\nWind speed: ").append(String.format(java.util.Locale.US, "%.1f", windVelocity)).append("m/s").toString();
    }

    public String toStringForecastData() {
        return new StringBuilder().append("Time: ").append(getActualTimeAsDate())
                .append(" (temp.: ").append(String.format(java.util.Locale.US, "%.2f", kelvinToCelciusTemperature(actualTemperature))).append("°C)").toString();
    }

    private float kelvinToCelciusTemperature(float kelvinTemp) {
        return kelvinTemp - kelvinCelciusDiff;
    }

    @ToString
    public static final class NullWeatherData extends WeatherData {

        public NullWeatherData() {
            super(new Point.NullPoint(), 0.f, 0.f, 0.f, 0.f, 0.f, 0.f, 0L, 0L, 0L, 0L);
        }
    }

}
