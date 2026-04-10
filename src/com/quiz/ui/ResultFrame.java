package com.quiz.ui;
import com.quiz.model.*;
import com.quiz.util.UIHelper;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class ResultFrame extends JFrame {
    private Student student;
    private String subject;

    public ResultFrame(Student student, String subject, List<Question> questions,
                       List<String> userAnswers, List<String> correctAnswers,
                       int correct, int wrong, double pct) {
        this.student=student; this.subject=subject;
        setTitle("Exam Result - "+subject);
        setSize(750,620); setResizable(false);
        UIHelper.styleFrame(this);
        setLayout(new BorderLayout());

        // Header result banner
        Color bannerColor = pct>=60 ? UIHelper.SUCCESS : UIHelper.DANGER;
        JPanel banner = new JPanel(new GridLayout(3,1));
        banner.setBackground(bannerColor);
        banner.setBorder(new EmptyBorder(15,20,15,20));
        banner.setPreferredSize(new Dimension(750,110));
        JLabel grade = new JLabel(getGrade(pct), SwingConstants.CENTER);
        grade.setFont(new Font("Segoe UI",Font.BOLD,28)); grade.setForeground(Color.WHITE);
        JLabel info  = new JLabel(String.format("Score: %d/10   |   Correct: %d   |   Wrong: %d   |   Percentage: %.1f%%", 
                                   correct,correct,wrong,pct), SwingConstants.CENTER);
        info.setFont(UIHelper.BODY); info.setForeground(Color.WHITE);
        JLabel student_info = new JLabel(student.getName()+" | "+subject, SwingConstants.CENTER);
        student_info.setFont(UIHelper.SMALL); student_info.setForeground(new Color(220,240,220));
        banner.add(grade); banner.add(info); banner.add(student_info);
        add(banner, BorderLayout.NORTH);

        // Review table
        String[] cols = {"#","Question","Your Answer","Correct","Status"};
        Object[][] data = new Object[questions.size()][5];
        for(int i=0;i<questions.size();i++){
            data[i][0]=i+1;
            String q=questions.get(i).getQuestionText();
            data[i][1]=q.length()>60?q.substring(0,57)+"...":q;
            data[i][2]=getOption(questions.get(i), userAnswers.get(i));
            data[i][3]=getOption(questions.get(i), correctAnswers.get(i));
            data[i][4]=userAnswers.get(i).equals(correctAnswers.get(i))?"✓ Correct":"✗ Wrong";
        }
        DefaultTableModel model = new DefaultTableModel(data,cols){
            public boolean isCellEditable(int r,int c){return false;}
        };
        JTable table = new JTable(model);
        table.setFont(UIHelper.SMALL);
        table.setRowHeight(30);
        table.setGridColor(new Color(220,220,220));
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,13));
        table.getTableHeader().setBackground(UIHelper.PRIMARY);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(210,230,255));

        // Color rows
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable t,Object v,boolean sel,boolean foc,int r,int c){
                Component comp=super.getTableCellRendererComponent(t,v,sel,foc,r,c);
                String status=(String)t.getValueAt(r,4);
                if(!sel){
                    if(status.startsWith("✓")) comp.setBackground(new Color(230,255,230));
                    else comp.setBackground(new Color(255,230,230));
                }
                return comp;
            }
        });

        TableColumn col0=table.getColumnModel().getColumn(0); col0.setPreferredWidth(30);
        TableColumn col1=table.getColumnModel().getColumn(1); col1.setPreferredWidth(280);
        TableColumn col4=table.getColumnModel().getColumn(4); col4.setPreferredWidth(80);

        JScrollPane scroll=new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        add(scroll, BorderLayout.CENTER);

        // Buttons
        JPanel btnPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,15,12));
        btnPanel.setBackground(UIHelper.BG);
        JButton retakeBtn=UIHelper.makeButton("Retake", UIHelper.SECONDARY);
        JButton menuBtn=UIHelper.makeButton("Main Menu", UIHelper.PRIMARY);
        JButton logoutBtn=UIHelper.makeButton("Logout", UIHelper.DANGER);
        btnPanel.add(retakeBtn); btnPanel.add(menuBtn); btnPanel.add(logoutBtn);
        add(btnPanel, BorderLayout.SOUTH);

        retakeBtn.addActionListener(e->{ new SubjectSelectionFrame(student).setVisible(true); dispose(); });
        menuBtn.addActionListener(e->{ new SubjectSelectionFrame(student).setVisible(true); dispose(); });
        logoutBtn.addActionListener(e->{ new LoginFrame().setVisible(true); dispose(); });
        setVisible(true);
    }

    private String getGrade(double pct){
        if(pct>=90) return "Excellent!  " + String.format("%.1f%%",pct);
        if(pct>=75) return "Very Good!  " + String.format("%.1f%%",pct);
        if(pct>=60) return "Pass  " + String.format("%.1f%%",pct);
        return "Fail  " + String.format("%.1f%%",pct);
    }

    private String getOption(Question q, String key){
        switch(key.toUpperCase()){
            case "A": return "A: "+q.getOptionA();
            case "B": return "B: "+q.getOptionB();
            case "C": return "C: "+q.getOptionC();
            case "D": return "D: "+q.getOptionD();
            default:  return "(Not answered)";
        }
    }
}
