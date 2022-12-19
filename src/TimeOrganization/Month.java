package TimeOrganization;

import Occurences.Expense;
import Occurences.IncomeSource;

import java.util.ArrayList;

public class Month {
    private String monthName;


    private ArrayList<Expense> expensesOfMonth = new ArrayList<>();

    private ArrayList<IncomeSource> incomeSourcesOfMonth = new ArrayList<>();
    private ArrayList<Day> days = new ArrayList<>();

    public Month(String monthName, ArrayList<Day> days) {
        this.monthName = monthName;
        this.days = days;
    }

    public double sumIncome(){
        return incomeSourcesOfMonth.stream().mapToDouble(n -> n.getValueofIncome()).sum();
    }
    public double sumExpenses(){
       return expensesOfMonth.stream().mapToDouble(n-> n.getValueOfExpense()).sum();
    }
    public static ArrayList<Month> createAllYear(){
        ArrayList<Month> allYear= new ArrayList<>();
        allYear.add(newMonth("JANUARY",31));
        allYear.add(newMonth("FEBRUARY",28));
        allYear.add(newMonth("MARCH",31));
        allYear.add(newMonth("APRIL",30));
        allYear.add(newMonth("MAY",31));
        allYear.add(newMonth("JUNE",30));
        allYear.add(newMonth("JULY",31));
        allYear.add(newMonth("AUGUST",31));
        allYear.add(newMonth("SEPTEMBER",30));
        allYear.add(newMonth("OCTOBER",31));
        allYear.add(newMonth("NOVEMBER",30));
        allYear.add(newMonth("DECEMBER",31));

        return allYear;
    }

    public static Month newMonth(String monthName, int numberOfDays){
        ArrayList<Day> days = new ArrayList<>();
        for (int i = 0; i <numberOfDays; i++) {
            Day day = new Day(i+1);
            days.add(day);
        }
        return new Month(monthName,days);
    }

    public static int numberOfDays(String monthName) {
        switch (monthName) {
            case "JANUARY":
            case "MARCH":
            case "MAY":
            case "JULY":
            case "AUGUST":
            case "OCTOBER":
            case "DECEMBER":
                return 31;
            case "APRIL":
            case "JUNE":
            case "SEPTEMBER":
            case "NOVEMBER":
                return 30;
            case "FEBRUARY":
                return 28;
            default:
                return 0;
        }
    }

    public String getMonthName() {
        return monthName;
    }


    public ArrayList<Day> getDays() {
        return days;
    }

    public ArrayList<Expense> getExpensesOfMonth() {
        return expensesOfMonth;
    }

    public void addExpensesOfMonth(Expense expenseOfMonth) {
        this.expensesOfMonth.add(expenseOfMonth);
    }

    public ArrayList<IncomeSource> getIncomeSourcesOfMonth() {
        return incomeSourcesOfMonth;
    }

    public void addIncomeSourcesOfMonth(IncomeSource incomeSource) {
        this.incomeSourcesOfMonth.add(incomeSource);
    }
}
