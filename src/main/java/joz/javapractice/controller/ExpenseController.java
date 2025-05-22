package joz.javapractice.controller;

import joz.javapractice.model.AppUser;
import joz.javapractice.model.Expense;
import joz.javapractice.service.ExpenseService;
import joz.javapractice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @GetMapping("/expenses/day/{date}")
    public ResponseEntity<List<Expense>> getExpensesByDay(@PathVariable String date, Authentication authentication){
        String username = authentication.getName();
        AppUser user = userService.findByUsername(username);

        List<Expense> expenses = expenseService.getExpenseByDay(date, user.getId());
//        if (expenses.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//        }
        return ResponseEntity.status(HttpStatus.OK).body(expenses);
    }

    @GetMapping("/expenses/category/{category}/month")
    public ResponseEntity<List<Expense>> getExpensesByCategoryAndMonth(@PathVariable String category,
                                                                       @RequestParam String month,
                                                                       Authentication authentication){
        String username = authentication.getName();
        AppUser user = userService.findByUsername(username);

        List<Expense> expenses = expenseService.getExpenseByCategoryAndMonth(category, month, user.getId());
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/expenses/categories")
    public ResponseEntity<List<String>> getAllExpenseCategories(Authentication authentication){
        String username = authentication.getName();
        AppUser user = userService.findByUsername(username);

        List<String> categories = expenseService.getAllExpenseCategories(user.getId());
        if (categories.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/expenses/categories/month")
    public ResponseEntity<List<Expense>> getExpenseCategoriesInAMonth(@RequestParam String month,
                                                                     Authentication authentication){
        String username = authentication.getName();
        AppUser user = userService.findByUsername(username);

        List<Expense> categories = expenseService.getAllExpenseInAMonth(month, user.getId());
        if (categories.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Optional<Expense>> getExpenseById(@PathVariable Long id, Authentication authentication){
        String username = authentication.getName();
        AppUser user = userService.findByUsername(username);

        return ResponseEntity.ok(expenseService.getExpenseById(id, user.getId()));
    }

    @GetMapping("/expenses/avg_expense/{month}")
    public ResponseEntity<Double> getAverageExpenseInAMonth(@PathVariable String month, Authentication authentication){
        String username = authentication.getName();
        AppUser user = userService.findByUsername(username);

        return ResponseEntity.ok(expenseService.getAverageAmountOnAMonth(0, month, user.getId()));
    }

    @GetMapping("/expenses/avg_income/{month}")
    public ResponseEntity<Double> getAverageIncomeInAMonth(@PathVariable String month, Authentication authentication){
        String username = authentication.getName();
        AppUser user = userService.findByUsername(username);

        return ResponseEntity.ok(expenseService.getAverageAmountOnAMonth(1, month, user.getId()));
    }

    @PostMapping("/expenses")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense, Authentication authentication){
        String username = authentication.getName();
        AppUser user = userService.findByUsername(username);

        Expense newExpense = expenseService.addExpense(expense, user.getId());
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable long id,
                                                 @RequestBody Expense expense,
                                                 Authentication authentication){
        String username = authentication.getName();
        AppUser user = userService.findByUsername(username);

        expense.setId(id);
        boolean isUpdated = expenseService.updateExpense(expense, user.getId());
        if (isUpdated){
            return new ResponseEntity<>(expense, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id, Authentication authentication){
        String username = authentication.getName();
        AppUser user = userService.findByUsername(username);

        boolean isDeleted = expenseService.deleteExpense(id, user.getId());
        if (isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
