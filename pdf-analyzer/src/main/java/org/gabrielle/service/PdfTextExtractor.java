package org.gabrielle.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.nio.file.Path;

public class PdfTextExtractor {

    public String extractText(Path filePath) {

        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null");
        }

        try (PDDocument pdDocument = PDDocument.load(filePath.toFile())) {

            PDFTextStripper textStripper = new PDFTextStripper();

            textStripper.setSortByPosition(true);
            textStripper.setStartPage(1);
            textStripper.setEndPage(pdDocument.getNumberOfPages());

            return textStripper.getText(pdDocument);

        } catch (IOException exception) {
            throw new RuntimeException( "Error extracting text from PDF: " + filePath, exception);
        }
    }
}
