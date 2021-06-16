package pl.zzpwj.logic;

import org.junit.Assert;
import org.junit.Test;
import pl.zzpwj.data.HistoryContainer;
import pl.zzpwj.data.Point;
import pl.zzpwj.data.WeatherData;

import java.sql.SQLException;

public class SQLWriterTest {

    @Test
    public void SQLWriterWriting() throws Exception {
        HistoryContainer historyContainer = new HistoryContainer();

        Assert.assertEquals(0, historyContainer.getHistory().size());

        Assert.assertTrue(historyContainer.addWeatherData(WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505691L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build()));
        Assert.assertEquals(1, historyContainer.getHistory().size());

        WeatherData weatherData = WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505742L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build();
        Assert.assertTrue(historyContainer.addWeatherData(weatherData));
        Assert.assertEquals(2, historyContainer.getHistory().size());

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
        Assert.assertTrue(historyContainer.addWeatherData(weatherData1));
        Assert.assertEquals(3, historyContainer.getHistory().size());

        SQLWriter sqlWriter = new SQLWriter();
        sqlWriter.write(historyContainer, SQLPropertiesInterface.dbTestFilepath);
    }
}
