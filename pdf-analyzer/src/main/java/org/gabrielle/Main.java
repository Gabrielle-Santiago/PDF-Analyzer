package org.gabrielle;

import org.gabrielle.database.DatabaseConnection;
import org.gabrielle.dto.PdfData;
import org.gabrielle.service.ExpenseParser;
import org.gabrielle.service.PdfTextExtractor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.connect();

        PdfTextExtractor extractor = new PdfTextExtractor();
        ExpenseParser parser = new ExpenseParser();

        Path pdfPath = Paths.get("pdf-analyzer/pdf/info.pdf");

        if (!Files.exists(pdfPath)) {
            System.out.println("PDF file not found");
            return;
        }

        String text = extractor.extractText(pdfPath);

        List<PdfData> expenses = parser.parse(text);

        System.out.println(expenses);
    }
}