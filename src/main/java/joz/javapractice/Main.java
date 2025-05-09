package joz.javapractice;

import joz.javapractice.model.Expense;
import joz.javapractice.utils.ExpenseDataLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("THE APP IS RUNNING!");
//        List<Expense> theExpenseList = ExpenseDataLoader.getExpenses();
//        theExpenseList.forEach(System.out::println);
    }
}