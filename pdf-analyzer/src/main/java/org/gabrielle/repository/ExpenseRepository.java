package org.gabrielle.repository;

import org.gabrielle.database.DatabaseConnection;
import org.gabrielle.dto.PdfData;

import java.sql.*;
import java.util.List;

public class ExpenseRepository {

    private DatabaseConnection conn;

    public ExpenseRepository(DatabaseConnection conn) {
        this.conn = conn;
    }

    public void save(List<PdfData> expenses) {

        String sql = """
                INSERT INTO expenses(name, type, value)
                VALUES(?, ?, ?)
                """;

        try (Connection connection = conn.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for(PdfData expense : expenses) {
                preparedStatement.setString(1, expense.name());
                preparedStatement.setString(2, expense.type());
                preparedStatement.setDouble(3, expense.value());
                preparedStatement.executeUpdate();
            }

            System.out.println("Expense saved successfully.");

        } catch (SQLException exception) {
            System.err.println("Error saving expense: " + exception.getMessage());
        }
    }

    public PdfData findById(int id){
        String sql = """
                SELECT * FROM expenses
                WHERE id = ?
                """;

        try (Connection connection = conn.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return new PdfData(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)
                    );
                }
            }

            System.out.println("Successfully found the ID: ");
        } catch (SQLException exception) {
            System.err.println("Error finding ID: " + exception.getMessage());
        }
        return null;
    }

    public void delete(int id) {

        PdfData expense = findById(id);

        if (expense == null) {
            System.out.println("Id not found");
            return;
        }

        String sql = """
                DELETE FROM expenses
                WHERE id = ?
                """;

       try (Connection connection = conn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

           preparedStatement.setInt(1, id);
           preparedStatement.executeUpdate();

           System.out.println("Expense deleted successfully.");

       } catch (SQLException exception) {
           System.err.println("Error deleting expense: " + exception.getMessage());
       }
    }
}
