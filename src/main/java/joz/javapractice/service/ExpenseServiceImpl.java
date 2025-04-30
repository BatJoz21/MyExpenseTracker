package joz.javapractice.service;

import joz.javapractice.model.Expense;
import joz.javapractice.utils.ExpenseDataLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Override
    public List<Expense> getExpenseByDay(String date) {
        return ExpenseDataLoader.getExpenses().stream().filter(
                expense -> expense.getDate().equalsIgnoreCase(date)).toList();
    }

    @Override
    public List<Expense> getExpenseByCategoryAndMonth(String category, String month) {
        return ExpenseDataLoader.getExpenses().stream().filter(
                expense -> expense.getCategory().equalsIgnoreCase(category) &&
                        expense.getDate().startsWith(month)).toList();
    }

    @Override
    public List<String> getAllExpenseCategories() {
        return ExpenseDataLoader.getExpenses().stream().map(Expense::getCategory).distinct().toList();
    }
}
