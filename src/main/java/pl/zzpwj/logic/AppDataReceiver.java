package pl.zzpwj.logic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.zzpwj.data.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AppDataReceiver {

    ForecastContainer forecastContainer;
    HistoryContainer historyContainer;
    PointContainer pointContainer;
    ArrayList<Point> foundPoints;
    OWMRequester owmRequester;

    public void init() throws IOException, SQLException {
        pointContainer = new PointContainer();
        pointContainer.addPoint(new PointLoader().readPointsFromJSON());
        historyContainer = new SQLLoader().read(SQLPropertiesInterface.dbFilepath);
        forecastContainer = new ForecastContainer();
        owmRequester = new OWMRequester();
    }

    public void loadHistoryContainer() throws SQLException {
        historyContainer = new SQLLoader().read(SQLPropertiesInterface.dbFilepath);
    };

    public String[] fillHistoryComboBox() {
        List<String> stringArrayList = historyContainer.getHistoryDataForComboBox();
        return getStrings(stringArrayList);
    }

    public String[] fillPointsComboBox(String name) {
        List<String> loadedPoints = pointContainer.getPointDataForComboBox(name);
        foundPoints = pointContainer.getPointByName(name);
        return getStrings(loadedPoints);
    }

    private String[] getStrings(List<String> loadedPoints) {
        if(loadedPoints.size() > 0) {
            String[] strings = new String[loadedPoints.size()];
            for(int i = 0; i < loadedPoints.size(); i++) {
                strings[i] = loadedPoints.get(i);
            }
            return strings;
        } else {
            return new String[0];
        }
    }

    public String getHistoryInformationText() {
        return new StringBuilder().append("Choose a city from the list (found ").append(historyContainer.getHistory().size())
                .append(" records).").toString();
    }

    public String getSearchInformationText() {
        return "Here will be information about weather, choose one of cities.";
    }

    public String getHistoryDataTextArea(int index) {
        return historyContainer.getHistory().get(index).toStringTextArea();
    }

    public String getSearchDataTextArea(int index) throws Exception {
        owmRequester.setPoint(foundPoints.get(index));
        WeatherData weatherData = owmRequester.requestAPIForCurrent();

        if(weatherData.getClass() != WeatherData.NullWeatherData.class) {
            addSearchDataToHistory(weatherData);
            return weatherData.toStringTextArea();
        } else {
            return "";
        }
    }

    public String getForecastDataTextArea(int index) throws IOException {
        owmRequester.setPoint(foundPoints.get(index));
        ArrayList<WeatherData> weatherData = owmRequester.requestAPIForForecast();

        if(weatherData.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder().append(weatherData.get(0).getPoint().toStringNameWithCoords());
            for (int i = 0; i < weatherData.size(); i = i + 3) {
                stringBuilder.append("\n").append(weatherData.get(i).toStringForecastData());
            }
            return stringBuilder.toString();
        } else {
            return "";
        }
    }

    public void addSearchDataToHistory(WeatherData weatherData) throws Exception {
        if(historyContainer.addWeatherData(weatherData)) {
            new SQLWriter().write(historyContainer, SQLPropertiesInterface.dbFilepath);
        }
    }



}
