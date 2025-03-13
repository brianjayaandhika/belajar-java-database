package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetadataTest {

    @Test
    void testDatabaseMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData dbm = connection.getMetaData();

        System.out.println(
                "product name: " + dbm.getDatabaseProductName() + "; product version: " +
                        dbm.getDatabaseProductVersion()
        );

        ResultSet res = dbm.getTables("belajar_java_database", null, null, null);

        while (res.next()) {
            String tableName = res.getString("TABLE_NAME");
            System.out.println(tableName);

        }


        connection.close();
    }

    @Test
    void testParameterMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO comments(email, comment) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ParameterMetaData pm = preparedStatement.getParameterMetaData();

        // Commented -> Not supported yet for MYSQL Driver
//        System.out.println("getParameterClassName 1 -> " + pm.getParameterClassName(1));
//        System.out.println("getParameterTypeName 1 -> " + pm.getParameterTypeName(1));
//        System.out.println("getParameterType 1 -> " + pm.getParameterType(1));
//        System.out.println("getParameterType 2 -> " + pm.getParameterType(2));
//        System.out.println("getParameterCount -> " + pm.getParameterCount());

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testResultSetMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "SELECT * FROM comments LIMIT 10";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet res = preparedStatement.executeQuery();

        ResultSetMetaData rsm = res.getMetaData();

        for (int i = 1; i <= rsm.getColumnCount(); i++) {
            System.out.println("Name: " + rsm.getColumnName(i));
            System.out.println("Type: " + rsm.getColumnType(i));
            System.out.println("Type Name: " + rsm.getColumnTypeName(i));
            System.out.println("======================================================");
        }

        preparedStatement.close();
        connection.close();
    }


}
