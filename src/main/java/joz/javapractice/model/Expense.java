package joz.javapractice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Expense {
    @JsonProperty("id")
    private long id;
    @JsonProperty("expenseType")
    private int expenseType;
    @JsonProperty("date")
    private String date;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("category")
    private String category;
    @JsonProperty("account")
    private String account;
    @JsonProperty("note")
    private String note;
}
