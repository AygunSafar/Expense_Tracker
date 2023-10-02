package model;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Income {

    private int id;
    private String name;

    private Integer amount;

    private LocalDateTime date;

    private String description;

    private IncomeCategory category;
    private User user;

    public Income(int id, String name, Integer amount, LocalDateTime date, String description, IncomeCategory category, User user) {
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

    public IncomeCategory getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Income{" +
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
