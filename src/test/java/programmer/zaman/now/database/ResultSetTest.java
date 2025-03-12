package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetTest {

    @Test
    void testExecuteQuery() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM customers";

        ResultSet res = statement.executeQuery(sql);

        while (res.next()) {
            String id = res.getString("id");
            String name = res.getString("name");
            String email = res.getString("email");
            System.out.println("id: " + id + ", name: " + name + ", email: " + email);
            System.out.println("==========================================");

        }

        res.close();
        statement.close();
        connection.close();
    }
}
