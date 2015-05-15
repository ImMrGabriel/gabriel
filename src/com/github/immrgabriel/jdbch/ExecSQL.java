package com.github.immrgabriel.jdbch;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ExecSQL {
    private static Connection connection;

    public static void main(String[] args) throws IOException {
        Scanner scanner = args.length == 0 ? new Scanner(System.in) :
                new Scanner(new FileInputStream(args[0]));
        try(Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            while (true) {
                if(args.length == 0)
                    System.out.println("enter your SQL command or EXIT to exit");
                if(!scanner.hasNext())
                    return;
                String line = scanner.nextLine();
                if("EXIT".equalsIgnoreCase(line))
                    return;
                if(line.trim().endsWith(";")) {
                    line = line.trim();
                    line = line.substring(0, line.length() - 1);
                }
                try{
                    boolean isResult = stmt.execute(line);
                    if(isResult) {
                        ResultSet rs = stmt.getResultSet();
                        showResult(rs);
                    } else {
                        int count = stmt.getUpdateCount();
                        System.out.println("Update count = " + count);
                    }
                } catch (SQLException e) {
                    for (Throwable throwable : e) {
                        throwable.printStackTrace();
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
        try(InputStream in = new BufferedInputStream(new FileInputStream(ExecSQL.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
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
