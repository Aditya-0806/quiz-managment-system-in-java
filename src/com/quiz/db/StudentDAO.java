package com.quiz.db;
import com.quiz.model.Student;
import java.sql.*;
import java.util.*;
public class StudentDAO {
    public boolean register(Student s) {
        String sql="INSERT INTO students(name,email,password,phone,dob) VALUES(?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,s.getName());ps.setString(2,s.getEmail());ps.setString(3,s.getPassword());
            ps.setString(4,s.getPhone());ps.setString(5,s.getDob());
            return ps.executeUpdate()>0;
        }catch(SQLException e){System.err.println(e.getMessage());return false;}
    }
    public Student login(String email,String password) {
        System.out.println("Login attempt for " + email);
        String sql="SELECT * FROM students WHERE email=? AND password=?";
        try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,email);ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return new Student(rs.getInt("id"),rs.getString("name"),rs.getString("email"),
                    rs.getString("password"),rs.getString("phone"),rs.getString("dob"));
            }
        }catch(SQLException e){System.err.println(e.getMessage());}
        return null;
    }
    public boolean emailExists(String email) {
        try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("SELECT id FROM students WHERE email=?")){
            ps.setString(1,email);return ps.executeQuery().next();
        }catch(SQLException e){return false;}
    }
    public List<Student> getAll() {
        List<Student> list=new ArrayList<Student>();
        try(Connection c=DBConnection.getConnection();Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM students ORDER BY name")){
            while(rs.next()) list.add(new Student(rs.getInt("id"),rs.getString("name"),
                rs.getString("email"),rs.getString("password"),rs.getString("phone"),rs.getString("dob")));
        }catch(SQLException e){System.err.println(e.getMessage());}
        return list;
    }
}
