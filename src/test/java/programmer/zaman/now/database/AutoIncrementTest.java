package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {

    @Test
    void testAutoIncrement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO comments(email, comment) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, "email@test.com");
        preparedStatement.setString(2, "Hi");

        int updated = preparedStatement.executeUpdate();

        ResultSet res = preparedStatement.getGeneratedKeys();
        // -> Akan error apabila tidak menambahkan Statement.RETURN_GENERATED_KEYS saat membuat statement

        if (res.next()) {
            int id = res.getInt(1);
            System.out.println(updated + " updated; " + id + " -> auto generated key");
        }

        preparedStatement.close();
        connection.close();
    }

}
