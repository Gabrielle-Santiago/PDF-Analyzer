package org.gabrielle.service;

import org.gabrielle.dto.PdfData;

import java.util.ArrayList;
import java.util.List;

public class ExpenseParser {

    public List<PdfData> parse(String rawText) {

        List<PdfData> expenses = new ArrayList<>();
        String[] lines = rawText.split("\n");

        for (String line : lines) {

            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            if (!line.contains(";")) {
                continue;
            }

            String[] parts = line.split(";");

            if (parts.length != 3) {
                continue;
            }

            try {
                String name = parts[0].trim();
                String type = parts[1].trim();
                Double value = Double.parseDouble(parts[2].trim());

                PdfData expense = new PdfData(name, type, value);

                expenses.add(expense);

            } catch (NumberFormatException exception) {
                System.err.println("Invalid numeric value found in line: " + line);
            }
        }
        return expenses;
    }
}
