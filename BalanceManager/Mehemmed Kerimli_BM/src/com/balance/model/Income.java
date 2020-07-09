package com.balance.model;


public class Income {
    private int id;
    private double amount;
    private String reg_date;
    private User user;
    private IncomeCategory incomeCategory;
    private String note;

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

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IncomeCategory getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(IncomeCategory incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Income{" + "id=" + id + ", amount=" + amount + ", reg_date=" + reg_date + ", user=" + user + ", incomeCategory=" + incomeCategory + ", note=" + note + '}';
    }

   
    
}
