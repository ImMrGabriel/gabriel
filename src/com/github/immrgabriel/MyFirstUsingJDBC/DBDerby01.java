package com.github.immrgabriel.MyFirstUsingJDBC;

import java.sql.*;

public class DBDerby01 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Lesson22");

            // SQL gets compile once
            stmt = conn.prepareStatement("SELECT empno, ename, job_title FROM Employee WHERE empno = ?");
            int[] arr = {7369, 7499, 25, 7521};
            for (int i = 0; i < arr.length; i++) {
                stmt.setInt(1, arr[i]);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    int empno = rs.getInt("empno");
                    String ename = rs.getString(2);
                    String job = rs.getString("job_title");
                    System.out.println(empno + " " + ename + " " + job);
                }
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