package com.edward.project;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @author Neuron-to-Opens
 * @Description
 * @create 2025-03-20 2:46
 * @ProjectName com.edward.project
 */
public class YamlConfigLoader {
    public static DatabaseConfig loadConfig() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = YamlConfigLoader.class.getClassLoader().getResourceAsStream("config.yaml")) {
            return yaml.loadAs(inputStream, DatabaseConfig.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load YAML configuration", e);
        }
    }
}
