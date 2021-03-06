package com.github.immrgabriel.jdbch;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PreStmt {
    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        try(Connection conn = getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
            StringBuilder strTables = new StringBuilder();
            if(tables.next()) {
                strTables.append(tables.getString(3));                  // or "TABLE_NAME"
            }
            while (tables.next()) {
                strTables.append(", ").append(tables.getString(3));     // or "TABLE_NAME"
            }

            while (true) {
                System.out.println("Tables:");
                System.out.println(strTables);
                String tableName = request("enter a table name or EXIT to exit");
                if(tableName == null) return;

                System.out.println("Columns: ");
                ResultSet columns = metaData.getColumns(null, null, tableName.toUpperCase(), null);
                if(columns.next()) {
                    System.out.print(columns.getString(4));
                }
                while (columns.next()) {
                    System.out.print(", " + columns.getString(4));
                }
                System.out.println();

                String column = request("enter column or EXIT to exit");
                if(column == null) return;
                String query = String.format("SELECT %s FROM %s WHERE %s LIKE ?", column, tableName, column);
                while (true) {
                    String like = request("enter like or EXIT to exit");
                    if (like == null) break;
                    try {
                        PreparedStatement preStmt = conn.prepareStatement(query);
                        preStmt.setString(1, like);
                        try (ResultSet rs = preStmt.executeQuery()) {
                            showResult(rs);
                        }
                    } catch (SQLException e) {
                        for (Throwable throwable : e) {
                            throwable.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            for (Throwable throwable : e) {
                throwable.printStackTrace();
            }
        }
    }

    private static void showResult(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int count = metaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            System.out.print(" " + metaData.getColumnName(i));
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= count; i++) {
                System.out.print(" " + rs.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        try(InputStream in = new BufferedInputStream(new FileInputStream(PreStmt.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
                                "com/github/immrgabriel/jdbch/database.properties"))){
            properties.load(in);
        }
        String drivers = properties.getProperty("jdbc.drivers");
        if(drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.user");
        String password = properties.getProperty("jdbc.password");
        return DriverManager.getConnection(url, user, password);
    }

    public static String request(String message) {
        System.out.println(message);
        String request = scanner.nextLine();
        if("EXIT".equalsIgnoreCase(request))
            return null;
        return request;
    }
}
