package com.edward.project;

/**
 * @author Neuron-to-Opens
 * @Description
 * @create 2025-03-20 2:45
 * @ProjectName com.edward.project
 */
public class DatabaseConfig {
    private Database database;

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public static class Database {
        private String host;
        private String user;
        private String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
