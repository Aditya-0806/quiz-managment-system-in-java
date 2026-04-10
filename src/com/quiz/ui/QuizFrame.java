package com.quiz.ui;
import com.quiz.db.QuestionDAO;
import com.quiz.db.ResultDAO;
import com.quiz.model.*;
import com.quiz.util.UIHelper;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;

public class QuizFrame extends JFrame {
    private Student student;
    private String subject;
    private java.util.List<Question> questions;
    private java.util.List<String> userAnswers;
    private int current = 0, timeLeft = 30;
    private Timer timer;

    // UI
    private JLabel questionNumLabel, timerLabel, questionLabel;
    private JRadioButton optA, optB, optC, optD;
    private ButtonGroup bg;
    private JButton nextBtn, prevBtn, submitBtn;
    private JProgressBar timerBar;

    public QuizFrame(Student student, String subject) {
        try {
            this.student = student;
            this.subject = subject;
            System.out.println("Starting quiz for " + subject);
            QuestionDAO dao = new QuestionDAO();
            questions = dao.getRandom(subject, 10);
            System.out.println("Questions loaded: " + questions.size());
            userAnswers = new ArrayList<String>(Collections.nCopies(questions.size(), ""));

            if (questions.isEmpty()) {
                JOptionPane.showMessageDialog(null,"No questions found for "+subject,"Error",JOptionPane.ERROR_MESSAGE);
                new SubjectSelectionFrame(student).setVisible(true); dispose(); return;
            }

            setTitle("Quiz: " + subject);
            setSize(700, 560); setResizable(false);
            UIHelper.styleFrame(this);
            setLayout(new BorderLayout(10,10));
            buildUI();
            loadQuestion(0);
            startTimer();
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error initializing the quiz:\n" + ex.getClass().getSimpleName() + ": " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            new SubjectSelectionFrame(student).setVisible(true);
            dispose();
        }
    }

