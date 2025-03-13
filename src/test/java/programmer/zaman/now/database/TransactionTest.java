package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionTest {

    @Test
    void testCommit() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) values (?, ?)";
        for (int i = 0; i < 100; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "email@test.com");
            preparedStatement.setString(2, "Hi " + i + "x");

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }


        connection.commit();
        connection.close();
    }

    @Test
    void testRollback() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) values (?, ?)";
        for (int i = 0; i < 100; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "email@test.com");
            preparedStatement.setString(2, "Hi " + i + "x");

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }


        connection.rollback();
        connection.close();
    }

}
