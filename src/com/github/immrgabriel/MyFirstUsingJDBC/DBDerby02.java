package com.github.immrgabriel.MyFirstUsingJDBC;

import java.sql.*;

public class DBDerby02 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Lesson22");

            try {
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
                stmt.addBatch("INSERT INTO Employee values (7777,'Anonymus', 'Clerk')");
                stmt.addBatch("UPDATE Employee SET ename='Mr Anonymus' WHERE empno=7777");
                stmt.addBatch("UPDATE Employee SET ename2='Mr Anonymus' WHERE empno=7777");         // Fail
                stmt.executeBatch();
                conn.commit();                                                          // Transaction succeeded!
            } catch (SQLException e) {
                conn.rollback();                                                        // Transaction failed!
                e.printStackTrace();
            }

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