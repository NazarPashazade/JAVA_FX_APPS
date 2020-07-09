package com.balance.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Plan {

    private int id;
    private double amount;
    private String name;
    private String begin_date;
    private String end_date;
    private User user;
    private Timestamp reg_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Plan{" + "id=" + id + ", amount=" + amount + ", name=" + name + ", begin_date=" + begin_date + ", end_date=" + end_date + ", user=" + user + '}';
    }

}
