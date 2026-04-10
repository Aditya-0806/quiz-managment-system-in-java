package com.quiz.admin;
import com.quiz.db.*;
import com.quiz.model.*;
import com.quiz.util.UIHelper;
import com.quiz.ui.LoginFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AdminDashboard extends JFrame {
    private ResultDAO resultDAO = new ResultDAO();
    private StudentDAO studentDAO = new StudentDAO();

    public AdminDashboard() {
        setTitle("Admin Dashboard - Student Quiz System");
        setSize(900, 640); setResizable(false);
        UIHelper.styleFrame(this);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(44,62,80));
        header.setPreferredSize(new Dimension(900,80));
        JLabel title = new JLabel("  Admin Dashboard", SwingConstants.LEFT);
        title.setFont(UIHelper.TITLE); title.setForeground(Color.WHITE);
        JButton logoutBtn = UIHelper.makeButton("Logout", UIHelper.DANGER);
        logoutBtn.setPreferredSize(new Dimension(120,36));
        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT,15,22));
        headerRight.setBackground(new Color(44,62,80));
        headerRight.add(logoutBtn);
        header.add(title, BorderLayout.WEST); header.add(headerRight, BorderLayout.EAST);
        header.setBorder(new EmptyBorder(10,20,10,10));
        add(header, BorderLayout.NORTH);

        // Stats row
        List<Result> allResults = resultDAO.getAll();
        int totalAttempts=allResults.size();
        double avgPct=allResults.stream().mapToDouble(Result::getPercentage).average().orElse(0);
        int passCount=(int)allResults.stream().filter(r->r.getPercentage()>=60).count();
        int students=studentDAO.getAll().size();

        JPanel statsRow = new JPanel(new GridLayout(1,4,12,0));
        statsRow.setBackground(UIHelper.BG);
        statsRow.setBorder(new EmptyBorder(15,20,10,20));
        statsRow.add(statCard("Total Students", String.valueOf(students), UIHelper.PRIMARY));
        statsRow.add(statCard("Total Attempts", String.valueOf(totalAttempts), UIHelper.SECONDARY));
        statsRow.add(statCard("Average Score", String.format("%.1f%%",avgPct), new Color(142,68,173)));
        statsRow.add(statCard("Pass Rate", String.format("%.0f%%", totalAttempts>0?(passCount*100.0/totalAttempts):0), UIHelper.SUCCESS));
        add(statsRow, BorderLayout.CENTER);

        // Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(UIHelper.BODY);
        tabs.addTab("All Results", buildResultsPanel(allResults));
        tabs.addTab("All Students", buildStudentsPanel());
        tabs.addTab("Topper", buildTopperPanel());
        add(tabs, BorderLayout.SOUTH);
        tabs.setPreferredSize(new Dimension(900,380));

        logoutBtn.addActionListener(e->{ new LoginFrame().setVisible(true); dispose(); });
        setVisible(true);
    }

    private JPanel statCard(String label, String value, Color color) {
        JPanel p = new JPanel(new GridLayout(2,1));
        p.setBackground(color);
        p.setBorder(new EmptyBorder(12,15,12,15));
        p.setPreferredSize(new Dimension(200,80));
        JLabel v = new JLabel(value, SwingConstants.CENTER);
        v.setFont(new Font("Segoe UI",Font.BOLD,26)); v.setForeground(Color.WHITE);
        JLabel l = new JLabel(label, SwingConstants.CENTER);
        l.setFont(UIHelper.SMALL); l.setForeground(new Color(220,240,220));
        p.add(v); p.add(l); return p;
    }

    private JScrollPane buildResultsPanel(List<Result> results) {
        String[] cols={"#","Student","Subject","Score","Correct","Wrong","Percentage","Date"};
        Object[][] data=new Object[results.size()][8];
        for(int i=0;i<results.size();i++){
            Result r=results.get(i);
            data[i][0]=i+1; data[i][1]=r.getStudentName(); data[i][2]=r.getSubject();
            data[i][3]=r.getCorrectAnswers()+"/"+r.getTotalQuestions();
            data[i][4]=r.getCorrectAnswers(); data[i][5]=r.getWrongAnswers();
            data[i][6]=String.format("%.1f%%",r.getPercentage());
            data[i][7]=r.getAttemptDate()!=null?r.getAttemptDate().substring(0,16):"N/A";
        }
        return wrapTable(data,cols);
    }

    private JScrollPane buildStudentsPanel() {
        List<Student> students = studentDAO.getAll();
        String[] cols={"#","Name","Email","Phone","DOB"};
        Object[][] data=new Object[students.size()][5];
        for(int i=0;i<students.size();i++){
            Student s=students.get(i);
            data[i][0]=i+1; data[i][1]=s.getName(); data[i][2]=s.getEmail();
            data[i][3]=s.getPhone(); data[i][4]=s.getDob();
        }
        return wrapTable(data,cols);
    }

    private JPanel buildTopperPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(UIHelper.BG);
        Result topper = resultDAO.getTopper();
        if(topper==null) {
            p.add(new JLabel("No data yet.",SwingConstants.CENTER),BorderLayout.CENTER);
            return p;
        }
        JPanel card = new JPanel(new GridLayout(6,2,10,10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255,215,0),2),
            new EmptyBorder(20,40,20,40)));
        card.setMaximumSize(new Dimension(500,300));
        JLabel trophy = new JLabel("TOPPER", SwingConstants.CENTER);
        trophy.setFont(new Font("Segoe UI",Font.BOLD,24));
        trophy.setForeground(new Color(180,120,0));
        JPanel trophyWrap=new JPanel(new FlowLayout(FlowLayout.CENTER));
        trophyWrap.setBackground(new Color(255,250,220));
        trophyWrap.setBorder(new EmptyBorder(10,0,10,0));
        trophyWrap.add(trophy);
        p.add(trophyWrap,BorderLayout.NORTH);
        addRow(card,"Student Name:", topper.getStudentName());
        addRow(card,"Subject:", topper.getSubject());
        addRow(card,"Score:", topper.getCorrectAnswers()+"/"+topper.getTotalQuestions());
        addRow(card,"Correct Answers:", String.valueOf(topper.getCorrectAnswers()));
        addRow(card,"Percentage:", String.format("%.1f%%",topper.getPercentage()));
        addRow(card,"Attempt Date:", topper.getAttemptDate()!=null?topper.getAttemptDate():"N/A");
        JPanel centerWrap=new JPanel(new FlowLayout(FlowLayout.CENTER,0,20));
        centerWrap.setBackground(UIHelper.BG); centerWrap.add(card);
        p.add(centerWrap,BorderLayout.CENTER);
        return p;
    }

    private void addRow(JPanel p, String label, String value) {
        JLabel l=new JLabel(label); l.setFont(new Font("Segoe UI",Font.BOLD,14)); l.setForeground(UIHelper.TEXT_DARK);
        JLabel v=new JLabel(value); v.setFont(UIHelper.BODY); v.setForeground(UIHelper.PRIMARY);
        p.add(l); p.add(v);
    }

    private JScrollPane wrapTable(Object[][] data, String[] cols) {
        DefaultTableModel model=new DefaultTableModel(data,cols){
            public boolean isCellEditable(int r,int c){return false;}
        };
        JTable table=new JTable(model);
        table.setFont(UIHelper.SMALL); table.setRowHeight(30);
        table.setGridColor(new Color(220,220,220));
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,13));
        table.getTableHeader().setBackground(new Color(44,62,80));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(210,230,255));
        return new JScrollPane(table);
    }
}
