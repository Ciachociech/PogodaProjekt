package pl.zzpwj.logic;

import org.junit.Assert;
import org.junit.Test;
import pl.zzpwj.data.Point;
import pl.zzpwj.data.WeatherData;

import java.io.IOException;
import java.util.ArrayList;

public class OWMRequesterTest {

    OWMRequester owmRequester = new OWMRequester();

    @Test
    public void OWMCurrentConnection() throws IOException {
        Point point = Point.builder().id(3093133).name("Łódź").longitude(19.466669f).latitude(51.75f).build();
        owmRequester.setPoint(point);

        Assert.assertEquals(point.getId(), Integer.valueOf(3093133));
        Assert.assertEquals(point.getName(), "Łódź");
        Assert.assertEquals(19.466669f, point.getLongitude(), 0.01);
        Assert.assertEquals(51.75f, point.getLatitude(), 0.01);

        WeatherData getData = owmRequester.requestAPIForCurrent();
        Assert.assertNotNull(getData);
        Assert.assertEquals(getData.getPoint(), point);
        Assert.assertEquals(getData.getTimeZone(), Long.valueOf(7200L));
    }

    @Test
    public void OWMForecastConnection() throws IOException {
        Point point = Point.builder().id(7531886).name("Zgierz").longitude(19.393999f).latitude(51.855499f).build();
        owmRequester.setPoint(point);

        Assert.assertEquals(point.getId(), Integer.valueOf(7531886));
        Assert.assertEquals(point.getName(), "Zgierz");
        Assert.assertEquals(19.393999f, point.getLongitude(), 0.01);
        Assert.assertEquals(51.855499f, point.getLatitude(), 0.01);

        ArrayList<WeatherData> dataArrayList = owmRequester.requestAPIForForecast();
        Assert.assertNotNull(dataArrayList);
        Assert.assertEquals(96, dataArrayList.size());
        Assert.assertEquals(dataArrayList.get(0).getPoint(), point);
        Assert.assertEquals(dataArrayList.get(0).getTimeZone(), Long.valueOf(7200L));
    }
}
