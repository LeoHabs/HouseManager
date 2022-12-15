package UserRelated;
import Occurences.Expense;
import Occurences.IncomeSource;
import TimeOrganization.*;
import java.util.ArrayList;
import java.util.Scanner;


public class User {
    private String firstName;
    private String lastName;

    private String username;
    private String password;
    private double balance;
    private boolean loggedIn;
    private double expenseObjective = 0;
    private double effortTax = 40;
    private double effortTaxObjective;
    private ArrayList<Month> monthsInUse = new ArrayList<>();
    private ArrayList<Expense> expenses = new ArrayList<>();
    private ArrayList<IncomeSource> incomeSources = new ArrayList<>();

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public boolean logIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password: ");
        if (scanner.nextLine().equals(this.password)) {
            this.loggedIn = true;
            return true;
        }
        return false;
    }


    //Print methods

    //Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public double getExpenseObjective() {
        return expenseObjective;
    }

    public void setExpenseObjective(double expenseObjective) {
        this.expenseObjective = expenseObjective;
    }

    public double getEffortTax() {
        return effortTax;
    }

    public void setEffortTax(double effortTax) {
        this.effortTax = effortTax;
    }

    public double getEffortTaxObjective() {
        return effortTaxObjective;
    }

    public void setEffortTaxObjective(double effortTaxObjective) {
        this.effortTaxObjective = effortTaxObjective;
    }

    public ArrayList<Month> getMonthsInUse() {
        return monthsInUse;
    }

    public void setMonthsInUse(ArrayList<Month> monthsInUse) {
        this.monthsInUse = monthsInUse;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public ArrayList<IncomeSource> getIncomeSources() {
        return incomeSources;
    }

    public void setIncomeSources(ArrayList<IncomeSource> incomeSources) {
        this.incomeSources = incomeSources;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
