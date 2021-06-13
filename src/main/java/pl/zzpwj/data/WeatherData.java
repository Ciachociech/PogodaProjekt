package pl.zzpwj.data;

import lombok.*;

import java.util.Date;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
@AllArgsConstructor (access = AccessLevel.PUBLIC)
@Builder (access = AccessLevel.PUBLIC)
@Getter
@ToString
@EqualsAndHashCode
public class WeatherData {
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

}
