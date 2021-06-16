package pl.zzpwj.logic;

import lombok.NoArgsConstructor;
import pl.zzpwj.data.HistoryContainer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
public class SQLWriter extends SQLConnector {

    public void write(HistoryContainer history, String database) throws SQLException {
        super.connect(database);

        String create = new StringBuilder().append("CREATE TABLE ").append(history.getClass().getName())
                .append(" (pointID integer, pointName varchar(32), pointLong real, pointLat real,")
                .append(" actualTemp real, realTemp real, pressure real, humidity real, windVel real")
                .append(" windDir real, actualTime integer, timeZone integer, sunriseTime integer, sunsetTime integer);").toString();
        String insert = new StringBuilder().append("INSERT INTO ").append(history.getClass().getName())
                .append(" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);").toString();

        Statement statement = getConnection().createStatement();
        statement.execute(create);

        for(int i=0; i < history.getHistory().size(); i++) {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, history.getHistory().get(i).getPoint().getId());
            preparedStatement.setString(2, history.getHistory().get(i).getPoint().getName());
            preparedStatement.setFloat(3, history.getHistory().get(i).getPoint().getLongitude());
            preparedStatement.setFloat(4, history.getHistory().get(i).getPoint().getLatitude());

            preparedStatement.setFloat(5, history.getHistory().get(i).getActualTemperature());
            preparedStatement.setFloat(6, history.getHistory().get(i).getFeelTemperature());
            preparedStatement.setFloat(7, history.getHistory().get(i).getPressure());
            preparedStatement.setFloat(8, history.getHistory().get(i).getHumidity());
            preparedStatement.setFloat(9, history.getHistory().get(i).getWindVelocity());
            preparedStatement.setFloat(10, history.getHistory().get(i).getWindDirection());
            preparedStatement.setLong(11, history.getHistory().get(i).getActualTime());
            preparedStatement.setLong(12, history.getHistory().get(i).getTimeZone());
            preparedStatement.setLong(13, history.getHistory().get(i).getSunriseTime());
            preparedStatement.setLong(14, history.getHistory().get(i).getSunsetTime());
            preparedStatement.execute();

            close();
        }


    }
}
