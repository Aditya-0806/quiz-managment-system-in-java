package com.quiz.ui;
import com.quiz.model.Student;
import com.quiz.util.UIHelper;
import javax.swing.*;
import java.awt.*;

public class SubjectSelectionFrame extends JFrame {
    private Student student;
    private String[] subjects = {"Java","Mathematics","Computer Networks","DBMS","Advanced Data Structures and Algorithms"};

    public SubjectSelectionFrame(Student student) {
        this.student = student;
        setTitle("Select Subject - Quiz System");
        setSize(520, 540); setResizable(false);
        UIHelper.styleFrame(this);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header
        JPanel header = new JPanel(new GridLayout(2,1));
        header.setBackground(UIHelper.PRIMARY);
        header.setPreferredSize(new Dimension(520,100));
        JLabel title = new JLabel("Welcome, " + student.getName() + "!", SwingConstants.CENTER);
        title.setFont(UIHelper.TITLE); title.setForeground(Color.WHITE);
        JLabel sub = new JLabel("Select a subject to start the exam", SwingConstants.CENTER);
        sub.setFont(UIHelper.BODY); sub.setForeground(new Color(200,220,240));
        header.add(title); header.add(sub);
        header.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));
        add(header, BorderLayout.NORTH);

        // Subject buttons
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(UIHelper.BG);
        center.setBorder(BorderFactory.createEmptyBorder(30,60,20,60));
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill=GridBagConstraints.HORIZONTAL; gc.insets=new Insets(8,0,8,0); gc.gridx=0;

        Color[] colors = {UIHelper.PRIMARY, UIHelper.SECONDARY, new Color(142,68,173),
                          new Color(26,188,156), new Color(230,126,34)};
        for(int i=0;i<subjects.length;i++){
            final String subj = subjects[i];
            JButton btn = new JButton("  " + subj);
            btn.setBackground(colors[i]); btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI",Font.BOLD,14));
            btn.setFocusPainted(false); btn.setBorderPainted(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.setPreferredSize(new Dimension(380,48));
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.addActionListener(e -> startQuiz(subj));
            gc.gridy=i; center.add(btn,gc);
        }

        JButton histBtn = UIHelper.makeButton("My History", UIHelper.TEXT_DARK);
        JButton logoutBtn = UIHelper.makeButton("Logout", UIHelper.DANGER);
        JPanel bot = new JPanel(new FlowLayout(FlowLayout.CENTER,15,15));
        bot.setBackground(UIHelper.BG); bot.add(histBtn); bot.add(logoutBtn);
        add(center, BorderLayout.CENTER);
        add(bot, BorderLayout.SOUTH);

        histBtn.addActionListener(e -> new StudentHistoryFrame(student).setVisible(true));
        logoutBtn.addActionListener(e -> { new LoginFrame().setVisible(true); dispose(); });
        setVisible(true);
    }

    private void startQuiz(String subject) {
        int confirm = JOptionPane.showConfirmDialog(this,
            "<html>Start exam for: <b>" + subject + "</b><br>10 random questions, timed per question.<br>Are you ready?</html>",
            "Start Exam", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                new QuizFrame(student, subject).setVisible(true);
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                    "Failed to start quiz:\n" + ex.getClass().getSimpleName() + ": " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
