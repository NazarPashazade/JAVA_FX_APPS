package com.balance.model;

public class Balance {
    private int id;
    private double balance;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Balance{" + "id=" + id + ", balance=" + balance + ", user=" + user + '}';
    }
}
