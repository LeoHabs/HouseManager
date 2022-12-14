package UserRelated;

import Enums.Color;
import Enums.ExpenseCategories;
import Enums.ExpenseTypeRule50;
import Occurences.Expense;
import Occurences.IncomeSource;
import TimeOrganization.Month;

import java.util.ArrayList;
import java.util.Scanner;

public class UserOperations {
    private static User currentUser = UserManager.getCurrentUser();


    public static void monthsCreatedCheck(){
        if(currentUser.getMonthsInUse().size() == 0){
            System.out.println(Color.BLUE_BOLD + "DON'T FORGET TO CREATE YOUR CALENDAR FIRST" + Color. RESET);
        }
    }

    public static void payExpense() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        Month month = printMonthExpenses();
        System.out.println();
        int day = 0;
        int expenseIndex = 0;
        while (true) {
            try {
                System.out.println("Day 📅: ");
                day = scanner.nextInt() - 1;
                System.out.println("Expense number🗃");
                expenseIndex = scanner.nextInt() - 1;
                break;
            } catch (Exception e) {
                System.out.println("Unidentified value.Try again!");
            }
        }
        try {
            month.getDays().get(day).getExpensesOfDay().get(expenseIndex).setPaid(true);
        } catch (Exception e) {
            System.out.println("No expense found.");
        }
    }

    public static void defineBalanceObjective() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Objective 🎯: ");
        try {
            currentUser.setExpenseObjective(scanner.nextDouble());
        } catch (Exception e) {
            System.out.println("Unidentified value.Try again!");
        }
    }

    public static void printExpensesByCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Month🗓: ");
        Month month = findMonth(scanner.next().toUpperCase());
        if (month == null) {
            System.out.println("Month was not found");
            return;
        }
        for (int i = 0; i < ExpenseCategories.values().length; i++) {
            System.out.println(Color.GREEN_BOLD_BRIGHT + ExpenseCategories.values()[i].name() + " : " + Color.RESET);
            int finalI = i;
            month.getExpensesOfMonth()
                    .stream()
                    .filter(e -> e.getCategoryOfExpense().name().equals(ExpenseCategories.values()[finalI].name()))
                    .forEach(e -> System.out.println(e.getNameOfExpense() + " - " + e.getValueOfExpense() + " 💶"));
        }
    }

    public static void createAllYear() {
        if (currentUser.getMonthsInUse().size() > 0) {
            System.out.println(Color.BLUE_BOLD + "To avoid conflict, it is only possible to create the all year if you have not created any month before!" + Color.RESET);
            return;
        }
        currentUser.setMonthsInUse(Month.createAllYear());
        System.out.println("Full year created with success!");
    }

    public static void effortTaxAlarm() {
        System.out.println();
        ArrayList<Month> monthsOverLimit = new ArrayList<>();
        for (int i = 0; i < currentUser.getMonthsInUse().size(); i++) {
            if (!safeEffortTax(currentUser.getMonthsInUse().get(i).getExpensesOfMonth(), currentUser.getMonthsInUse().get(i).getIncomeSourcesOfMonth()))
                monthsOverLimit.add(currentUser.getMonthsInUse().get(i));
        }
        if (monthsOverLimit.size() > 0) {
            System.out.println(Color.RED_UNDERLINED + "⚠️The current months are over the effort tax defined!⚠️:" + Color.RESET);
            for (Month month : monthsOverLimit) {
                System.out.println(Color.YELLOW_BOLD + month.getMonthName() + Color.RESET);
            }
        }
    }

    public static void defineEffortTax() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.BLUE_BOLD + "TIP" + Color.RESET + Color.BLUE_UNDERLINED +
                "We recommend you to aim for a effort tax below 40% at most!" + Color.RESET);
        System.out.println();
        while (true) {
            try {
                System.out.print("Effort tax objective🎯:");
                currentUser.setEffortTax(scanner.nextDouble());
                System.out.println("Tax defined with success!✅");
                break;
            } catch (Exception e) {
                System.out.println("Unidentified value.Try again!");
            }
        }

    }

    public static void createNewMonth() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the full name of the month");
        System.out.print("Month 📆: ");
        String monthName = scanner.next().toUpperCase();
        currentUser.getMonthsInUse().add(Month.newMonth(monthName, Month.numberOfDays(monthName)));
        System.out.println("Month created with success🗄");
    }

    public static void autoFillAllMonthsExp(Expense expense) {
        int day = expense.getDay();
        for (int i = 0; i < currentUser.getMonthsInUse().size() && expense.getMonthsLeft() > 0; i++) {
            if (day > currentUser.getMonthsInUse().get(i).getDays().size()) {
                continue;
            }
            currentUser.getMonthsInUse().get(i).addExpensesOfMonth(expense);
            currentUser.getMonthsInUse().get(i).getDays().get(day).addExpense(new Expense(expense.getNameOfExpense(), expense.getValueOfExpense(), expense.isMonthly()));
            expense.setMonthsLeft(expense.getMonthsLeft() - 1);
        }
    }

    public static void removeExpense() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        Month month = printMonthExpenses();
        if (month == null){
            System.out.println("Month not found");
            return;
        }
        System.out.println();
        int day = 0;
        int expenseIndex = 0;
        while (true) {
            try {
                System.out.println("Day 📅: ");
                day = scanner.nextInt() - 1;
                System.out.println("Expense number🗃");
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
                System.out.print("Day 📅: ");
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
        boolean empty = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("What month?🗓: ");
        Month month = findMonth(scanner.next().toUpperCase());
        System.out.println();
        if(month == null){
            return null;
        }
        for (int i = 0; i < month.getDays().size(); i++) {
            if (month.getDays().get(i).getExpensesOfDay().size() > 0) {
                empty = false;
                System.out.print(Color.CYAN_BOLD);
                System.out.println("Day: " + (i + 1));
                System.out.print(Color.RESET);
            }
            for (int j = 0; j < month.getDays().get(i).getExpensesOfDay().size(); j++) {
                System.out.println((j + 1) + ": " + month.getDays().get(i).getExpensesOfDay().get(j).getNameOfExpense() + " : " + month.getDays().get(i).getExpensesOfDay().get(j).getValueOfExpense() + "💶");
                if (month.getDays().get(i).getExpensesOfDay().get(j).isPaid()) {
                    System.out.println(" Paid✅");
                } else {
                    System.out.println("Not paid yet❌");
                }
                System.out.println(Color.BLUE_BOLD + "------------------------" + Color.RESET);
            }
        }
        if (empty) {
            System.out.println("Nothing to show in " + month.getMonthName());
        }
        System.out.println();
        return month;
    }

    public static Month printMonthIncome() {
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
            if (month == null) {
                System.out.print("Go back? (Y/N): ");
                if (scanner.next().equalsIgnoreCase("Y")) {
                    return;
                }
            }
        }

        System.out.print("Day of the month🗓: ");
        try {
            income.setDay(scanner.nextInt() - 1);
        } catch (Exception e){
            System.out.println("Unidentified value");
            return;
        }
        month.getDays().get(income.getDay()).addIncome(income);
        month.addIncomeSourcesOfMonth(income);
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
                        scanner.nextLine();
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

            if (!expense.isMonthly()) {
                while (month == null) {
                    System.out.print("Month🗓: ");
                    month = findMonth(scanner.next().toUpperCase());
                    if (month == null) {
                        System.out.print("Go back? (Y/N): ");
                        if (scanner.next().equalsIgnoreCase("Y")) {
                            return;
                        }
                    }
                }
            }
            System.out.print("Day of the month🗓: ");
            int day = 0;
            try {
                day = scanner.nextInt() - 1;
            }catch (Exception e){
                System.out.println("Unidentified value!");
                return;
            }
            expense.setDay(day);
            if (!expense.isMonthly()) {
                month.addExpensesOfMonth(expense);
                month.getDays().get(day).addExpense(expense);
            } else {
                autoFillAllMonthsExp(expense);
            }
            System.out.println("Choose a category 🏷: ");
            ExpenseCategories.printCategories();
            System.out.println();
            System.out.print("Category 🏷: ");
            int categoryIndex = 0;
            try{
                categoryIndex = scanner.nextInt() - 1;
            } catch (Exception e){
                System.out.println("Unidentified value!");
                return;
            }

            expense.setCategoryOfExpense(ExpenseCategories.values()[categoryIndex]);
            if (categoryIndex < 6) {
                expense.setCategoryOfRule(ExpenseTypeRule50.values()[1]);
            }
            if (categoryIndex > 6) {
                expense.setCategoryOfRule(ExpenseTypeRule50.values()[0]);
            }
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
        System.out.println("Month needs to be created first!");
        return null;
    }

    public static boolean safeEffortTax(ArrayList<Expense> expenses, ArrayList<IncomeSource> income) {
        double expenseTotal = 0;
        double incomeTotal = 0;
        for (Expense expense : expenses) {
            expenseTotal += expense.getValueOfExpense();
        }
        for (IncomeSource incomeSource : income) {
            incomeTotal += incomeSource.getValueofIncome();
        }
        if ((expenseTotal / incomeTotal) * 100 >= currentUser.getEffortTax()) {
            return false;
        }
        return true;
    }

    public static double sumMonthExpenses(Month month) {
        double sum = 0;
        for (int i = 0; i < month.getExpensesOfMonth().size(); i++) {
            sum += month.getExpensesOfMonth().get(i).getValueOfExpense();
        }
        return sum;
    }

    public static double sumMonthIncome(Month month) {
        double sum = 0;
        for (int i = 0; i < month.getIncomeSourcesOfMonth().size(); i++) {
            sum += month.getIncomeSourcesOfMonth().get(i).getValueofIncome();
        }
        return sum;
    }
}
