package com.github.immrgabriel.MyFirstUsingJDBC;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JDBCTutorial00 {
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "hr";
    private static final String DB_PASSWORD = "hr";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        try(Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM employees");) {
            System.out.println("conn.getAutoCommit() = " + conn.getAutoCommit());
            while(rs.next()) {
                int id = rs.getInt("employee_id");
                int did = rs.getInt("department_id");
                String first = rs.getString("first_name");
                String last = rs.getString("last_name");
                System.out.print("ID: " + id);
                System.out.print(", DID = " + did);
                System.out.print(", First = " + first);
                System.out.println(", Last = " + last);
            }
        }
    }
}
