package org.gabrielle.service;

import org.gabrielle.database.DatabaseConnection;
import org.gabrielle.dto.PdfData;
import org.gabrielle.repository.ExpenseRepository;

import java.util.List;

public class ExpenseService {
    ExpenseRepository repository = new ExpenseRepository(new DatabaseConnection());

    public void save(List<PdfData> expense) {
        repository.save(expense);
    }
    public void delete(int id) {
        repository.delete(id);
    }
}
