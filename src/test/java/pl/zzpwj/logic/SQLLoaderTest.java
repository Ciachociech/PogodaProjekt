package pl.zzpwj.logic;

import org.junit.Assert;
import org.junit.Test;
import pl.zzpwj.data.HistoryContainer;
import pl.zzpwj.data.Point;
import pl.zzpwj.data.WeatherData;

import java.sql.SQLException;

public class SQLLoaderTest {

    @Test
    public void SQLLoaderLoading() throws SQLException {
        HistoryContainer historyContainer = new HistoryContainer();
        SQLLoader sqlLoader = new SQLLoader();
        try {
            historyContainer = sqlLoader.read(SQLPropertiesInterface.dbTestFilepath);
        } catch (SQLException ex) {
            Assert.fail();
        }
        Assert.assertTrue(true);

        Assert.assertEquals(3, historyContainer.getHistory().size());
        Assert.assertEquals(historyContainer.getHistory().get(0).getPoint().getId(), Integer.valueOf(0));
        Assert.assertEquals(historyContainer.getHistory().get(0).getPoint().getName(), "Test");
        Assert.assertEquals(1.f, historyContainer.getHistory().get(0).getPoint().getLongitude(), 0.0);
        Assert.assertEquals(1.f, historyContainer.getHistory().get(0).getPoint().getLatitude(), 0.0);
        Assert.assertEquals(historyContainer.getHistory().get(0).getActualTemperature(), Float.valueOf(297.f));
        Assert.assertEquals(historyContainer.getHistory().get(0).getFeelTemperature(), Float.valueOf(298.f));
        Assert.assertEquals(historyContainer.getHistory().get(0).getPressure(), Float.valueOf(1000.f));
        Assert.assertEquals(historyContainer.getHistory().get(0).getWindVelocity(), Float.valueOf(4.f));
        Assert.assertEquals(historyContainer.getHistory().get(0).getWindDirection(), Float.valueOf(270.f));
        Assert.assertEquals(historyContainer.getHistory().get(0).getHumidity(), Float.valueOf(94.f));
        Assert.assertEquals(historyContainer.getHistory().get(0).getActualTime(), Long.valueOf(1623505691L));
        Assert.assertEquals(historyContainer.getHistory().get(0).getTimeZone(), Long.valueOf(7200L));
        Assert.assertEquals(historyContainer.getHistory().get(0).getSunriseTime(), Long.valueOf(1623464617L));
        Assert.assertEquals(historyContainer.getHistory().get(0).getSunsetTime(), Long.valueOf(1623524433L));

        Assert.assertEquals(historyContainer.getHistory().get(1), WeatherData.builder()
                .point(Point.builder().id(0).name("Test").latitude(1.f).longitude(1.f).build())
                .actualTemperature(297.f).feelTemperature(298.f).pressure(1000.f).humidity(94.f)
                .windDirection(270.f).windVelocity(4.f).actualTime(1623505742L).timeZone(7200L)
                .sunriseTime(1623464617L).sunsetTime(1623524433L).build());
    }
}
