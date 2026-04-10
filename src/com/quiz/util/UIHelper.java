package com.quiz.util;
import javax.swing.*;
import java.awt.*;
public class UIHelper {
    public static final Color PRIMARY   = new Color(33, 97, 140);
    public static final Color SECONDARY = new Color(46, 134, 193);
    public static final Color SUCCESS   = new Color(39, 174, 96);
    public static final Color DANGER    = new Color(192, 57, 43);
    public static final Color WARNING   = new Color(243, 156, 18);
    public static final Color BG        = new Color(236, 240, 241);
    public static final Color WHITE     = Color.WHITE;
    public static final Color TEXT_DARK = new Color(44, 62, 80);
    public static final Font  TITLE     = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font  SUBTITLE  = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font  BODY      = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font  SMALL     = new Font("Segoe UI", Font.PLAIN, 12);

    public static JButton makeButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg); b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false); b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(160, 40));
        return b;
    }
    public static JLabel makeTitle(String text) {
        JLabel l = new JLabel(text, SwingConstants.CENTER);
        l.setFont(TITLE); l.setForeground(PRIMARY); return l;
    }
    public static JTextField makeField() {
        JTextField tf = new JTextField();
        tf.setFont(BODY); tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SECONDARY,1),
            BorderFactory.createEmptyBorder(6,10,6,10)));
        return tf;
    }
    public static JPasswordField makePassField() {
        JPasswordField pf = new JPasswordField();
        pf.setFont(BODY); pf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SECONDARY,1),
            BorderFactory.createEmptyBorder(6,10,6,10)));
        return pf;
    }
    public static void styleFrame(JFrame f) {
        f.getContentPane().setBackground(BG);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
    }
}
