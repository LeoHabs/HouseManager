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
    private double expenseObjective;
    private double effortTax;
    private double effortTaxObjective;
    private ArrayList<Month> monthsInUse = new ArrayList<>();
    private ArrayList<Expense> expenses = new ArrayList<>();
    private ArrayList<IncomeSource> incomeSources = new ArrayList<>();

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.expenseObjective = 800;
        this.effortTaxObjective = 40;
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


    public ArrayList<Month> getMonthsInUse() {
        return monthsInUse;
    }

    public void setMonthsInUse(ArrayList<Month> monthsInUse) {
        this.monthsInUse = monthsInUse;
    }

    public ArrayList<IncomeSource> getIncomeSources() {
        return incomeSources;
    }

    public String getUsername() {
        return username;
    }

}
