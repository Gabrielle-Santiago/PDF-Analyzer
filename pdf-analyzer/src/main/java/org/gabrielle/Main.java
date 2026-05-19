package org.gabrielle;

import org.gabrielle.service.PdfTextExtractor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        PdfTextExtractor extractor = new PdfTextExtractor();
        Path pdfPath = Paths.get("pdf-analyzer/pdf/text.pdf");

        if (!Files.exists(pdfPath)) {
            System.out.println("PDF file not found");
            return;
        }

        String text = extractor.extractText(pdfPath);
        System.out.println(text);
    }
}