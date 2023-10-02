package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Expense {

    private int id;
    private String name;

    private Integer amount;

    private LocalDateTime date;

    private String description;

    private ExpenseCategory category;
    private User user;

    public Expense(int id, String name, Integer amount, LocalDateTime date, String description, ExpenseCategory category, User user) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", user=" + user +
                '}';
    }
}
