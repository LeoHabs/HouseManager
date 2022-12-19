package Enums;

import Occurences.Expense;
import TimeOrganization.Month;
import UserRelated.UserOperations;

import java.util.Scanner;

public enum ExpenseTypeRule50 {
    Wants,
    Needs,
    Saves;

    // Assign each category to one of these categories automatically
    public static void calculate50RuleCat() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("What month you want to simulate?📆: ");
        Month month = UserOperations.findMonth(scanner.next().toUpperCase());
        System.out.println("You have a total of " + month.sumIncome() + " 💶 to spend in " + month.getMonthName());
        System.out.println();
        System.out.print("Amount to had to the simulation" + Color.BLUE_UNDERLINED + " (This simulates the money you count to have at the start of this month) :" + Color.RESET +" ");
        double totalIncome = month.sumIncome() - scanner.nextInt();
        double wantsValue = Math.round(month.getExpensesOfMonth().stream()
                .filter(e -> e.getRule50Category().name().equals("Wants"))
                .mapToDouble(Expense::getValueOfExpense).sum());
        double needsValue = Math.round( month.getExpensesOfMonth().stream()
                .filter(e -> e.getRule50Category().name().equals("Needs"))
                .mapToDouble(Expense::getValueOfExpense).sum());
        double saveValue = totalIncome - (wantsValue + needsValue);
        double wantsPercentage = wantsValue / totalIncome * 100;
        double needsPercentage = needsValue / totalIncome * 100;
        double savePercentage = saveValue / totalIncome * 100;

        double saveDeficit = 20 - savePercentage;
        double wantsDeficit = 30 - wantsPercentage;
        double needsDeficit = 50 - needsPercentage;
        System.out.println();
        System.out.println(Color.BLUE_BOLD + "Currently your wage is organized like: " + Color.RESET);
        System.out.println(Color.GREEN_BOLD_BRIGHT + "Where you're at " + Color.RESET + Color.RED_BOLD_BRIGHT + "----> " + "Where to aim" + Color.RESET);
        System.out.println();
        System.out.print("Needs : " + needsPercentage + "%");
        System.out.println(Color.RED_BOLD + " ----> 50%" + Color.RESET);
        System.out.print("Wants  : " + wantsPercentage + "% ");
        System.out.println(Color.RED_BOLD + " ----> 30%" + Color.RESET);
        System.out.print("Savings : " + savePercentage + "% ");
        System.out.println(Color.RED_BOLD + " ----> 20%" + Color.RESET);

        System.out.println(Color.BLUE_BOLD +  "By how much you are missing " + Color.RESET);
        System.out.print("Needs : ");
        checkBalance(needsDeficit);
        System.out.print("Wants : ");
        checkBalance(wantsDeficit);
        System.out.print("Savings : ");
        checkBalance(saveDeficit);
    }

    public static void checkBalance(double value) {
        if (value > 0) {
            System.out.println(Color.GREEN_BOLD_BRIGHT);
            System.out.println("+" + value);
            System.out.println(Color.RESET);
        }
        if (value < 0) {
            System.out.println(Color.RED_BOLD_BRIGHT);
            System.out.println(value);
            System.out.println(Color.RESET);
        }

    }
}
