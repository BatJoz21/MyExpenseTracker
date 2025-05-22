package joz.javapractice;

import joz.javapractice.model.Expense;
import joz.javapractice.utils.ExpenseDataLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        String port = System.getenv("PORT");
        System.out.println("PORT env var: " + port);
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("THE APP IS RUNNING!");
//        List<Expense> theExpenseList = ExpenseDataLoader.getExpenses();
//        theExpenseList.forEach(System.out::println);
    }

    @Bean
    public CommandLineRunner logPort(@Value("${server.port}") String port) {
        return args -> System.out.println("Spring Boot listening on port: " + port);
    }
}