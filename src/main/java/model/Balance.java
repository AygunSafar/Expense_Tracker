package model;

import java.time.LocalDateTime;

public class Balance {

    private  int id;
    private Integer amount;
    private LocalDateTime date;
    private User user;

    public Balance(int id, Integer amount, LocalDateTime date, User user) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
