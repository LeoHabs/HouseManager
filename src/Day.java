import java.util.ArrayList;
public class Day {
    private int day;
    private ArrayList<Expense> expensesOfDay= new ArrayList<>();
    private ArrayList<IncomeSource> incomeOfDay = new ArrayList<>();

    public Day(int day) {
        this.day = day;
    }

    public void addExpense(Expense newExpense){
        expensesOfDay.add(newExpense);
    }

    public void addIncome(IncomeSource newIncome){
        incomeOfDay.add(newIncome);
    }
    public double getBalance() {
      return incomeOfDay.stream().mapToDouble(e -> e.getValueofIncome()).sum() - expensesOfDay.stream().mapToDouble(e ->e.getValueOfExpense()).sum();
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public ArrayList<Expense> getExpense() {
        return expensesOfDay;
    }

    public ArrayList<IncomeSource> getIncome() {
        return incomeOfDay;
    }

    public void setIncome(ArrayList<IncomeSource> income) {
        this.incomeOfDay = income;
    }

    public ArrayList<Expense> getExpensesOfDay() {
        return expensesOfDay;
    }

    public void setExpensesOfDay(ArrayList<Expense> expensesOfDay) {
        this.expensesOfDay = expensesOfDay;
    }

    public ArrayList<IncomeSource> getIncomeOfDay() {
        return incomeOfDay;
    }

    public void setIncomeOfDay(ArrayList<IncomeSource> incomeOfDay) {
        this.incomeOfDay = incomeOfDay;
    }
}
