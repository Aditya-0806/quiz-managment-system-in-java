package com.quiz.ui;
import com.quiz.db.StudentDAO;
import com.quiz.model.Student;
import com.quiz.util.UIHelper;
import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField nameField, emailField, phoneField, dobField;
    private JPasswordField passField, confPassField;
    private StudentDAO dao = new StudentDAO();

    public RegisterFrame() {
        setTitle("Register - Student Quiz System");
        setSize(500, 620); setResizable(false);
        UIHelper.styleFrame(this);
        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIHelper.SECONDARY);
        header.setPreferredSize(new Dimension(500,80));
        JLabel t = new JLabel("Create Account", SwingConstants.CENTER);
        t.setFont(UIHelper.TITLE); t.setForeground(Color.WHITE);
        header.add(t, BorderLayout.CENTER);
        header.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
        add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(UIHelper.BG);
        form.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill=GridBagConstraints.HORIZONTAL; gc.insets=new Insets(6,0,6,0);

        nameField    = UIHelper.makeField();
        emailField   = UIHelper.makeField();
        phoneField   = UIHelper.makeField();
        dobField     = UIHelper.makeField(); dobField.setToolTipText("Format: YYYY-MM-DD");
        passField    = UIHelper.makePassField();
        confPassField= UIHelper.makePassField();

        String[] labels={"Full Name:","Email Address:","Phone Number:","Date of Birth (YYYY-MM-DD):","Password:","Confirm Password:"};
        JComponent[] fields={nameField,emailField,phoneField,dobField,passField,confPassField};
        for(int i=0;i<labels.length;i++){
            gc.gridy=i*2; form.add(lbl(labels[i]),gc);
            gc.gridy=i*2+1; form.add(fields[i],gc);
        }

        JButton regBtn = UIHelper.makeButton("REGISTER", UIHelper.SUCCESS);
        JButton backBtn= UIHelper.makeButton("Back to Login", UIHelper.SECONDARY);
        gc.gridy=12; gc.insets=new Insets(16,0,6,0); form.add(regBtn,gc);
        gc.gridy=13; gc.insets=new Insets(4,0,4,0);  form.add(backBtn,gc);
        add(form, BorderLayout.CENTER);

        regBtn.addActionListener(e -> doRegister());
        backBtn.addActionListener(e->{ new LoginFrame().setVisible(true); dispose(); });
        setVisible(true);
    }

    private JLabel lbl(String t){JLabel l=new JLabel(t);l.setFont(UIHelper.BODY);l.setForeground(UIHelper.TEXT_DARK);return l;}

    private void doRegister() {
        String name=nameField.getText().trim(), email=emailField.getText().trim();
        String phone=phoneField.getText().trim(), dob=dobField.getText().trim();
        String pass=new String(passField.getPassword()), conf=new String(confPassField.getPassword());
        if(name.isEmpty()||email.isEmpty()||pass.isEmpty()||dob.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please fill all fields","Warning",JOptionPane.WARNING_MESSAGE); return;}
        if(!pass.equals(conf)){
            JOptionPane.showMessageDialog(this,"Passwords do not match","Error",JOptionPane.ERROR_MESSAGE); return;}
        if(pass.length()<6){
            JOptionPane.showMessageDialog(this,"Password must be at least 6 characters","Error",JOptionPane.ERROR_MESSAGE); return;}
        if(dao.emailExists(email)){
            JOptionPane.showMessageDialog(this,"Email already registered","Error",JOptionPane.ERROR_MESSAGE); return;}
        Student s=new Student(0,name,email,pass,phone,dob);
        if(dao.register(s)){
            JOptionPane.showMessageDialog(this,"Registration successful! Please login.","Success",JOptionPane.INFORMATION_MESSAGE);
            new LoginFrame().setVisible(true); dispose();
        } else {
            JOptionPane.showMessageDialog(this,"Registration failed. Try again.","Error",JOptionPane.ERROR_MESSAGE);}
    }
}
