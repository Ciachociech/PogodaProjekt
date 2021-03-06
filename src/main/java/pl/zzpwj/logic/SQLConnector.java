package pl.zzpwj.logic;

import lombok.AccessLevel;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter(AccessLevel.PROTECTED)
class SQLConnector implements SQLPropertiesInterface {

    private Connection connection;

    protected void connect(String database) throws SQLException {
        this.connection = DriverManager.getConnection(database);
    }

    protected void close() throws SQLException {
        this.connection.close();
    }
}
