package Program;

import Enums.Color;
import Enums.ExpenseTypeRule50;
import MyGraphics.MyGraphics;
import UserRelated.User;
import UserRelated.UserManager;
import UserRelated.UserOperations;

import java.util.Scanner;

public class Menus {


    public static void initialMenu() {
        clearScreen();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            VisualMenus.loginMenu();
            System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
            switch (scanner.next()) {
                case "1":
                    clearScreen();
                    if(UserManager.loginProcedure()){
                        mainMenu();
                    }
                    break;
                case "2":
                    clearScreen();
                    UserManager.createNewUser();
                    break;
                case "3":
                    clearScreen();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Option unavailable");
                    break;
            }
        }
    }

    public static void mainMenu() {
        clearScreen();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        VisualMenus.mainMenu();
        System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
        switch (scanner.next()) {
            case "1":
                clearScreen();
                occurrences();
                break;
            case "2":
                clearScreen();
                calendar();
                break;
            case "3":
                clearScreen();
                defineObjectives();
                break;
            case "4":
                clearScreen();
                dataAnalysis();
                break;
            case "5":
                clearScreen();
                VisualMenus.userInfo();
                break;
            case "6":
                clearScreen();
                UserManager.logoutProcedure();
                break;
            default:
                System.out.println("Option unavailable");
                mainMenu();
        }
    }

    public static void occurrences() {
        clearScreen();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        VisualMenus.occurrences();
        System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
        switch (scanner.next()) {
            case "1":
                clearScreen();
                UserOperations.createNewExpense();
                occurrences();
                break;
            case "2":
                clearScreen();
                UserOperations.createNewIncomeSource();
                occurrences();
                break;
            case "3":
                clearScreen();
                UserOperations.printMonthExpenses();
                occurrences();
                break;
            case "4":
                clearScreen();
                UserOperations.printMonthIncome();
                occurrences();
                break;
            case "5":
                clearScreen();
                UserOperations.printExpensesByCategory();
                occurrences();
                break;
            case "6":
                clearScreen();
                UserOperations.removeExpense();
                occurrences();
                break;
            case "7":
                clearScreen();
                UserOperations.removeIncome();
                occurrences();
                break;
            case "8":
                clearScreen();
                UserOperations.payExpense();
                occurrences();
                break;
            case "9":
                clearScreen();
                mainMenu();
            default:
                System.out.println("Option unavailable");
                occurrences();
        }
    }

    public static void calendar() {
        clearScreen();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        VisualMenus.calendar();
        System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
        switch (scanner.next()) {
            case "1":
                clearScreen();
                UserOperations.createAllYear();
                calendar();
                break;
            case "2":
                clearScreen();
                UserOperations.createNewMonth();
                calendar();
                break;
            case "3":
                clearScreen();
                mainMenu();
                calendar();
            default:
                System.out.println("Option unavailable");
                calendar();
        }
    }

    public static void defineObjectives() {
        clearScreen();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        VisualMenus.defineObjectives();
        System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
        switch (scanner.next()) {
            case "1":
                clearScreen();
                UserOperations.defineEffortTax();
                defineObjectives();
                break;
            case "2":
                clearScreen();
                UserOperations.defineBalanceObjective();
                defineObjectives();
                break;
            case "3":
                clearScreen();
                mainMenu();
            default:
                System.out.println("Option unavailable");
                defineObjectives();
        }
    }

    public static void dataAnalysis() {
        clearScreen();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        VisualMenus.dataAnalysis();
        System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
        switch (scanner.next()) {
            case "1":
                clearScreen();
                try {
                    System.out.print("What month🗓: ");
                    MyGraphics.printDaysWithExpCalendar(UserOperations.findMonth(scanner.next().toUpperCase()));
                } catch (Exception e) {
                    dataAnalysis();
                }
                dataAnalysis();
                break;
            case "2":
                clearScreen();
                MyGraphics.printCalendarBelowObjective();
                dataAnalysis();
                break;
            case "3":
                clearScreen();
                MyGraphics.updateBar();
                dataAnalysis();
                break;
            case "4":
                clearScreen();
                MyGraphics.printMonthsBelowObjective(UserManager.getCurrentUser());
                dataAnalysis();
                break;
            case "5":
                clearScreen();
                ExpenseTypeRule50.calculate50RuleCat();
                dataAnalysis();
                break;
            case "6":
                clearScreen();
                mainMenu();
                break;
            default:
                System.out.println("Option unavailable");
                dataAnalysis();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
