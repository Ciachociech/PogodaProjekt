package pl.zzpwj.data;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Point point = new Point.PointBuilder().id(0).latitude(1.f).longitude(1.f).name("test").build();
        System.out.println(point.toString());
    }

}

//architektura proserwisowa
//pomyśleć ze springiem
