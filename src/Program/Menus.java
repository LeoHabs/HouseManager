package Program;

import Enums.Color;
import MyGraphics.MyGraphics;
import UserRelated.User;
import UserRelated.UserManager;
import UserRelated.UserOperations;

import java.util.Scanner;

public class Menus {


    public static void initialMenu() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            VisualMenus.loginMenu();
            System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
            switch (scanner.next()) {
                case "1":
                    if(UserManager.loginProcedure()){
                        mainMenu();
                    }
                    break;
                case "2":
                    UserManager.createNewUser();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Option unavailable");
                    break;
            }
        }
    }

    public static void mainMenu() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        VisualMenus.mainMenu();
        System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
        switch (scanner.next()) {
            case "1":
                occurrences();
                break;
            case "2":
                calendar();
                break;
            case "3":
                defineObjectives();
                break;
            case "4":
                dataAnalysis();
                break;
            case "5":
                VisualMenus.userInfo();
                break;
            case "6":
                UserManager.logoutProcedure();
                break;
            default:
                System.out.println("Option unavailable");
                mainMenu();
        }
    }

    public static void occurrences() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        VisualMenus.occurrences();
        System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
        switch (scanner.next()) {
            case "1":
                UserOperations.createNewExpense();
                occurrences();
                break;
            case "2":
                UserOperations.createNewIncomeSource();
                occurrences();
                break;
            case "3":
                UserOperations.printMonthExpenses();
                occurrences();
                break;
            case "4":
                UserOperations.printMonthIncome();
                occurrences();
                break;
            case "5":
                UserOperations.printExpensesByCategory();
                occurrences();
                break;
            case "6":
                UserOperations.removeExpense();
                occurrences();
                break;
            case "7":
                UserOperations.removeIncome();
                occurrences();
                break;
            case "8":
                UserOperations.payExpense();
                occurrences();
                break;
            case "9":
                mainMenu();
            default:
                System.out.println("Option unavailable");
                occurrences();
        }
    }

    public static void calendar() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        VisualMenus.calendar();
        System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
        switch (scanner.next()) {
            case "1":
                UserOperations.createAllYear();
                calendar();
                break;
            case "2":
                UserOperations.createNewMonth();
                calendar();
                break;
            case "3":
                mainMenu();
                calendar();
            default:
                System.out.println("Option unavailable");
                calendar();
        }
    }

    public static void defineObjectives() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        VisualMenus.defineObjectives();
        System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
        switch (scanner.next()) {
            case "1":
                UserOperations.defineEffortTax();
                defineObjectives();
                break;
            case "2":
                UserOperations.defineBalanceObjective();
                defineObjectives();
                break;
            case "3":
                mainMenu();
            default:
                System.out.println("Option unavailable");
                defineObjectives();
        }
    }

    public static void dataAnalysis() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        VisualMenus.dataAnalysis();
        System.out.print(Color.GREEN_BOLD + "Option:" + Color.RESET);
        switch (scanner.next()) {
            case "1":
                try {
                    System.out.print("What month🗓: ");
                    MyGraphics.printDaysWithExpCalendar(UserOperations.findMonth(scanner.next().toUpperCase()));
                } catch (Exception e) {
                    dataAnalysis();
                }
                dataAnalysis();
                break;
            case "2":
                MyGraphics.printCalendarBelowObjective();
                dataAnalysis();
                break;
            case "3":
                MyGraphics.updateBar();
                dataAnalysis();
                break;
            case "4":
                MyGraphics.printMonthsBelowObjective(UserManager.getCurrentUser());
                break;
            case "5":
                mainMenu();
                break;
            default:
                System.out.println("Option unavailable");
                dataAnalysis();
        }
    }
}
