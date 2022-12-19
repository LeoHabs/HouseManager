package TimeOrganization;
import Occurences.*;

import java.util.ArrayList;
public class Day {
    private int day;
    private ArrayList<Expense> expensesOfDay = new ArrayList<>();
    private ArrayList<IncomeSource> incomeOfDay = new ArrayList<>();

    public Day(int day) {
        this.day = day;
    }

    public void addExpense(Expense newExpense) {
        expensesOfDay.add(newExpense);
    }

    public void addIncome(IncomeSource newIncome) {
        incomeOfDay.add(newIncome);
    }

    public double getBalance() {
        return incomeOfDay.stream().mapToDouble(e -> e.getValueofIncome()).sum() - expensesOfDay.stream().mapToDouble(e -> e.getValueOfExpense()).sum();
    }


    public double sumOfIncome() {
        if (incomeOfDay.size() == 0) {
            return 0;
        }
        return incomeOfDay.stream().mapToDouble(e -> e.getValueofIncome()).sum();
    }

    public double sumOfExpenses() {
        return expensesOfDay.stream().mapToDouble(e -> e.getValueOfExpense()).sum();
    }


    public ArrayList<Expense> getExpensesOfDay() {
        return expensesOfDay;
    }


    public ArrayList<IncomeSource> getIncomeOfDay() {
        return incomeOfDay;
    }
}


