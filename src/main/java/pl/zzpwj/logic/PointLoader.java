package pl.zzpwj.logic;

import org.json.JSONObject;
import pl.zzpwj.data.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PointLoader implements PointLoaderInterface {

    public ArrayList<Point> readPointsFromJSON() throws IOException {
        ArrayList<Point> points = new ArrayList<>();
        JSONObject jsonObject = queryJSON();
        return points;
    }

    private JSONObject queryJSON() throws IOException {
        BufferedReader bReader = new BufferedReader(new FileReader(PointLoaderInterface.jsonFilePath));
        String jsonString = bReader.toString();
        return new JSONObject(jsonString);
    }

    private ArrayList<Point> parseCitiesByCountry(JSONObject json) {
        ArrayList<Point> retPoints = new ArrayList<>();

        return retPoints;
    }
}
