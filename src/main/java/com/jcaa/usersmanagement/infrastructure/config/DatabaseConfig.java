package com.jcaa.usersmanagement.infrastructure.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/7502510025_1_app_ofertas";
        String user = "root";
        String password = "TuContraseñaMaestra";
        return DriverManager.getConnection(url, user, password);
    }
}