package com.github.immrgabriel.jdbch;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnProp {
    private static Connection connection;

    public static void main(String[] args) throws IOException {
        try {
            runTest();
        } catch (SQLException e) {
            for (Throwable throwable : e) {
                throwable.printStackTrace();
            }
        }
    }

    private static void runTest() throws SQLException, IOException {
        try(Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE TABLE Greating ( message VARCHAR(50))");
            stmt.executeUpdate("INSERT INTO Greating VALUES ('Hello, world!')");

            try(ResultSet rs = stmt.executeQuery("SELECT message FROM Greating")) {
                while(rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }
            stmt.executeUpdate("DROP TABLE Greating");
        }
    }

    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        try(InputStream in = new BufferedInputStream(new FileInputStream(ConnProp.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
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
}
