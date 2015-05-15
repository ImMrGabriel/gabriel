package com.github.immrgabriel.servlet.logic;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SimpleSelect {
    private final static  String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final static  String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private final static  String USER = "hr";
    private final static  String PASSWORD = "hr";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your JDBC Driver?");
            System.out.println(e.getMessage());
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static List<Employee> getEmployees() {
        List<Employee> list = new LinkedList<>();
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT employee_id, first_name, last_name FROM Employees")) {
            while (rs.next()) {
                list.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
