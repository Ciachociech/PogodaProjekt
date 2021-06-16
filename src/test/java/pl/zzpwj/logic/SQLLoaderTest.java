package pl.zzpwj.logic;

import org.junit.Assert;
import org.junit.Test;
import pl.zzpwj.data.HistoryContainer;

import java.sql.SQLException;

public class SQLLoaderTest {

    @Test
    public void SQLLoaderLoading() throws SQLException {
        HistoryContainer historyContainer;
        SQLLoader sqlLoader = new SQLLoader();
        historyContainer = sqlLoader.read(SQLPropertiesInterface.dbTestFilepath);
        /*
        try {
            historyContainer = sqlLoader.read(SQLPropertiesInterface.dbTestFilepath);
        }
        catch (SQLException ex) {
            Assert.fail();
        }

         */
        Assert.assertTrue(true);
    }
}
