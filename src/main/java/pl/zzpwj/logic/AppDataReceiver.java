package pl.zzpwj.logic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.zzpwj.data.ForecastContainer;
import pl.zzpwj.data.HistoryContainer;
import pl.zzpwj.data.PointContainer;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AppDataReceiver {

    ForecastContainer forecastContainer;
    HistoryContainer historyContainer;
    PointContainer pointContainer;

    public void init() throws IOException {
        pointContainer = new PointContainer();
        pointContainer.addPoint(new PointLoader().readPointsFromJSON());
    }

    public void loadHistoryContainer() throws SQLException {
        historyContainer = new SQLLoader().read(SQLPropertiesInterface.dbFilepath);
    };

    public void loadPointContainer() {

    }

    public String[] fillHistoryComboBox() {
        List<String> stringArrayList = historyContainer.getHistoryDataForComboBox();
        String strings[] = new String[stringArrayList.size()];
        for(int i = 0; i < stringArrayList.size(); i++) {
            strings[i] = stringArrayList.get(i);
        }
        return strings;
    }

    public String getHistoryInformationText() {
        return new StringBuilder().append("Choose a city from the list (found ").append(historyContainer.getHistory().size())
                .append(" records).").toString();
    }

    public String getHistoryDataTextArea(int index) {
        return historyContainer.getHistory().get(index).toStringTextArea();
    }
}
