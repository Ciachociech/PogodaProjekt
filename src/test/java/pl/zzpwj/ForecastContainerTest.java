package pl.zzpwj;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ForecastContainerTest {

    @Test
    public void forecastContainerGenerating() {
        ForecastContainer forecastContainer = new ForecastContainer();
        Assert.assertEquals(0, forecastContainer.getForecast().size());
    }

    @Test
    public void forecastContainerAdding() {
        ForecastContainer forecastContainer = new ForecastContainer();
        Assert.assertEquals(0, forecastContainer.getForecast().size());

        Assert.assertTrue(forecastContainer.addHistoryData(WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505691L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build()));
        Assert.assertEquals(1, forecastContainer.getForecast().size());

        WeatherData weatherData = WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505742L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build();
        Assert.assertTrue(forecastContainer.addHistoryData(weatherData));
        Assert.assertEquals(2, forecastContainer.getForecast().size());

        Point point = Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build();
        Float actualTemperature = 297.f;
        Float feelTemperature = 298.f;
        Float pressure = 1000.f;
        Float humidity = 94.f;
        Float windVelocity = 4.f;
        Float windDirection = 270.f;
        Long actualTime = 1623505818L;
        Long timeZone = 7200L;
        Long sunriseTime = 1623464617L;
        Long sunsetTime = 1623524433L;
        WeatherData weatherData1 = WeatherData.builder().point(point).actualTemperature(actualTemperature)
                .feelTemperature(feelTemperature).pressure(pressure).humidity(humidity)
                .windVelocity(windVelocity).windDirection(windDirection).actualTime(actualTime).timeZone(timeZone)
                .sunriseTime(sunriseTime).sunsetTime(sunsetTime).build();
        Assert.assertTrue(forecastContainer.addHistoryData(weatherData1));
        Assert.assertEquals(3, forecastContainer.getForecast().size());

        Assert.assertFalse(forecastContainer.addHistoryData(WeatherData.builder()
                .point(Point.builder().id(0).name("Test3").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505971L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build()));
        Assert.assertEquals(3, forecastContainer.getForecast().size());
    }

}
