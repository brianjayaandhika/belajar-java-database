package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class PreparedStatementTest {


    @Test
    void testPrepareStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();


        String username = "admin";
        String password = "1234";

        String sql = "SELECT * FROM admin where username= ? and password = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet res = statement.executeQuery();

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
