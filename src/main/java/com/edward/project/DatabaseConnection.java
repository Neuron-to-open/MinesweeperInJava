package com.edward.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

/**
 * @author Neuron-to-Opens
 * @Description
 * @create 2025-03-20 2:35
 * @ProjectName com.edward.project
 */
public class DatabaseConnection {
    private static DatabaseConfig config ;

    static {
        config = YamlConfigLoader.loadConfig() ;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(config.getDatabase().getHost(), config.getDatabase().getUser(), config.getDatabase().getPassword());
        return connection;
    }
}
