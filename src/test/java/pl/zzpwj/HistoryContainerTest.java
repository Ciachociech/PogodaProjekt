package pl.zzpwj;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static pl.zzpwj.WeatherDataListInterface.millisecondsInSecond;

public class HistoryContainerTest {

    @Test
    public void historyContainerGenerating() {
        HistoryContainer historyContainer = new HistoryContainer();
        Assert.assertEquals(0, historyContainer.getHistory().size());
    }

    @Test
    public void historyContainerAdding() {
        HistoryContainer historyContainer = new HistoryContainer();
        Assert.assertEquals(0, historyContainer.getHistory().size());

        Assert.assertTrue(historyContainer.addHistoryData(WeatherData.builder()
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
        Assert.assertTrue(historyContainer.addHistoryData(weatherData));
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
        Assert.assertTrue(historyContainer.addHistoryData(weatherData1));
        Assert.assertEquals(3, historyContainer.getHistory().size());

        Assert.assertFalse(historyContainer.addHistoryData(WeatherData.builder()
                .point(Point.builder().id(0).name("Test3").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505691L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build()));
        Assert.assertEquals(3, historyContainer.getHistory().size());
    }

    @Test
    public void historyContainerSearching() {
        HistoryContainer historyContainer = new HistoryContainer();
        Point point = Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build();
        WeatherData weatherData = WeatherData.builder()
                .point(point).actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505741L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build();
        WeatherData weatherData1 = WeatherData.builder()
                .point(Point.builder().id(4).name("Test2").longitude(1.1f).latitude(1.1f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505842L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build();

        Assert.assertTrue(historyContainer.addHistoryData(weatherData));
        Assert.assertTrue(historyContainer.addHistoryData(weatherData1));
        Assert.assertTrue(historyContainer.addHistoryData(WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623507593L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build()));
        Assert.assertTrue(historyContainer.addHistoryData(WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623508494L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build()));
        Assert.assertTrue(historyContainer.addHistoryData(WeatherData.builder()
                .point(Point.builder().id(7).name("Test4").longitude(1.4f).latitude(1.4f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.4f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623512345L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build()));
        Assert.assertEquals(5, historyContainer.getHistory().size());

        ArrayList<WeatherData> pointHistory = historyContainer.getHistoryOfPoint(point);
        Assert.assertEquals(3, pointHistory.size());
        WeatherData weatherData2 = pointHistory.get(0);
        Assert.assertNotNull(weatherData2);
        Assert.assertEquals(weatherData.getPoint().getId(), Integer.valueOf(0));
        Assert.assertEquals(weatherData.getPoint().getName(), "Test");
        Assert.assertEquals(1.0f, weatherData.getPoint().getLongitude(), 0.0);
        Assert.assertEquals(1.0f, weatherData.getPoint().getLatitude(), 0.0);
        Assert.assertEquals(weatherData.getActualTemperature(), Float.valueOf(297.f));
        Assert.assertEquals(weatherData.getFeelTemperature(), Float.valueOf(298.f));
        Assert.assertEquals(weatherData.getPressure(), Float.valueOf(1000.f));
        Assert.assertEquals(weatherData.getHumidity(), Float.valueOf(94.f));
        Assert.assertEquals(weatherData.getWindVelocity(), Float.valueOf(4.f));
        Assert.assertEquals(weatherData.getWindDirection(), Float.valueOf(270.f));
        Assert.assertEquals(weatherData.getActualTime(), Long.valueOf(1623505741L));
        Assert.assertEquals(weatherData.getTimeZone(), Long.valueOf(7200L));
        Assert.assertEquals(weatherData.getSunriseTime(), Long.valueOf(1623464617L));
        Assert.assertEquals(weatherData.getSunsetTime(), Long.valueOf(1623524433L));

        ArrayList<WeatherData> pointHistory1 = historyContainer.getHistoryOfPoint(Point.builder().id(4).name("Test2")
                .longitude(1.1f).latitude(1.1f).build());
        Assert.assertEquals(1, pointHistory1.size());
        Assert.assertEquals(pointHistory1.get(0).getPoint().getId(), Integer.valueOf(4));
        Assert.assertEquals(pointHistory1.get(0).getPoint().getName(), "Test2");
        Assert.assertEquals(1.1f, pointHistory1.get(0).getPoint().getLongitude(), 0.0);
        Assert.assertEquals(1.1f, pointHistory1.get(0).getPoint().getLatitude(), 0.0);
        Assert.assertEquals(pointHistory1.get(0).getActualTemperature(), Float.valueOf(297.f));
        Assert.assertEquals(pointHistory1.get(0).getFeelTemperature(), Float.valueOf(298.f));
        Assert.assertEquals(pointHistory1.get(0).getPressure(), Float.valueOf(1000.f));
        Assert.assertEquals(pointHistory1.get(0).getWindVelocity(), Float.valueOf(4.f));
        Assert.assertEquals(pointHistory1.get(0).getWindDirection(), Float.valueOf(270.f));
        Assert.assertEquals(pointHistory1.get(0).getHumidity(), Float.valueOf(94.f));
        Assert.assertEquals(pointHistory1.get(0).getActualTime(), Long.valueOf(1623505842L));
        Assert.assertEquals(pointHistory1.get(0).getTimeZone(), Long.valueOf(7200L));
        Assert.assertEquals(pointHistory1.get(0).getSunriseTime(), Long.valueOf(1623464617L));
        Assert.assertEquals(pointHistory1.get(0).getSunsetTime(), Long.valueOf(1623524433L));

        Point point1 = Point.builder().id(9).name("Test3").longitude(3.3f).latitude(3.3f).build();
        ArrayList<WeatherData> pointHistory2 = historyContainer.getHistoryOfPoint(point1);
        Assert.assertEquals(0, pointHistory2.size());

        Assert.assertEquals(1, historyContainer.getHistoryOfPoint(Point.builder().id(7).name("Test4")
                .longitude(1.4f).latitude(1.4f).build()).size());

        Assert.assertEquals(weatherData, historyContainer.getWeatherDataByDate(new Date(1623505741L * millisecondsInSecond)));
        Assert.assertEquals(weatherData1, historyContainer.getWeatherDataByDate(weatherData1.getActualTimeAsDate()));
        Assert.assertNull(historyContainer.getWeatherDataByDate(new Date(1623509999L * millisecondsInSecond)));
    }
}
