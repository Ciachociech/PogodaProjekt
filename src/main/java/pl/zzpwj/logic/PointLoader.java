package pl.zzpwj.logic;

import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.zzpwj.data.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

@NoArgsConstructor
public class PointLoader implements PointLoaderInterface {

    public ArrayList<Point> readPointsFromJSON() throws IOException {
        ArrayList<Point> points = new ArrayList<>();
        JSONArray jsonObject = queryJSON();
        return parseCitiesByCountry(jsonObject);
    }

    private JSONArray queryJSON() throws IOException {
        BufferedReader bReader = new BufferedReader(new FileReader(PointLoaderInterface.jsonFilePath));
        String jsonString = stringReader(bReader);
        return new JSONArray(jsonString);
    }

    private String stringReader(Reader reader) throws IOException {
        StringBuilder sReader = new StringBuilder();
        int cp;
        while((cp = reader.read()) != -1) {
            sReader.append((char) cp);
        }
        return sReader.toString();
    }

    private ArrayList<Point> parseCitiesByCountry(JSONArray jsonArray) {
        ArrayList<Point> retPoints = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            if(jsonArray.getJSONObject(i).getString("country").equals(country)) {
                retPoints.add(Point.builder()
                        .id(jsonArray.getJSONObject(i).getInt("id"))
                        .name(jsonArray.getJSONObject(i).getString("name"))
                        .latitude(jsonArray.getJSONObject(i).getJSONObject("coord").getFloat("lat"))
                        .longitude(jsonArray.getJSONObject(i).getJSONObject("coord").getFloat("lon"))
                        .build());
            }
        }
        return retPoints;
    }
}
