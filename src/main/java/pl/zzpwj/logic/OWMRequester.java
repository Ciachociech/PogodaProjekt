package pl.zzpwj.logic;

import lombok.Getter;
import lombok.Setter;
import org.json.*;
import pl.zzpwj.data.Point;
import pl.zzpwj.data.WeatherData;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Getter
@Setter
public class OWMRequester implements OWMPropertiesInterface {
    private Point point;

    public WeatherData requestAPIForCurrent() throws IOException {
        URL url = getCurrentURL();
        JSONObject receivedJSON = queryJSON(url);
        return parseFromJSON(receivedJSON);
    }

    public ArrayList<WeatherData> requestAPIForForecast() throws IOException {
        URL url = getForecastURL();
        JSONObject receivedJSON = queryJSON(url);
        return parseArrayFromJSON(receivedJSON);
    }

    private URL getCurrentURL() throws MalformedURLException {
        StringBuilder apiAddress = new StringBuilder();
        apiAddress.append(currentUrl).append("id=").append(point.getId()).append(apiKey);
        return new URL(apiAddress.toString());
    }

    private URL getForecastURL() throws MalformedURLException {
        StringBuilder apiAddress = new StringBuilder();
        apiAddress.append(forecastUrl).append("id=").append(point.getId()).append(apiKey);
        return new URL(apiAddress.toString());
    }

    private JSONObject queryJSON(URL url) throws IOException {
        try (InputStream istream = new URL(url.toString()).openStream()) {
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(istream, StandardCharsets.UTF_8));
            String jsonString = stringReader(buffReader);
            return new JSONObject(jsonString);
        }
    }

    private String stringReader(Reader reader) throws IOException {
        StringBuilder sReader = new StringBuilder();
        int cp;
        while((cp = reader.read()) != -1) {
            sReader.append((char) cp);
        }
        return sReader.toString();
    }

    private WeatherData parseFromJSON(JSONObject json) {
        return WeatherData.builder().point(point)
                .actualTemperature(json.getJSONObject("main").getFloat("temp"))
                .feelTemperature(json.getJSONObject("main").getFloat("feels_like"))
                .pressure(json.getJSONObject("main").getFloat("pressure"))
                .humidity(json.getJSONObject("main").getFloat("humidity"))
                .windVelocity(json.getJSONObject("wind").getFloat("speed"))
                .windDirection(json.getJSONObject("wind").getFloat("deg"))
                .actualTime(json.getLong("dt")).timeZone(json.getLong("timezone"))
                .sunriseTime(json.getJSONObject("sys").getLong("sunrise"))
                .sunsetTime(json.getJSONObject("sys").getLong("sunset")).build();
    }

    private ArrayList<WeatherData> parseArrayFromJSON(JSONObject json) {
        ArrayList<WeatherData> retData = new ArrayList<>();
        for (int i = 0; i < json.getJSONArray("list").length(); i++) {
            retData.add(WeatherData.builder().point(point)
                    .actualTemperature(json.getJSONArray("list").getJSONObject(i).getJSONObject("main").getFloat("temp"))
                    .feelTemperature(json.getJSONArray("list").getJSONObject(i).getJSONObject("main").getFloat("feels_like"))
                    .pressure(json.getJSONArray("list").getJSONObject(i).getJSONObject("main").getFloat("pressure"))
                    .humidity(json.getJSONArray("list").getJSONObject(i).getJSONObject("main").getFloat("humidity"))
                    .windVelocity(json.getJSONArray("list").getJSONObject(i).getJSONObject("wind").getFloat("speed"))
                    .windDirection(json.getJSONArray("list").getJSONObject(i).getJSONObject("wind").getFloat("deg"))
                    .actualTime(json.getJSONArray("list").getJSONObject(i).getLong("dt"))
                    .timeZone(json.getJSONObject("city").getLong("timezone"))
                    .sunriseTime(json.getJSONObject("city").getLong("sunrise"))
                    .sunsetTime(json.getJSONObject("city").getLong("sunset")).build());
        }
        return retData;
    }
}
