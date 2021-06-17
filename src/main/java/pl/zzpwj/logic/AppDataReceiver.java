package pl.zzpwj.logic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.zzpwj.data.ForecastContainer;
import pl.zzpwj.data.HistoryContainer;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AppDataReceiver {

    ForecastContainer forecastContainer;
    HistoryContainer historyContainer;

    public void loadHistoryContainer() throws SQLException {
        historyContainer = new SQLLoader().read(SQLPropertiesInterface.dbFilepath);
    };

    public void fillHistoryComboBox(JComboBox comboBox) {
        List<String> stringArrayList = historyContainer.getHistoryDataForComboBox();
        comboBox = new JComboBox(stringArrayList.toArray());
    }

    public String setHistoryInformationText() {
        return new StringBuilder().append("Choose a city from the list (found ").append(historyContainer.getHistory().size())
                .append(" cities).").toString();
    }
}
