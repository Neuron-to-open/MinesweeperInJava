package com.edward.project;

import javax.swing.*;

/**
 * @author Neuron-to-Opens
 * @Description
 * @create 2025-03-20 0:48
 * @ProjectName com.edward.project
 */

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}