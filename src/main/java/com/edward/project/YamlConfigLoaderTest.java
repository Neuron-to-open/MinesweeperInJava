package com.edward.project;

/**
 * @author Neuron-to-Opens
 * @Description
 * @create 2025-03-20 3:20
 * @ProjectName com.edward.project
 */

public class YamlConfigLoaderTest {
    public static void main(String[] args) {
        try {
            DatabaseConfig config = YamlConfigLoader.loadConfig();
            System.out.println("URL: " + config.getDatabase().getHost());
            System.out.println("User: " + config.getDatabase().getUser());
            System.out.println("Password: " + config.getDatabase().getUser());
        } catch (Exception e) {
            System.out.println("Failed to load configuration.");
            e.printStackTrace();
        }
    }
}
