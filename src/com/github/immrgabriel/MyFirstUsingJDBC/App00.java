package com.github.immrgabriel.MyFirstUsingJDBC;

import java.sql.*;


public class App00 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");     // указываем наш JDBC драйвер если не добавили в проект
            myConn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
            System.out.println("Database connection successful!");
        } finally {
            if(myConn != null) {
                myConn.close();
            }
        }
    }
}
