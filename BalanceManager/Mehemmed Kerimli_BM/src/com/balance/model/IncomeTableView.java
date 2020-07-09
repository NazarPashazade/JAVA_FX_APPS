package com.balance.model;

public class IncomeTableView {

    private int id;
    private double amount;
    private String reg_date;
    private String incomeCategory;
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
        reg_date = reg_date.substring(0, 10);
        this.reg_date = reg_date;
    }

    public String getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(String incomeCategory) {
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
        return "IncomeTableView{" + "id=" + id + ", amount=" + amount + ", reg_date=" + reg_date + ", incomeCategory=" + incomeCategory + ", note=" + note + '}';
    }
}
