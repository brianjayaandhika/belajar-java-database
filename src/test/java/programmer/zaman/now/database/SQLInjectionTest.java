package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionTest {


    @Test
    void testSqlInjection() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin";
        String password = "12345";

        String sql = "SELECT * FROM admin where username= '" + username
                + "' and password = '" + password
                + "'";

        ResultSet res = statement.executeQuery(sql);

        if (res.next()) {
            System.out.println("user " + username + " has logged on");
        } else {
            System.out.println("user " + username + " not found");
        }

        res.close();
        statement.close();
        connection.close();
    }
}
