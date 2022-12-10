package UserRelated;
import Enums.Color;
import Occurences.Expense;
import Occurences.IncomeSource;
import TimeOrganization.Month;
import java.util.ArrayList;
import java.util.Scanner;

public class UserOperations{
    private static User currentUser = UserManager.getCurrentUser();


    public static void createNewMonth() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the full name of the month");
        System.out.print("Month 📆: ");
        String monthName = scanner.next().toUpperCase();
        currentUser.getMonthsInUse().add(Month.newMonth(monthName, Month.numberOfDays(monthName)));
        System.out.println("Month created with success🗄");
    }

    public static void autoFillAllMonthsExp() {
        for (int i = 0; i < currentUser.getExpenses().size() ; i++) {
            if(currentUser.getExpenses().get(i).isMonthly()){
                String name = currentUser.getExpenses().get(i).getNameOfExpense();
                double value = currentUser.getExpenses().get(i).getValueOfExpense();
                int day = currentUser.getExpenses().get(i).getDay();
                for (int j = 0; j < currentUser.getMonthsInUse().size(); j++) {
                    if(currentUser.getExpenses().get(i).getMonthsLeft() > 0) {
                        currentUser.getMonthsInUse().get(i).getDays().get(day).addExpense(new Expense(name, value, true));
                        currentUser.getExpenses().get(i).setMonthsLeft(currentUser.getExpenses().get(i).getMonthsLeft() - 1);
                    }
                }
            }
        }
    }


    public static void removeExpense() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        Month month = printMonthExpenses();
        System.out.println();
        int day = 0;
        int expenseIndex = 0;
        while (true) {
            try {
                System.out.println("TimeOrganization.Day 📅: ");
                day = scanner.nextInt() - 1;
                System.out.println("Occurences.Expense number🗃");
                expenseIndex = scanner.nextInt() - 1;
                break;
            } catch (Exception e) {
                System.out.println("Unidentified value.Try again!");
            }
        }
        try {
            month.getDays().get(day).getExpensesOfDay().remove(expenseIndex);
        } catch (Exception e) {
            System.out.println("No expense found.");
        }
    }

    public static void removeIncome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        Month month = printMonthIncome();
        System.out.println();
        int day = 0;
        int incomeIndex = 0;
        while (true) {
            try {
                System.out.print("TimeOrganization.Day 📅: ");
                day = scanner.nextInt() - 1;
                System.out.print("Income number🗃");
                incomeIndex = scanner.nextInt() - 1;
                break;
            } catch (Exception e) {
                System.out.println("Unidentified value.Try again!");
            }
        }
        try {
            month.getDays().get(day).getIncomeOfDay().remove(incomeIndex);
        } catch (Exception e) {
            System.out.println("No income found.");
        }
    }

    public static Month printMonthExpenses() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("What month?🗓: ");
        Month month = findMonth(scanner.next().toUpperCase());
        System.out.println();
        for (int i = 0; i < month.getDays().size(); i++) {
            if (month.getDays().get(i).getExpensesOfDay().size() > 0) {
                System.out.print(Color.CYAN_BOLD);
                System.out.println("Day: " + (i + 1));
                System.out.print(Color.RESET);
            }
            for (int j = 0; j < month.getDays().get(i).getExpensesOfDay().size(); j++) {
                System.out.println((j + 1) + ": " + month.getDays().get(i).getExpensesOfDay().get(j).getNameOfExpense() + " : " + month.getDays().get(i).getExpensesOfDay().get(j).getValueOfExpense() +
                        "💶  Months left: " + month.getDays().get(i).getExpensesOfDay().get(j).getMonthsLeft());
                if (month.getDays().get(i).getExpensesOfDay().get(j).isPaid()) {
                    System.out.println(" Paid✅");
                } else {
                    System.out.println("Not paid yet❌");
                }
                System.out.println(Color.BLUE_BOLD + "------------------------" + Color.RESET);
            }
        }
        System.out.println();
        return month;
    }

    public static  Month printMonthIncome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("What month?🗓: ");
        Month month = null;
        try {
            month = findMonth(scanner.next().toUpperCase());
        } catch (Exception e) {
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < month.getDays().size(); i++) {
            if (month.getDays().get(i).getIncomeOfDay().size() > 0) {
                System.out.println(Color.CYAN_BOLD);
                System.out.println("Day: " + (i + 1));
                System.out.print(Color.RESET);
            }
            for (int j = 0; j < month.getDays().get(i).getIncomeOfDay().size(); j++) {
                System.out.println((j + 1) + ": " + month.getDays().get(i).getIncomeOfDay().get(j).getNameOfIncomeSource() + " : " + month.getDays().get(i).getIncomeOfDay().get(j).getValueofIncome()
                        + "💶");
                if (month.getDays().get(i).getIncomeOfDay().get(j).isMonthlyIncome()) {
                    System.out.println("Monthly income🗓");
                } else {
                    System.out.println("Unique income📆");
                }
                System.out.println(Color.BLUE_BOLD + "--------------------------------------------------" + Color.RESET);
            }
        }
        System.out.println();
        return month;
    }


    public static void createNewIncomeSource() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Source name🧾: ");
        String sourceName = scanner.next();
        double value = 0;
        while (true) {
            try {
                System.out.print("Value of Income💶: ");
                scanner.nextLine();
                value = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Unidentified value.Try again!");
            }
        }
        boolean monthlyBool = false;
        while (true) {
            System.out.print("Is the income monthly(Y/N)?🗓: ");
            String monthly = scanner.next().toUpperCase();
            if (monthly.equals("Y") || monthly.equals("YES")) {
                monthlyBool = true;
            }

            if (!monthly.equals("Y") && !monthly.equals("YES") && !monthly.equals("N") && !monthly.equals("NO")) {
                System.out.println("Unidentified answer! Try again.");
                continue;
            }
            break;
        }
        IncomeSource income = new IncomeSource(sourceName, value, monthlyBool);
        Month month = null;

        while (month == null) {
            System.out.print("Month🗓: ");
            month = findMonth(scanner.next().toUpperCase());
        }
        System.out.print("Day of the month🗓: ");
        income.setDay(scanner.nextInt() - 1);
        month.getDays().get(income.getDay()).addIncome(income);
        currentUser.getIncomeSources().add(income);
        System.out.println("Income source added with success!🗄");
    }

    public static void createNewExpense() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name of expense📓: ");
        String nameExpense = scanner.next();
        double valueExpense = 0;
        while (true) {
            try {
                System.out.print("Value of expense💶: ");
                scanner.nextLine();
                valueExpense = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Unidentified value!Try again!");
            }
        }
        while (true) {
            System.out.print("Is the expense monthly?🗓: ");
            String monthly = scanner.next().toUpperCase();
            boolean monthlyBool = false;
            if (monthly.equals("Y") || monthly.equals("YES")) {
                monthlyBool = true;
            }
            Expense expense = new Expense(nameExpense, valueExpense, monthlyBool);
            if (monthly.equals("Y") || monthly.equals("YES")) {
                while (true) {
                    try {
                        System.out.print("How many months?🗓: ");
                        expense.setMonthsLeft(scanner.nextInt());
                        break;
                    } catch (Exception e) {
                        System.out.println("Unidentified value!Try again!");
                    }
                }
            }
            if (!monthly.equals("Y") && !monthly.equals("YES") && !monthly.equals("N") && !monthly.equals("NO")) {
                System.out.println("Unidentified answer! Try again.");
                continue;
            }
            Month month = null;

            while (month == null) {
                System.out.print("Month🗓: ");
                month = findMonth(scanner.next().toUpperCase());
            }
            System.out.print("Day of the month🗓: ");
            int day = scanner.nextInt() - 1;
            expense.setDay(day);
            month.getDays().get(day).addExpense(expense);
            currentUser.getExpenses().add(expense);
            break;
        }
        System.out.println("Expense created with success!🗄");
    }

    //Reusable
    public static Month findMonth(String month) {
        for (int i = 0; i < currentUser.getMonthsInUse().size(); i++) {
            if (month.equals(currentUser.getMonthsInUse().get(i).getMonthName())) {
                return currentUser.getMonthsInUse().get(i);
            }
        }
        System.out.println("TimeOrganization.Month needs to be created first!");
        return null;
    }


}
