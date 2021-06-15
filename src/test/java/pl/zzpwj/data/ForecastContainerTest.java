package pl.zzpwj.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static pl.zzpwj.data.WeatherDataListInterface.millisecondsInSecond;

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

    @Test
    public void forecastContainerSearching() {
        ForecastContainer forecastContainer = new ForecastContainer();
        Point point = Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build();

        Assert.assertTrue(forecastContainer.addHistoryData(WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505691L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build()));
        WeatherData weatherData = WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505742L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build();
        Assert.assertTrue(forecastContainer.addHistoryData(weatherData));
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
        Assert.assertTrue(forecastContainer.addHistoryData(WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505961L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build()));
        Assert.assertTrue(forecastContainer.addHistoryData(WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623506144L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build()));
        Assert.assertEquals(5, forecastContainer.getForecast().size());

        ArrayList<WeatherData> weatherDataArrayList = forecastContainer.getForecast();
        Assert.assertEquals(5, weatherDataArrayList.size());

        Assert.assertEquals(weatherDataArrayList.get(1), weatherData);
        Assert.assertEquals(weatherDataArrayList.get(0), WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505691L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build());
        Assert.assertEquals(weatherDataArrayList.get(2).getPoint(), point);
        Assert.assertEquals(weatherDataArrayList.get(2).getActualTemperature(), actualTemperature);
        Assert.assertEquals(weatherDataArrayList.get(2).getFeelTemperature(), feelTemperature);
        Assert.assertEquals(weatherDataArrayList.get(2).getPressure(), pressure);
        Assert.assertEquals(weatherDataArrayList.get(2).getHumidity(), humidity);
        Assert.assertEquals(weatherDataArrayList.get(2).getWindVelocity(), windVelocity);
        Assert.assertEquals(weatherDataArrayList.get(2).getWindDirection(), windDirection);
        Assert.assertEquals(weatherDataArrayList.get(2).getActualTime(), actualTime);
        Assert.assertEquals(weatherDataArrayList.get(2).getTimeZone(), timeZone);
        Assert.assertEquals(weatherDataArrayList.get(2).getSunriseTime(), sunriseTime);
        Assert.assertEquals(weatherDataArrayList.get(2).getSunsetTime(), sunsetTime);

        Assert.assertEquals(weatherDataArrayList.get(3).getPoint(), Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build());
        Assert.assertEquals(weatherDataArrayList.get(3).getPoint().getId(), Integer.valueOf(0));
        Assert.assertEquals(weatherDataArrayList.get(3).getPoint().getName(), "Test");
        Assert.assertEquals(1.f, weatherDataArrayList.get(3).getPoint().getLongitude(), 0.f);
        Assert.assertEquals(1.f, weatherDataArrayList.get(3).getPoint().getLatitude(), 0.f);
        Assert.assertEquals(weatherDataArrayList.get(3).getActualTemperature(), Float.valueOf(297.f));
        Assert.assertEquals(weatherDataArrayList.get(3).getFeelTemperature(), Float.valueOf(298.f));
        Assert.assertEquals(weatherDataArrayList.get(3).getPressure(), Float.valueOf(1000.f));
        Assert.assertEquals(weatherDataArrayList.get(3).getHumidity(), Float.valueOf(94.f));
        Assert.assertEquals(weatherDataArrayList.get(3).getWindVelocity(), Float.valueOf(4.f));
        Assert.assertEquals(weatherDataArrayList.get(3).getWindDirection(), Float.valueOf(270.f));
        Assert.assertEquals(weatherDataArrayList.get(3).getActualTime(), Long.valueOf(1623505961L));
        Assert.assertEquals(weatherDataArrayList.get(3).getTimeZone(), Long.valueOf(7200L));
        Assert.assertEquals(weatherDataArrayList.get(3).getSunriseTime(), Long.valueOf(1623464617L));
        Assert.assertEquals(weatherDataArrayList.get(3).getSunsetTime(), Long.valueOf(1623524433L));

        WeatherData weatherData2 = weatherDataArrayList.get(4);
        Assert.assertNotSame(weatherData2.getClass(), WeatherData.NullWeatherData.class);

        Assert.assertEquals(weatherData, forecastContainer.getWeatherDataByDate(new Date(1623505742L * millisecondsInSecond)));
        Assert.assertEquals(weatherData1, forecastContainer.getWeatherDataByDate(weatherData1.getActualTimeAsDate()));
        Assert.assertSame(forecastContainer.getWeatherDataByDate(new Date(1623509999L * millisecondsInSecond)).getClass(), WeatherData.NullWeatherData.class);
    }

}
