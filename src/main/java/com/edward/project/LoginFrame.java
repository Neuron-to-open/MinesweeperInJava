package com.edward.project;

import javax.swing.*;
import java.awt.*;

/**
 * @author Neuron-to-Opens
 * @Description
 * @create 2025-03-20 2:26
 * @ProjectName com.edward.project
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() {
        setTitle("扫雷游戏登录");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("用户名:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("密码:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("登录");
        panel.add(loginButton);

        registerButton = new JButton("注册");
        panel.add(registerButton);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "登录成功");
                    // 打开游戏主界面
                    openMinesweeperFrame();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "用户名或密码错误");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (register(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "注册成功");
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "注册失败，用户名可能已存在");
                }
            }
        });
    }

    private boolean authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection() ;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean register(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void openMinesweeperFrame() {
        MinesweeperFrame minesweeperFrame = new MinesweeperFrame();
        minesweeperFrame.setVisible(true);
    }

}

