
package com.balance.model;

public class ExpenseTableView {
    private int id;
    private double amount;
    private String reg_date;
    private String expenseCategory;
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

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ExpenseTableView{" + "id=" + id + ", amount=" + amount + ", reg_date=" + reg_date + ", expenseCategory=" + expenseCategory + ", note=" + note + '}';
    }
    
}
