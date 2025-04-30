package joz.javapractice.service;

import joz.javapractice.model.Expense;

import java.util.List;

public interface ExpenseService {
    List<Expense> getExpenseByDay(String date);
    List<Expense> getExpenseByCategoryAndMonth(String category, String month);
    List<String> getAllExpenseCategories();
}