    private void buildUI() {
        // Top bar
        JPanel top = new JPanel(new BorderLayout(10,5));
        top.setBackground(UIHelper.PRIMARY);
        top.setBorder(new EmptyBorder(12,20,12,20));
        questionNumLabel = new JLabel("Q1 / 10", SwingConstants.LEFT);
        questionNumLabel.setFont(UIHelper.SUBTITLE); questionNumLabel.setForeground(Color.WHITE);
        timerLabel = new JLabel("30s", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Segoe UI",Font.BOLD,20)); timerLabel.setForeground(Color.WHITE);
        JLabel subLabel = new JLabel(subject, SwingConstants.CENTER);
        subLabel.setFont(UIHelper.BODY); subLabel.setForeground(new Color(200,220,240));
        top.add(questionNumLabel, BorderLayout.WEST);
        top.add(subLabel, BorderLayout.CENTER);
        top.add(timerLabel, BorderLayout.EAST);
        timerBar = new JProgressBar(0,30);
        timerBar.setValue(30); timerBar.setStringPainted(false);
        timerBar.setForeground(UIHelper.SUCCESS); timerBar.setBackground(new Color(70,130,180));
        timerBar.setPreferredSize(new Dimension(700,6));
        JPanel topWrap = new JPanel(new BorderLayout());
        topWrap.setBackground(UIHelper.PRIMARY);
        topWrap.add(top, BorderLayout.CENTER); topWrap.add(timerBar, BorderLayout.SOUTH);
        add(topWrap, BorderLayout.NORTH);

        // Question panel
        JPanel qPanel = new JPanel(new BorderLayout(10,10));
        qPanel.setBackground(Color.WHITE);
        qPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0,0,1,0,UIHelper.SECONDARY),
            new EmptyBorder(25,30,20,30)));
        questionLabel = new JLabel("<html><body style='width:580px'>Question</body></html>");
        questionLabel.setFont(new Font("Segoe UI",Font.PLAIN,16));
        questionLabel.setForeground(UIHelper.TEXT_DARK);
        qPanel.add(questionLabel, BorderLayout.CENTER);
        add(qPanel, BorderLayout.CENTER);

        // Options
        JPanel optPanel = new JPanel(new GridLayout(4,1,5,5));
        optPanel.setBackground(UIHelper.BG); optPanel.setBorder(new EmptyBorder(15,30,10,30));
        optA = makeOpt("A"); optB = makeOpt("B"); optC = makeOpt("C"); optD = makeOpt("D");
        bg = new ButtonGroup();
        bg.add(optA); bg.add(optB); bg.add(optC); bg.add(optD);
        optPanel.add(optA); optPanel.add(optB); optPanel.add(optC); optPanel.add(optD);
        add(optPanel, BorderLayout.SOUTH);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,15,10));
        btnPanel.setBackground(UIHelper.BG);
        prevBtn   = UIHelper.makeButton("◀ Previous", UIHelper.SECONDARY);
        nextBtn   = UIHelper.makeButton("Next ▶", UIHelper.PRIMARY);
        submitBtn = UIHelper.makeButton("Submit Exam", UIHelper.SUCCESS);
        submitBtn.setVisible(false);
        btnPanel.add(prevBtn); btnPanel.add(nextBtn); btnPanel.add(submitBtn);

        JPanel southWrap = new JPanel(new BorderLayout());
        southWrap.setBackground(UIHelper.BG);
        southWrap.add(optPanel, BorderLayout.CENTER);
        southWrap.add(btnPanel, BorderLayout.SOUTH);
        add(southWrap, BorderLayout.SOUTH);

        prevBtn.addActionListener(e -> saveAndGo(current-1));
        nextBtn.addActionListener(e -> saveAndGo(current+1));
        submitBtn.addActionListener(e -> confirmSubmit());
    }

    private JRadioButton makeOpt(String prefix) {
        JRadioButton r = new JRadioButton();
        r.setFont(UIHelper.BODY); r.setBackground(UIHelper.BG);
        r.setForeground(UIHelper.TEXT_DARK); r.setFocusPainted(false);
        return r;
    }

    private void loadQuestion(int idx) {
        if (idx<0||idx>=questions.size()) return;
        current = idx;
        Question q = questions.get(idx);
        questionNumLabel.setText("Q"+(idx+1)+" / "+questions.size());
        questionLabel.setText("<html><body style='width:580px'><b>Q"+(idx+1)+".</b> "+q.getQuestionText()+"</body></html>");
        optA.setText("A.  "+q.getOptionA());
        optB.setText("B.  "+q.getOptionB());
        optC.setText("C.  "+q.getOptionC());
        optD.setText("D.  "+q.getOptionD());
        bg.clearSelection();
        String saved = userAnswers.get(idx);
        if(saved.equals("A")) optA.setSelected(true);
        else if(saved.equals("B")) optB.setSelected(true);
        else if(saved.equals("C")) optC.setSelected(true);
        else if(saved.equals("D")) optD.setSelected(true);
        prevBtn.setEnabled(idx > 0);
        nextBtn.setVisible(idx < questions.size()-1);
        submitBtn.setVisible(idx == questions.size()-1);
        resetTimer();
    }

    private void saveAnswer() {
        String ans = "";
        if(optA.isSelected()) ans="A";
        else if(optB.isSelected()) ans="B";
        else if(optC.isSelected()) ans="C";
        else if(optD.isSelected()) ans="D";
        userAnswers.set(current, ans);
    }

    private void saveAndGo(int idx) {
        saveAnswer();
        if(idx>=0 && idx<questions.size()) loadQuestion(idx);
    }

    private void startTimer() {
        timeLeft = 30;
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText(timeLeft+"s");
            timerBar.setValue(timeLeft);
            if(timeLeft<=10) {
                timerBar.setForeground(UIHelper.DANGER);
                timerLabel.setForeground(UIHelper.WARNING);
            }
            if(timeLeft<=0) {
                timer.stop();
                saveAnswer();
                if(current < questions.size()-1) { loadQuestion(current+1); }
                else { submitExam(); }
            }
        });
        timer.start();
    }

    private void resetTimer() {
        if(timer!=null) timer.stop();
        timeLeft=30; timerLabel.setText("30s");
        timerBar.setValue(30); timerBar.setForeground(UIHelper.SUCCESS);
        timerLabel.setForeground(Color.WHITE);
        timer.start();
    }

    private void confirmSubmit() {
        saveAnswer();
        long unanswered = userAnswers.stream().filter(String::isEmpty).count();
        String msg = unanswered>0 ? "You have "+unanswered+" unanswered question(s). Submit anyway?" : "Submit exam now?";
        int r = JOptionPane.showConfirmDialog(this, msg, "Confirm Submit", JOptionPane.YES_NO_OPTION);
        if(r==JOptionPane.YES_OPTION) submitExam();
    }

    private void submitExam() {
        if(timer!=null) timer.stop();
        saveAnswer();
        int correct=0, wrong=0;
        java.util.List<String> correctList = new ArrayList<String>();
        for(int i=0;i<questions.size();i++){
            String ua = userAnswers.get(i);
            String ca = questions.get(i).getCorrectAnswer();
            if(ua.equals(ca)) correct++;
            else wrong++;
            correctList.add(ca);
        }
        double pct = (correct*100.0)/questions.size();
        Result result = new Result(student.getId(), student.getName(), subject,
            questions.size(), correct, wrong, pct);
        new ResultDAO().save(result);
        new ResultFrame(student, subject, questions, userAnswers, correctList, correct, wrong, pct).setVisible(true);
        dispose();
    }
}
