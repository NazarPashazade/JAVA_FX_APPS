package com.balance.model;

public class IncomeCategory {

    private int id;
    private String category;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "IncomeCategory{" + "id=" + id + ", category=" + category + ", user=" + user + '}';
    }
}
