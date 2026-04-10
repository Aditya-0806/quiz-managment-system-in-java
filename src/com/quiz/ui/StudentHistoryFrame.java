package com.quiz.ui;
import com.quiz.db.ResultDAO;
import com.quiz.model.*;
import com.quiz.util.UIHelper;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class StudentHistoryFrame extends JFrame {
    public StudentHistoryFrame(Student student) {
        setTitle("My Quiz History - "+student.getName());
        setSize(750, 500); setResizable(false);
        UIHelper.styleFrame(this);
        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIHelper.PRIMARY);
        header.setPreferredSize(new Dimension(750,70));
        JLabel t = new JLabel("  My Quiz History", SwingConstants.LEFT);
        t.setFont(UIHelper.TITLE); t.setForeground(Color.WHITE);
        JLabel s = new JLabel(student.getName()+" | "+student.getEmail()+"  ", SwingConstants.RIGHT);
        s.setFont(UIHelper.BODY); s.setForeground(new Color(200,220,240));
        header.add(t,BorderLayout.WEST); header.add(s,BorderLayout.EAST);
        header.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        add(header, BorderLayout.NORTH);

        ResultDAO dao = new ResultDAO();
        List<Result> results = dao.getByStudent(student.getId());

        String[] cols = {"#","Subject","Score","Correct","Wrong","Percentage","Date"};
        Object[][] data = new Object[results.size()][7];
        for(int i=0;i<results.size();i++){
            Result r=results.get(i);
            data[i][0]=i+1; data[i][1]=r.getSubject();
            data[i][2]=r.getCorrectAnswers()+"/"+r.getTotalQuestions();
            data[i][3]=r.getCorrectAnswers(); data[i][4]=r.getWrongAnswers();
            data[i][5]=String.format("%.1f%%",r.getPercentage());
            data[i][6]=r.getAttemptDate()!=null?r.getAttemptDate().substring(0,16):"N/A";
        }
        DefaultTableModel model = new DefaultTableModel(data,cols){
            public boolean isCellEditable(int r,int c){return false;}
        };
        JTable table = new JTable(model);
        table.setFont(UIHelper.BODY); table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,13));
        table.getTableHeader().setBackground(UIHelper.PRIMARY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(220,220,220));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        add(scroll, BorderLayout.CENTER);

        JPanel bot = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bot.setBackground(UIHelper.BG);
        JButton closeBtn = UIHelper.makeButton("Close", UIHelper.PRIMARY);
        bot.add(closeBtn);
        add(bot, BorderLayout.SOUTH);
        closeBtn.addActionListener(e->dispose());

        if(results.isEmpty()){
            JOptionPane.showMessageDialog(this,"No exam history found.","Info",JOptionPane.INFORMATION_MESSAGE);
        }
        setVisible(true);
    }
}
