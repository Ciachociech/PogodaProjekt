package pl.zzpwj.logic;

import lombok.NoArgsConstructor;
import pl.zzpwj.data.HistoryContainer;
import pl.zzpwj.data.Point;
import pl.zzpwj.data.WeatherData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
public class SQLLoader extends SQLConnector {

    public HistoryContainer read(String database) throws SQLException {
        HistoryContainer historyContainer = new HistoryContainer();

        super.connect(database);

        String select = new StringBuilder().append("SELECT * FROM ").append(historyContainer.getClass().getName()).toString();
        PreparedStatement preparedStatement = getConnection().prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            historyContainer.addWeatherData(WeatherData.builder()
                    .point(Point.builder()
                            .id(resultSet.getInt("pointID"))
                            .name(resultSet.getString("pointName"))
                            .latitude(resultSet.getFloat("pointLat"))
                            .longitude(resultSet.getFloat("pointLong")).build())
                    .actualTemperature(resultSet.getFloat("actualTemp"))
                    .feelTemperature(resultSet.getFloat("feelTemp"))
                    .pressure(resultSet.getFloat("pressure"))
                    .humidity(resultSet.getFloat("humidity"))
                    .windVelocity(resultSet.getFloat("windVel"))
                    .windDirection(resultSet.getFloat("windDir"))
                    .actualTime(resultSet.getLong("actualTime"))
                    .timeZone(resultSet.getLong("timeZone"))
                    .sunriseTime(resultSet.getLong("sunriseTime"))
                    .sunsetTime(resultSet.getLong("sunsetTime")).build());
        }

        close();
        return historyContainer;
    }
}

//podlać skalniak
//podlać kwiaty w domu