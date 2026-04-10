package com.quiz.db;
import com.quiz.model.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class QuestionDAO {
    public List<Question> getRandom(String subject,int count) {
        System.out.println("Getting questions for subject: " + subject);
        List<Question> list=new ArrayList<>();
        String sql="SELECT * FROM questions WHERE subject=? ORDER BY RAND() LIMIT ?";
        try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,subject);ps.setInt(2,count);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) list.add(new Question(rs.getInt("id"),rs.getString("subject"),
                rs.getString("question_text"),rs.getString("option_a"),rs.getString("option_b"),
                rs.getString("option_c"),rs.getString("option_d"),rs.getString("correct_answer")));
        }catch(SQLException e){System.err.println(e.getMessage());}
        return list;
    }
}
