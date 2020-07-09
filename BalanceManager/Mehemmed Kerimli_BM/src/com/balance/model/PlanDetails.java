package com.balance.model;

public class PlanDetails {

    private int id;
    private double amount;
    private String category;
    private User user;
    private Plan plan;

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

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "PlanDetails{" + "id=" + id + ", amount=" + amount + ", category=" + category + ", user=" + user + ", plan=" + plan + '}';
    }
    
}
