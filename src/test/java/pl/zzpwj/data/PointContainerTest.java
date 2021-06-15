package pl.zzpwj.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PointContainerTest {

    @Test
    public void pointContainerGenerating() {
        PointContainer pointContainer = new PointContainer();
        Assert.assertEquals(0, pointContainer.getPoints().size());
    }

    @Test
    public void pointContainerAdding() {
        PointContainer pointContainer = new PointContainer();
        Point point = Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build();
        Point point1 = Point.builder().id(1).name("Test1").longitude(1.1f).latitude(1.1f).build();
        Point point2 = Point.builder().id(2).name("Test2").longitude(1.2f).latitude(1.2f).build();
        Point point3 = Point.builder().id(1).name("Test3").longitude(1.3f).latitude(1.3f).build();

        Assert.assertEquals(0, pointContainer.getPoints().size());
        Assert.assertTrue(pointContainer.addPoint(point));
        Assert.assertEquals(1, pointContainer.getPoints().size());
        Assert.assertTrue(pointContainer.addPoint(point1));
        Assert.assertEquals(2, pointContainer.getPoints().size());
        Assert.assertTrue(pointContainer.addPoint(point2));
        Assert.assertEquals(3, pointContainer.getPoints().size());
        Assert.assertFalse(pointContainer.addPoint(point3));
        Assert.assertEquals(3, pointContainer.getPoints().size());

        Assert.assertTrue(pointContainer.addPoint(Point.builder().id(3).name("Test3").longitude(1.3f).latitude(1.3f).build()));
        Assert.assertEquals(4, pointContainer.getPoints().size());
    }

    @Test
    public void pointContainerSearching() {
        PointContainer pointContainer = new PointContainer();
        Point point = Point.builder().id(0).name("Test").longitude(1.0f).latitude(1.0f).build();
        Point point1 = Point.builder().id(1).name("Test1").longitude(1.1f).latitude(1.1f).build();

        Assert.assertTrue(pointContainer.addPoint(point));
        Assert.assertTrue(pointContainer.addPoint(point1));
        Assert.assertTrue(pointContainer.addPoint(Point.builder().id(2).name("Test2").longitude(1.2f).latitude(1.2f).build()));
        Assert.assertTrue(pointContainer.addPoint(Point.builder().id(3).name("Test3").longitude(1.3f).latitude(1.3f).build()));
        Assert.assertTrue(pointContainer.addPoint(Point.builder().id(4).name("Test2").longitude(1.5f).latitude(1.5f).build()));
        Assert.assertEquals(5, pointContainer.getPoints().size());

        Assert.assertEquals(pointContainer.getPointById(0), point);
        Assert.assertEquals(pointContainer.getPointById(1), point1);
        Assert.assertSame(pointContainer.getPointById(5).getClass(), Point.NullPoint.class);

        ArrayList<Point> pointTest = pointContainer.getPointByName("Test");
        Assert.assertEquals(1, pointTest.size());
        Assert.assertEquals(pointTest.get(0), point);
        ArrayList<Point> pointTest1 = pointContainer.getPointByName("Test1");
        Assert.assertEquals(1, pointTest1.size());
        Assert.assertEquals(pointTest1.get(0), point1);
        ArrayList<Point> pointTestX = pointContainer.getPointByName("TestX");
        Assert.assertEquals(0, pointTestX.size());
        ArrayList<Point> pointTest2 = pointContainer.getPointByName("Test2");
        Assert.assertEquals(2, pointTest2.size());
        Assert.assertEquals(pointTest2.get(0).getName(), "Test2");
        Assert.assertEquals(pointTest2.get(0).getName(), pointTest2.get(1).getName());
    }
}
