package MyGraphics;

import Enums.Color;
import TimeOrganization.Day;
import TimeOrganization.Month;
import UserRelated.User;
import UserRelated.UserOperations;

import java.util.ArrayList;
import java.util.Scanner;

public class MyGraphics {
    private static ArrayList<String> balanceBar = new ArrayList<>();

    public static void updateBar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What month?🗓: ");
        Month month = UserOperations.findMonth(scanner.next().toUpperCase());
        if (month == null){
            return;
        }
        double expenseTotal = UserOperations.sumMonthExpenses(month);
        double incomeTotal = UserOperations.sumMonthIncome(month);
        double total = expenseTotal + incomeTotal;
        double expensePerTen = Math.round(( expenseTotal / total) * 20);
        double incomePerTen = Math.round(( incomeTotal / total) * 20);

        for (int i = 0; i < expensePerTen; i++) {
            String singlebar = Color.RED_BOLD_BRIGHT + "◼" + Color.RESET;
            balanceBar.add(singlebar);
        }

        for (int i = 0; i < incomePerTen; i++) {
            String singlebar = Color.GREEN_BOLD_BRIGHT + "◼" + Color.RESET;
            balanceBar.add(singlebar);
        }
    }

    public static void printBar() {
        balanceBar.forEach(System.out::print);
    }

    public static void printDaysWithExpCalendar(Month month) {
        for (int i = 0; i < month.getDays().size(); i++) {
            if (i == 7 || i == 14 || i == 21 || i == 28) {
                System.out.print(Color.BLUE_BOLD);
                System.out.print("|");
                System.out.print(Color.RESET);
                System.out.print(Color.BLUE_BOLD);
                System.out.println();
                System.out.println("----------------------");
                System.out.print(Color.RESET);
            }
            System.out.print(Color.BLUE_BOLD);
            System.out.print("|");
            System.out.print(Color.RESET);
            if (month.getDays().get(i).getExpensesOfDay().size() == 0) {
                positive();
            } else {
                negative();
            }
            if (i < 9) {
                System.out.print(" ");
            }
            System.out.print(i + 1);
            System.out.print(Color.RESET);
        }
        System.out.print(Color.BLUE_BOLD);
        System.out.print("|");
        System.out.print(Color.RESET);
    }
    public static void printCalendarBelowObjective() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("What month? 🗓: ");
        Month month = UserOperations.findMonth(scanner.next().toUpperCase());
        System.out.println("What will be the initial balance? 💵");
        double initialBalance = scanner.nextDouble();
        System.out.println(Color.BLUE_BOLD + "Green means the balance is positive on green days and red negative on red days");
        System.out.println("TIP: Try to organize expenses for after the days you get green or altering the income to the start of red periods" + Color.RESET);
        System.out.println();
        System.out.println(Color.BLUE_BOLD + month.getMonthName() + ": " + Color.RESET);
        for (int i = 0; i < month.getDays().size(); i++) {
            initialBalance += month.getDays().get(i).sumOfIncome();
            initialBalance -= month.getDays().get(i).sumOfExpenses();
            if (i == 7 || i == 14 || i == 21 || i == 28) {
                System.out.print(Color.BLUE_BOLD);
                System.out.print("|");
                System.out.print(Color.RESET);
                System.out.print(Color.BLUE_BOLD);
                System.out.println();
                System.out.println("----------------------");
                System.out.print(Color.RESET);
            }
            System.out.print(Color.BLUE_BOLD);
            System.out.print("|");
            System.out.print(Color.RESET);
            if (initialBalance >= 0) {
                positive();
            } else {
                negative();
            }
            if (i < 9) {
                System.out.print(" ");
            }
            System.out.print(i + 1);
            System.out.print(Color.RESET);
        }
    }

    public static boolean checkBalance(Day day) {
        return day.getBalance() >= 0;
    }

    public static void positive() {
        System.out.print(Color.GREEN_BACKGROUND);
    }

    public static void negative() {
        System.out.print(Color.RED_BACKGROUND);
    }

    public static void printMonthsBelowObjective(User user){
        System.out.println();
        System.out.println(Color.RED_UNDERLINED + "⚠️This months are above the expense total objective set by you!⚠️:" + Color.RESET);
        for (int i = 0; i < user.getMonthsInUse().size(); i++) {
            if(user.getMonthsInUse().get(i).sumExpenses() > user.getExpenseObjective()){
                System.out.println(Color.RED_BOLD_BRIGHT + user.getMonthsInUse().get(i).getMonthName() + Color.RESET);
            }

        }
    }
}
