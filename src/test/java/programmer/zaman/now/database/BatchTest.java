package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class BatchTest {

    @Test
    void testStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "INSERT INTO comments(email, comment) values ('eko@test.com', 'hi')";

        for (int i = 0; i < 1000; i++) {
            statement.addBatch("INSERT INTO comments(email, comment) values ('testing_" + i + "@test.com', 'hi " + i + "x')");
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO comments(email, comment) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        for (int i = 0; i < 1000; i++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "email_" + i + "@test.com");
            preparedStatement.setString(2, "Hi " + i + "x");

            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }

}
