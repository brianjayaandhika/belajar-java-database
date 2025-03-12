package programmer.zaman.now.database;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoolTest {

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
    void HikariCP() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_database");
        config.setUsername("root");
        config.setPassword("root");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60);
        config.setMaxLifetime(10 * 60_000);


        try {
            HikariDataSource dataSource = new HikariDataSource(config);

            Connection connection = dataSource.getConnection();
            System.out.println("Sukses mengambil koneksi");

            connection.close(); // -> Ini bukan close koneksinya, tapi hanya dikembalikan ke HikariDataSourcenya
            System.out.println("Sukses Mengembalikan Koneksi");

            dataSource.close(); // -> Menutup semua pool koneksi
            System.out.println("Sukses Menutup Pool Koneksi");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testUtil() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
    }
}
