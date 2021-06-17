package pl.zzpwj.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WeatherDataTest {

    @Test
    public void weatherDataBuilderSuccess() {
        WeatherData weatherData = WeatherData.builder()
                                             .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                                             .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                                             .windVelocity(4.f).windDirection(270.f).actualTime(1623505691L).timeZone(7200L)
                                             .sunriseTime(1623464617L).sunsetTime(1623524433L).build();

        Assert.assertEquals(weatherData.getPoint(), Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build());

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
        Assert.assertEquals(weatherData.getActualTime(), Long.valueOf(1623505691L));
        Assert.assertEquals(weatherData.getTimeZone(), Long.valueOf(7200L));
        Assert.assertEquals(weatherData.getSunriseTime(), Long.valueOf(1623464617L));
        Assert.assertEquals(weatherData.getSunsetTime(), Long.valueOf(1623524433L));
    }

    @Test
    public void weatherDataBuilderSuccess1() {
        Point point = Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build();

        Float actualTemperature = 297.f;
        Float feelTemperature = 298.f;
        Float pressure = 1000.f;
        Float humidity = 94.f;
        Float windVelocity = 4.f;
        Float windDirection = 270.f;
        Long actualTime = 1623505691L;
        Long timeZone = 7200L;
        Long sunriseTime = 1623464617L;
        Long sunsetTime = 1623524433L;

        WeatherData weatherData = WeatherData.builder().point(point).actualTemperature(actualTemperature)
                .feelTemperature(feelTemperature).pressure(pressure).humidity(humidity)
                .windVelocity(windVelocity).windDirection(windDirection).actualTime(actualTime).timeZone(timeZone)
                .sunriseTime(sunriseTime).sunsetTime(sunsetTime).build();

        Assert.assertEquals(weatherData.getPoint(), point);

        Assert.assertEquals(weatherData.getPoint().getId(), point.getId());
        Assert.assertEquals(weatherData.getPoint().getName(), point.getName());
        Assert.assertEquals(point.getLongitude(), weatherData.getPoint().getLongitude(), 0.0);
        Assert.assertEquals(point.getLatitude(), weatherData.getPoint().getLatitude(), 0.0);

        Assert.assertEquals(weatherData.getActualTemperature(), actualTemperature);
        Assert.assertEquals(weatherData.getFeelTemperature(), feelTemperature);
        Assert.assertEquals(weatherData.getPressure(), pressure);
        Assert.assertEquals(weatherData.getHumidity(), humidity);
        Assert.assertEquals(weatherData.getWindVelocity(), windVelocity);
        Assert.assertEquals(weatherData.getWindDirection(), windDirection);
        Assert.assertEquals(weatherData.getActualTime(), actualTime);
        Assert.assertEquals(weatherData.getTimeZone(), timeZone);
        Assert.assertEquals(weatherData.getSunriseTime(), sunriseTime);
        Assert.assertEquals(weatherData.getSunsetTime(), sunsetTime);
    }

    @Test
    public void weatherDataDateCompatibility() {
        Long actualTime = 1623505691L;
        Date actualDate = new Date(actualTime * WeatherDataListInterface.millisecondsInSecond);
        Assert.assertEquals(actualDate, new Date(2021 - 1900, Calendar.JUNE, 12, 15, 48, 11));

        WeatherData weatherData = WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505691L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build();

        actualTime = weatherData.getActualTime();
        actualDate = weatherData.getActualTimeAsDate();
        Assert.assertEquals(Long.valueOf(actualDate.getTime() / WeatherDataListInterface.millisecondsInSecond), actualTime);
        Assert.assertEquals(actualDate, new Date(actualTime * WeatherDataListInterface.millisecondsInSecond));
    }

    @Test
    public void weatherDataEqualsAndHashCode() {
        Point point = Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build();

        Float actualTemperature = 297.f;
        Float feelTemperature = 298.f;
        Float pressure = 1000.f;
        Float humidity = 94.f;
        Float windVelocity = 4.f;
        Float windDirection = 270.f;
        Long actualTime = 1623505691L;
        Long timeZone = 7200L;
        Long sunriseTime = 1623464617L;
        Long sunsetTime = 1623524433L;

        WeatherData weatherData = WeatherData.builder().point(point).actualTemperature(actualTemperature)
                .feelTemperature(feelTemperature).pressure(pressure).humidity(humidity)
                .windVelocity(windVelocity).windDirection(windDirection).actualTime(actualTime).timeZone(timeZone)
                .sunriseTime(sunriseTime).sunsetTime(sunsetTime).build();

        Assert.assertEquals(weatherData.getPoint(), point);
        Assert.assertEquals(weatherData.getPoint().getId(), point.getId());
        Assert.assertEquals(weatherData.getPoint().getName(), point.getName());
        Assert.assertEquals(point.getLongitude(), weatherData.getPoint().getLongitude(), 0.0);
        Assert.assertEquals(point.getLatitude(), weatherData.getPoint().getLatitude(), 0.0);

        Assert.assertTrue(point.equals(weatherData.getPoint()));
        Assert.assertEquals(point.hashCode(), weatherData.getPoint().hashCode());

        Assert.assertEquals(weatherData.getActualTemperature(), actualTemperature);
        Assert.assertEquals(weatherData.getFeelTemperature(), feelTemperature);
        Assert.assertEquals(weatherData.getPressure(), pressure);
        Assert.assertEquals(weatherData.getHumidity(), humidity);
        Assert.assertEquals(weatherData.getWindVelocity(), windVelocity);
        Assert.assertEquals(weatherData.getWindDirection(), windDirection);
        Assert.assertEquals(weatherData.getActualTime(), actualTime);
        Assert.assertEquals(weatherData.getTimeZone(), timeZone);
        Assert.assertEquals(weatherData.getSunriseTime(), sunriseTime);
        Assert.assertEquals(weatherData.getSunsetTime(), sunsetTime);

        Assert.assertTrue(weatherData.equals(WeatherData.builder().point(point).actualTemperature(actualTemperature)
                .feelTemperature(feelTemperature).pressure(pressure).humidity(humidity)
                .windVelocity(windVelocity).windDirection(windDirection).actualTime(actualTime).timeZone(timeZone)
                .sunriseTime(sunriseTime).sunsetTime(sunsetTime).build()));
        Assert.assertEquals(weatherData.hashCode(), WeatherData.builder().point(point).actualTemperature(actualTemperature)
                .feelTemperature(feelTemperature).pressure(pressure).humidity(humidity)
                .windVelocity(windVelocity).windDirection(windDirection).actualTime(actualTime).timeZone(timeZone)
                .sunriseTime(sunriseTime).sunsetTime(sunsetTime).build().hashCode());
        Assert.assertTrue(weatherData.equals(WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505691L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build()));
        Assert.assertEquals(weatherData.hashCode(), WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505691L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build().hashCode());
    }

    @Test
    public void weatherDataStringTesting() {
        WeatherData weatherData = WeatherData.builder()
                .point(Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windVelocity(4.f).windDirection(270.f).actualTime(1623505691L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build();

        Assert.assertEquals(weatherData.getPoint(), Point.builder().id(0).name("Test").longitude(1.f).latitude(1.f).build());
        Assert.assertEquals(weatherData.toStringPointDate(), "Test (Sat Jun 12 15:48:11 CEST 2021)");

        Assert.assertEquals(weatherData.toStringTextArea(), new StringBuilder().append("Name: Test\nLongitude: 1.0 E\nLatitude: 1.0 N\n")
        .append("Time: Sat Jun 12 15:48:11 CEST 2021\nTemperature: 23.85°C\nFeel temperature: 24.85°C\nPressure: 1000.0hPa\n")
        .append("Humidity: 94.0%\nWind speed: 4.0m/s").toString());

        Assert.assertEquals(weatherData.toStringForecastData(), new StringBuilder().append("Time: Sat Jun 12 15:48:11 CEST 2021")
                .append(" (temp.: 23.85°C)").toString());

        ArrayList<WeatherData> weatherData1 = new ArrayList<>();
        Assert.assertEquals(0, weatherData1.size());

        Point point = Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build();
        Float actualTemperature = 297.f;
        Float feelTemperature = 298.f;
        Float pressure = 1000.f;
        Float humidity = 94.f;
        Float windVelocity = 4.f;
        Float windDirection = 270.f;
        Long actualTime = 1623505691L;
        Long timeZone = 7200L;
        Long sunriseTime = 1623464617L;
        Long sunsetTime = 1623524433L;

        for (long i = 0; i < 10; i++)
        {
            weatherData1.add(WeatherData.builder().point(point).actualTemperature(actualTemperature - 0.5f * i)
                    .feelTemperature(feelTemperature).pressure(pressure).humidity(humidity)
                    .windVelocity(windVelocity).windDirection(windDirection).actualTime(actualTime + 3600L * i).timeZone(timeZone)
                    .sunriseTime(sunriseTime).sunsetTime(sunsetTime).build());
        }

        Assert.assertEquals(10, weatherData1.size());
        Assert.assertEquals(weatherData1.get(0).toStringForecastData(), new StringBuilder().append("Time: Sat Jun 12 15:48:11 CEST 2021")
                .append(" (temp.: 23.85°C)").toString());
        Assert.assertEquals(weatherData1.get(1).toStringForecastData(), new StringBuilder().append("Time: Sat Jun 12 16:48:11 CEST 2021")
                .append(" (temp.: 23.35°C)").toString());
        Assert.assertEquals(weatherData1.get(2).toStringForecastData(), new StringBuilder().append("Time: Sat Jun 12 17:48:11 CEST 2021")
                .append(" (temp.: 22.85°C)").toString());
        Assert.assertEquals(weatherData1.get(3).toStringForecastData(), new StringBuilder().append("Time: Sat Jun 12 18:48:11 CEST 2021")
                .append(" (temp.: 22.35°C)").toString());
        Assert.assertEquals(weatherData1.get(4).toStringForecastData(), new StringBuilder().append("Time: Sat Jun 12 19:48:11 CEST 2021")
                .append(" (temp.: 21.85°C)").toString());
        Assert.assertEquals(weatherData1.get(5).toStringForecastData(), new StringBuilder().append("Time: Sat Jun 12 20:48:11 CEST 2021")
                .append(" (temp.: 21.35°C)").toString());
        Assert.assertEquals(weatherData1.get(6).toStringForecastData(), new StringBuilder().append("Time: Sat Jun 12 21:48:11 CEST 2021")
                .append(" (temp.: 20.85°C)").toString());
        Assert.assertEquals(weatherData1.get(7).toStringForecastData(), new StringBuilder().append("Time: Sat Jun 12 22:48:11 CEST 2021")
                .append(" (temp.: 20.35°C)").toString());
        Assert.assertEquals(weatherData1.get(8).toStringForecastData(), new StringBuilder().append("Time: Sat Jun 12 23:48:11 CEST 2021")
                .append(" (temp.: 19.85°C)").toString());
        Assert.assertEquals(weatherData1.get(9).toStringForecastData(), new StringBuilder().append("Time: Sun Jun 13 00:48:11 CEST 2021")
                .append(" (temp.: 19.35°C)").toString());

    }

}
