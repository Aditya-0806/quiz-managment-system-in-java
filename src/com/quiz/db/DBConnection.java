package com.quiz.db;
import java.sql.*;
public class DBConnection {
    private static final String URL="jdbc:mysql://localhost:3306/student_quiz?useSSL=false";
    private static final String USER="root";
    private static final String PASS="Adi@0806";
    private static Connection conn;
    public static Connection getConnection() {
        System.out.println("Connecting to DB");
        try {
            if (conn==null||conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn=DriverManager.getConnection(URL,USER,PASS);
            }
        } catch(ClassNotFoundException | SQLException e){
            System.err.println("DB Error: "+e.getMessage());
        }
        return conn;
    }
    public static void close() {
        try{ if(conn!=null&&!conn.isClosed()) conn.close(); } catch(SQLException e){}
    }
}
