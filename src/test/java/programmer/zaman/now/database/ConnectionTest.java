package programmer.zaman.now.database;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    @BeforeAll
    static void beforeAll() {
        try {
            Driver mysqlDriver = new Driver();
            DriverManager.registerDriver(mysqlDriver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConnection() {
        String jdbcUrl =  "jdbc:mysql://localhost:3306/belajar_java_database";
        // Apabila terkena issue timezone saat connect ke database,
        // String jdbcUrl =  "jdbc:mysql://localhost:3306/belajar_java_database?serverTimezone=Asia/Jakarta";
        String username = "root";
        String password = "root";

        try {
//            Tidak direkomendasikan, lebih baik menggunakan pool.
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            System.out.println("Connection Established");
            connection.close();
            System.out.println("Connection Closed");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
