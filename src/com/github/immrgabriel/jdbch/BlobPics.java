package com.github.immrgabriel.jdbch;

import sun.nio.ch.IOUtil;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class BlobPics {
    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);
    private static String INSERT = "INSERT INTO TESTBLOB VALUES (?, ?)";
    private static String SELECT = "SELECT NAME_PICTURE, PICTURE FROM TESTBLOB WHERE NAME_PICTURE = ?";

    public static void main(String[] args) throws IOException {
        insert();
//        insert();
        select();

//        File file = new File(request("enter file name:"));
//        try(FileInputStream in = new FileInputStream(file);
//            FileOutputStream out = new FileOutputStream(BlobPics.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
//                    "com/github/immrgabriel/jdbch/out/" + file.getName())) {
//            copyStream(in, out);
//        }
    }

    private static boolean insert() throws IOException {
        String fileName = request("enter file name or EXIT to exit");
        if(fileName == null)
            return false;
        File file = new File(fileName);
        if(!file.exists() || !file.isFile())
            return false;
        try(Connection conn = getConnection()) {
            Blob blob = conn.createBlob();
            OutputStream out = new BufferedOutputStream(blob.setBinaryStream(0));
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            copyStream(in, out);
            out.flush();
            PreparedStatement preStmt = conn.prepareStatement(INSERT);
            preStmt.setString(1, file.getName());
            preStmt.setBlob(2, blob);
            preStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void select() throws IOException {
        try(Connection conn = getConnection()) {
            System.out.println("File names:");
            try(Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT NAME_PICTURE FROM TESTBLOB")) {
                if(rs.next())
                    System.out.print(rs.getString(1));
                while (rs.next())
                    System.out.print(", " + rs.getString(1));
                System.out.println();
            }
            String request = request("select file name or EXIT to exit");
            if(request == null)
                return;
            try(PreparedStatement preStmt = conn.prepareStatement(SELECT)) {
                preStmt.setString(1, request);
                try(ResultSet rs = preStmt.executeQuery()) {
                    while (rs.next()) {
                        String name = rs.getString(1);
                        copyToOut(name, rs.getBinaryStream(2));
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static void copyToOut(String name, InputStream in) throws SQLException {
        System.out.println(name);
        try(BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(BlobPics.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
                    "com/github/immrgabriel/jdbch/out/" + name))) {
                copyStream(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024]; // Adjust if you want
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
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
        try(InputStream in = new BufferedInputStream(new FileInputStream(BlobPics.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
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
