package com.edward.project;

import javax.swing.*;

/**
 * @author Neuron-to-Opens
 * @Description
 * @create 2025-03-20 3:59
 * @ProjectName com.edward.project
 */

import javax.swing.*;
import java.awt.*;

public class MinesweeperFrame extends JFrame {
    public MinesweeperFrame() {
        setTitle("扫雷游戏");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 添加扫雷游戏的组件
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 10)); // 假设10x10的扫雷网格

        for (int i = 0; i < 100; i++) {
            JButton button = new JButton();
            panel.add(button);
        }

        add(panel);
    }
}
