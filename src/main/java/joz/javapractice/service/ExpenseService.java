package joz.javapractice.service;

import joz.javapractice.model.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    List<Expense> getAllUserExpenses(Long userId);
    List<Expense> getExpenseByDay(String date, Long userId);
    List<Expense> getAllExpenseInAMonth(String month, Long userId);
    List<Expense> getExpenseByCategoryAndMonth(String category, String month, Long userId);
    List<String> getAllExpenseCategories(Long userId);
    Optional<Expense> getExpenseById(Long id, Long userId);
    double getAverageAmountOnAMonth(int expenseType, String month, Long userId);
    Expense addExpense(Expense expense, Long userId);
    boolean updateExpense(Expense expense, Long userId);
    boolean deleteExpense(Long expense, Long userId);
}
