package com.github.immrgabriel.MyFirstUsingJDBC;

import java.sql.*;

public class DBDerby00 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Lesson22");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT empno, ename, job_title FROM Employee");
            while (rs.next()) {
                int empno = rs.getInt("empno");
                String ename = rs.getString(2);
                String job = rs.getString("job_title");
                System.out.println(empno + " " + ename + " " + job);
            }
        } catch (SQLException e) {
            System.out.println("SQLError: " + e.getMessage() + " code: " + e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if(rs != null)
                    rs.close();
                if(stmt != null)
                    stmt.close();
                if(conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}