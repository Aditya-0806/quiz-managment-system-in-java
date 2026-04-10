package com.quiz.db;
import com.quiz.model.Result;
import java.sql.*;
import java.util.*;
public class ResultDAO {
    public boolean save(Result r) {
        String sql="INSERT INTO results(student_id,student_name,subject,total_questions,correct_answers,wrong_answers,percentage) VALUES(?,?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,r.getStudentId());ps.setString(2,r.getStudentName());ps.setString(3,r.getSubject());
            ps.setInt(4,r.getTotalQuestions());ps.setInt(5,r.getCorrectAnswers());ps.setInt(6,r.getWrongAnswers());
            ps.setDouble(7,r.getPercentage());return ps.executeUpdate()>0;
        }catch(SQLException e){System.err.println(e.getMessage());return false;}
    }
    private Result map(ResultSet rs) throws SQLException {
        Result r=new Result();r.setId(rs.getInt("id"));r.setStudentId(rs.getInt("student_id"));
        r.setStudentName(rs.getString("student_name"));r.setSubject(rs.getString("subject"));
        r.setTotalQuestions(rs.getInt("total_questions"));r.setCorrectAnswers(rs.getInt("correct_answers"));
        r.setWrongAnswers(rs.getInt("wrong_answers"));r.setPercentage(rs.getDouble("percentage"));
        r.setAttemptDate(rs.getString("attempt_date"));return r;
    }
    public List<Result> getAll() {
        List<Result> list=new ArrayList<Result>();
        try(Connection c=DBConnection.getConnection();Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM results ORDER BY attempt_date DESC")){
            while(rs.next()) list.add(map(rs));
        }catch(SQLException e){System.err.println(e.getMessage());}
        return list;
    }
    public List<Result> getByStudent(int sid) {
        List<Result> list=new ArrayList<Result>();
        try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("SELECT * FROM results WHERE student_id=? ORDER BY attempt_date DESC")){
            ps.setInt(1,sid);ResultSet rs=ps.executeQuery();
            while(rs.next()) list.add(map(rs));
        }catch(SQLException e){System.err.println(e.getMessage());}
        return list;
    }
    public Result getTopper() {
        try(Connection c=DBConnection.getConnection();Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM results ORDER BY correct_answers DESC,percentage DESC LIMIT 1")){
            if(rs.next()) return map(rs);
        }catch(SQLException e){System.err.println(e.getMessage());}
        return null;
    }
}
