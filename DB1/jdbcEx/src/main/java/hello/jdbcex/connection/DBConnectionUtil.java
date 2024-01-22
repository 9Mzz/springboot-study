package hello.jdbcex.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbcex.connection.ConnectionConst.*;

public class DBConnectionUtil {


    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }

}
