package pl.zzpwj;

import lombok.*;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
@AllArgsConstructor (access = AccessLevel.PUBLIC)
@Builder
@Getter
@ToString
public class WeatherData
{
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
}
