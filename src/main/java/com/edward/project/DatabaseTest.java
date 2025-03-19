package com.edward.project;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Neuron-to-Opens
 * @Description
 * @create 2025-03-20 3:15
 * @ProjectName com.edward.project
 */
public class DatabaseTest {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("数据库连接成功！");
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
    }
}
