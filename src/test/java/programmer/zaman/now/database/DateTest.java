package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.sql.Date;

public class DateTest {

    @Test
    void testAutoIncrement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        // UPDATE QUERY
//        String sql = "INSERT INTO sample_time(sample_date, sample_time, sample_timestamp) values (?, ?, ?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
//        preparedStatement.setTime(2, new Time(System.currentTimeMillis()));
//        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//
//        int updated = preparedStatement.executeUpdate();

//        ====================================================================================
        // SELECT QUERY
        String sql = "SELECT * FROM sample_time";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet res = preparedStatement.executeQuery();

        while (res.next()) {
            Date date = res.getDate(2);
            Time time = res.getTime(3);
            Timestamp timestamp = res.getTimestamp(4);

            System.out.println(date + ", " + time + ", " + " dan " + timestamp);
        }
        res.close();

        preparedStatement.close();
        connection.close();
    }

}
