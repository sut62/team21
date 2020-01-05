package se.g21.backend.recordexpensesystem.controllers;
import se.g21.backend.recordexpensesystem.entities.ExpenseType;
import se.g21.backend.recordexpensesystem.repository.ExpenseTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ExpenseTypeController {

    @Autowired
    private final ExpenseTypeRepository expenseTypeRepository;

    public ExpenseTypeController(ExpenseTypeRepository expenseTypeRepository) {
        this.expenseTypeRepository = expenseTypeRepository;
    }

    @GetMapping("/expenseType/")
    public Collection<ExpenseType> ExpenseType() {
        return expenseTypeRepository.findAll().stream().collect(Collectors.toList());
    }

}
