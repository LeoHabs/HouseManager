package Occurences;

import Enums.ExpenseCategories;
import Enums.ExpenseTypeRule50;

public class Expense {
    private String nameOfExpense;
    private double valueOfExpense;

    private int monthsLeft;

    private boolean monthly;

    private boolean paid;

    private int day;

    private ExpenseCategories categoryOfExpense;

    private ExpenseTypeRule50 rule50Category;

    public Expense(String nameOfExpense, double valueOfExpense, boolean monthly) {
        this.nameOfExpense = nameOfExpense;
        this.valueOfExpense = valueOfExpense;
        this.monthly = monthly;
        this.paid = false;
    }



    public void pay(){
            this.paid = true;
            System.out.println("Occurences.Expense marked as paid ✅");
    }

    public String getNameOfExpense() {
        return nameOfExpense;
    }

    public void setNameOfExpense(String nameOfExpense) {
        this.nameOfExpense = nameOfExpense;
    }

    public double getValueOfExpense() {
        return valueOfExpense;
    }

    public void setValueOfExpense(double valueOfExpense) {
        this.valueOfExpense = valueOfExpense;
    }

    public int getMonthsLeft() {
        return monthsLeft;
    }

    public void setMonthsLeft(int monthsLeft) {
        this.monthsLeft = monthsLeft;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public ExpenseCategories getCategoryOfExpense() {
        return categoryOfExpense;
    }

    public void setCategoryOfExpense(ExpenseCategories categoryOfExpense) {
        this.categoryOfExpense = categoryOfExpense;
    }

    public ExpenseTypeRule50 getRule50Category() {
        return rule50Category;
    }

    public void setCategoryOfRule(ExpenseTypeRule50 category){
        this.rule50Category = category;
    }
}
