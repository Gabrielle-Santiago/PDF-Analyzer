package org.gabrielle.database;

import java.sql.*;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:pdf-analyzer/src/main/java/org/gabrielle/database/expenses.db";

    public static void connect() {

        String sql = """
                CREATE TABLE IF NOT EXISTS expenses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    type TEXT NOT NULL,
                    value REAL NOT NULL
                )
                """;

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(sql);
            System.out.println("Database connected successfully.");

        } catch (SQLException exception) {
            System.err.println("Error connecting database: " + exception.getMessage());
        }
    }
}
