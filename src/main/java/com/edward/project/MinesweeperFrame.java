package com.edward.project;

/**
 * @author Neuron-to-Opens
 * @Description
 * @create 2025-03-20 3:59
 * @ProjectName com.edward.project
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MinesweeperFrame extends JFrame {
    private int rows = 10;
    private int cols = 10;
    private int mineCount = 10;
    private JLabel timerLabel;
    private JLabel remainingMinesLabel;
    private Timer timer;
    private int timeElapsed = 0;
    private JButton[][] buttons;
    private boolean[][] mines;
    private boolean[][] revealed;
    private int remainingMines;

    public MinesweeperFrame() {
        setTitle("扫雷游戏");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 添加设置界面
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(3, 2));

        JTextField rowField = new JTextField("10");
        JTextField colField = new JTextField("10");
        JTextField mineField = new JTextField("10");

        settingsPanel.add(new JLabel("行数:"));
        settingsPanel.add(rowField);
        settingsPanel.add(new JLabel("列数:"));
        settingsPanel.add(colField);
        settingsPanel.add(new JLabel("地雷数:"));
        settingsPanel.add(mineField);

        JButton startButton = new JButton("开始游戏");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rows = Integer.parseInt(rowField.getText());
                cols = Integer.parseInt(colField.getText());
                mineCount = Integer.parseInt(mineField.getText());
                startGame();
            }
        });

        add(settingsPanel, BorderLayout.NORTH);
        add(startButton, BorderLayout.SOUTH);
    }

    private void startGame() {
        // 清空之前的组件
        getContentPane().removeAll();

        // 添加计时器和剩余地雷数显示
        JPanel infoPanel = new JPanel();
        timerLabel = new JLabel("时间: 0");
        remainingMinesLabel = new JLabel("剩余地雷: " + mineCount);
        infoPanel.add(timerLabel);
        infoPanel.add(remainingMinesLabel);
        add(infoPanel, BorderLayout.NORTH);

        // 初始化扫雷网格
        buttons = new JButton[rows][cols];
        mines = new boolean[rows][cols];
        revealed = new boolean[rows][cols];
        remainingMines = mineCount;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, cols));

        Random random = new Random();
        for (int i = 0; i < mineCount; i++) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            if (mines[row][col]) {
                i--;
            } else {
                mines[row][col] = true;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton button = new JButton();
                button.addActionListener(new ButtonClickListener(i, j));
                buttons[i][j] = button;
                panel.add(button);
            }
        }

        add(panel, BorderLayout.CENTER);

        // 启动计时器
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeElapsed++;
                timerLabel.setText("时间: " + timeElapsed);
            }
        });
        timer.start();

        // 重新绘制界面
        revalidate();
        repaint();
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (mines[row][col]) {
                // 踩到地雷，游戏结束
                timer.stop();
                JOptionPane.showMessageDialog(MinesweeperFrame.this, "游戏结束！", "失败", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            } else {
                // 显示周围地雷数
                revealCell(row, col);
                if (checkWin()) {
                    timer.stop();
                    JOptionPane.showMessageDialog(MinesweeperFrame.this, "扫雷成功！时间: " + timeElapsed + "秒", "胜利", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        }
    }

    private void revealCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || revealed[row][col]) {
            return;
        }
        revealed[row][col] = true;
        int count = countAdjacentMines(row, col);
        buttons[row][col].setText(count > 0 ? String.valueOf(count) : "");
        buttons[row][col].setEnabled(false);
        if (count == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    revealCell(i, j);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < cols && mines[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean checkWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!mines[i][j] && !revealed[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}

