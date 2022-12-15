package Program;

import Enums.Color;
import UserRelated.UserManager;
import UserRelated.UserOperations;

public class VisualMenus {
    public static void loginMenu() {
        System.out.println(Color.CYAN_BOLD);
        System.out.println("======HᴏᴜsᴇMᴀɴᴀɢᴇʀ======");
        System.out.println("|1- 🗂 Login");
        System.out.println("|2- 🗄 Create new user");
        System.out.println("|3- 🔙 Exit");
    }

    public static void mainMenu() {
        System.out.println(Color.CYAN_BOLD);
        System.out.println("===============Main Menu==============");
        System.out.println("|1- 🗃 Expenses/Income Sources");
        System.out.println("|2- 🗓 Calendar Creation");
        System.out.println("|3- 🎯 Define objectives");
        System.out.println("|4- 📊 Data analysis");
        System.out.println("|5- 📓 My Profile");
        System.out.println("|5- ✋ Logout");
        System.out.println();
        UserOperations.effortTaxAlarm();
    }

    //Expenses and Income menu
    public static void occurrences() {
        System.out.println(Color.CYAN_BOLD);
        System.out.println("==========Expenses/Income Sources==========");
        System.out.println("|1- 💸 Create new expense");
        System.out.println("|2- 💰 Create new income");
        System.out.println("|3- 📆 Check expenses by month");
        System.out.println("|4- 📆 Check income by month");
        System.out.println("|5- 🏷 Check expenses by category");
        System.out.println("|6- 📤 Remove expense");
        System.out.println("|7- 📤 Remove income source");
        System.out.println("|8- ✅ Mark expense as payed");
        System.out.println("|9- 🔙 Exit");
    }

    public static void calendar() {
        System.out.println(Color.CYAN_BOLD);
        System.out.println("===============Calendar Creation===============");
        System.out.println(Color.BLUE_UNDERLINED + "|Note: You can either create a all year or the months that interest you!" + Color.RESET);
        System.out.println("|1- 🗒 Create all year");
        System.out.println("|2- 📆 Create a particular month");
        System.out.println("|3- 🔙 Exit");
    }

    public static void defineObjectives() {
        System.out.println(Color.CYAN_BOLD);
        System.out.println("===========Define Objectives===========");
        System.out.println("|1- 📌 Define effort tax objective");
        System.out.println("|2- 📍 Define expense objective");
        System.out.println("|3- 🔙 Exit");
    }

    public static void dataAnalysis() {
        System.out.println(Color.CYAN_BOLD);
        System.out.println("===========Data Analysis===========");
        System.out.println("|1- 📆 Calendar with expense days");
        System.out.println("|2- 📆 Calendar with month balance simulation");
        System.out.println("|3- 📊 Bar to compare income and expenses in a month");
        System.out.println("|4- 🗃 Months above expense objective");
        System.out.println("|5- 🗂 50/40/30 Rule Simulation");
        System.out.println("|6- 🔙 Exit");
    }

    public static void userInfo() {
        System.out.println(Color.CYAN_BOLD);
        System.out.println("📓 " + UserManager.getCurrentUser().getFirstName() + " " + UserManager.getCurrentUser().getLastName());
        System.out.println("Username ✒️: " + UserManager.getCurrentUser().getUsername());
        System.out.println("Objectives 🎯:");
        System.out.println("    |Current monthly expense objective: " + UserManager.getCurrentUser().getExpenseObjective());
        System.out.println("    |Current monthly effort tax objective: "+ UserManager.getCurrentUser().getEffortTaxObjective());
        System.out.println("Active months: " + Color.GREEN_BOLD);
        UserManager.getCurrentUser().getMonthsInUse().forEach(e-> System.out.println(e.getMonthName()));
    }
}
