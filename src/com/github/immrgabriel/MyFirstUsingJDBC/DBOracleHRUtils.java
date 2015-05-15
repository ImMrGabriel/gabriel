package com.github.immrgabriel.MyFirstUsingJDBC;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBOracleHRUtils {

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "hr";
    private static final String DB_PASSWORD = "hr";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");



    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your JDBC Driver?");
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println("invalid connection!");
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void createDBUserTable() throws SQLException {
        Connection dbConn = null;
        Statement statement = null;

        String createTableSQL = "CREATE TABLE DBUSER("
                                + "USER_ID NUMBER(5) NOT NULL, "
                                + "USERNAME VARCHAR(20) NOT NULL, "
                                + "CREATED_BY VARCHAR(20) NOT NULL, "
                                + "CREATED_DATE DATE NOT NULL, "
                                + "PRIMARY KEY (USER_ID) "
                                + ")";

        try{
            dbConn = getDBConnection();
            statement = dbConn.createStatement();
            statement.execute(createTableSQL);
            System.out.println("Table \"dbuser\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(statement != null) {
                statement.close();
            }
            if(dbConn != null) {
                dbConn.close();
            }
        }
    }

    private static String getCurrentTimeStamp() {
        Date today = new Date();
        return dateFormat.format(today.getTime());
    }

    public static void main(String[] args) {
//        Connection conn = getDBConnection();
//        if(conn != null) {
//            System.out.println("Connection successful!");
//        }

//        try {
//            createDBUserTable();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try(Connection conn = getDBConnection();
//            Statement statement = conn.createStatement()) {
//            statement.executeUpdate("INSERT INTO DBUSER"
//                    + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
//                    + "(1,'Mr','system', " + "to_date('"
//                    + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))");
////            statement.executeUpdate("INSERT INTO DBUSER"
////                            + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
////                            + "(1,'Mr','system', " + "to_date('"
////                            + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))");        // нарушена уникальность
//            statement.executeUpdate("INSERT INTO DBUSER"
//                    + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
//                    + "(2,'Mr2','system', " + "to_date('"
//                    + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        String selectTableSQL = "SELECT user_id, username FROM dbuser";

        try(Connection dbConn = getDBConnection();
            Statement statement = dbConn.createStatement()) {
            ResultSet rSet = statement.executeQuery(selectTableSQL);                        // выполняем запрос
            while (rSet.next()) {
                String userid = rSet.getString("user_id");
                String username = rSet.getString("username");

                System.out.println("userid = " + userid);
                System.out.println("username = " + username);
            }
            rSet = statement.executeQuery("SELECT * FROM dbuser");
            while (rSet.next()) {
                System.out.print(rSet.getString("user_id"));
                System.out.print(" " + rSet.getString("username"));
                System.out.print(" " + rSet.getString("created_by"));
                System.out.println(" " + rSet.getString("created_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String deleteTableSQL = "DELETE dbuser WHERE user_id = 1";

        try(Connection dbConn = getDBConnection();
            Statement statement = dbConn.createStatement()) {
            statement.execute(deleteTableSQL);
            System.out.println("Record is deleted from dbuser table!");
            ResultSet rs = statement.executeQuery("SELECT * FROM dbuser");
            while(rs.next()) {
                System.out.print(rs.getString("user_id"));
                System.out.print(" " + rs.getString("username"));
                System.out.print(" " + rs.getString("created_by"));
                System.out.println(" " + rs.getString("created_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String updateTableSQL = "UPDATE dbuser SET username = 'sergiy' WHERE user_id = 2";

        try(Connection connection = getDBConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(updateTableSQL);
            System.out.println("Record is update to dbuser table!");
            ResultSet rs = statement.executeQuery("SELECT * FROM dbuser");
            while(rs.next()) {
                System.out.print(rs.getString("user_id"));
                System.out.print(" " + rs.getString("username"));
                System.out.print(" " + rs.getString("created_by"));
                System.out.println(" " + rs.getString("created_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
