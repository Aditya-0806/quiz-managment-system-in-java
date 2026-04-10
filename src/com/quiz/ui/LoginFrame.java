package com.quiz.ui;
import com.quiz.db.StudentDAO;
import com.quiz.model.Student;
import com.quiz.util.UIHelper;
import com.quiz.admin.AdminDashboard;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passField;
    private StudentDAO dao = new StudentDAO();

    public LoginFrame() {
        setTitle("Student Quiz System - Login");
        setSize(480, 520); setResizable(false);
        UIHelper.styleFrame(this);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel();
        header.setBackground(UIHelper.PRIMARY);
        header.setPreferredSize(new Dimension(480,100));
        header.setLayout(new BorderLayout());
        JLabel title = new JLabel("Student Quiz System", SwingConstants.CENTER);
        title.setFont(UIHelper.TITLE); title.setForeground(Color.WHITE);
        JLabel sub = new JLabel("Login to continue", SwingConstants.CENTER);
        sub.setFont(UIHelper.BODY); sub.setForeground(new Color(200,220,240));
        header.add(title, BorderLayout.CENTER);
        header.add(sub, BorderLayout.SOUTH);
        header.setBorder(BorderFactory.createEmptyBorder(20,0,15,0));
        add(header, BorderLayout.NORTH);

        // Form
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(UIHelper.BG);
        form.setBorder(BorderFactory.createEmptyBorder(30,50,20,50));
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL; gc.insets = new Insets(8,0,8,0);

        emailField = UIHelper.makeField();
        passField  = UIHelper.makePassField();

        gc.gridx=0; gc.gridy=0; form.add(makeLabel("Email Address:"), gc);
        gc.gridy=1; form.add(emailField, gc);
        gc.gridy=2; form.add(makeLabel("Password:"), gc);
        gc.gridy=3; form.add(passField, gc);

        JButton loginBtn = UIHelper.makeButton("LOGIN", UIHelper.PRIMARY);
        JButton regBtn   = UIHelper.makeButton("Register New", UIHelper.SECONDARY);
        JButton adminBtn = UIHelper.makeButton("Admin Login", UIHelper.TEXT_DARK);

        gc.gridy=4; gc.insets=new Insets(20,0,8,0); form.add(loginBtn, gc);
        gc.gridy=5; gc.insets=new Insets(4,0,4,0);  form.add(regBtn, gc);
        gc.gridy=6; form.add(adminBtn, gc);
        add(form, BorderLayout.CENTER);

        loginBtn.addActionListener(e -> doLogin());
        regBtn.addActionListener(e -> { new RegisterFrame().setVisible(true); dispose(); });
        adminBtn.addActionListener(e -> doAdminLogin());
        passField.addActionListener(e -> doLogin());
        setVisible(true);
    }

    private JLabel makeLabel(String t) {
        JLabel l = new JLabel(t); l.setFont(UIHelper.BODY); l.setForeground(UIHelper.TEXT_DARK);
        return l;
    }

    private void doLogin() {
        String email = emailField.getText().trim();
        String pass  = new String(passField.getPassword()).trim();
        if (email.isEmpty()||pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please fill all fields","Warning",JOptionPane.WARNING_MESSAGE); return;
        }
        Student s = dao.login(email, pass);
        if (s != null) {
            JOptionPane.showMessageDialog(this,"Welcome, "+s.getName()+"!","Login Success",JOptionPane.INFORMATION_MESSAGE);
            new SubjectSelectionFrame(s).setVisible(true); dispose();
        } else {
            JOptionPane.showMessageDialog(this,"Invalid email or password","Login Failed",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void doAdminLogin() {
        JTextField u = new JTextField(); JPasswordField p = new JPasswordField();
        Object[] msg = {"Username:", u, "Password:", p};
        int r = JOptionPane.showConfirmDialog(this, msg, "Admin Login", JOptionPane.OK_CANCEL_OPTION);
        if (r==JOptionPane.OK_OPTION) {
            if (u.getText().trim().equals("admin") && new String(p.getPassword()).equals("password")) {
                new AdminDashboard().setVisible(true); dispose();
            } else {
                JOptionPane.showMessageDialog(this,"Invalid admin credentials","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
