import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private double balance;
    private boolean loggedIn;
    private double expenseObjective;
    private double effortTax;
    private double effortTaxObjective;
    private ArrayList<Month> monthsInUse = new ArrayList<>();
    private ArrayList<Expense> expenses = new ArrayList<>();
    private ArrayList<IncomeSource> incomeSources = new ArrayList<>();
    private ArrayList<Report> reports = new ArrayList<>();

    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.BLUE_BOLD);
        System.out.println("\uD835\uDE83\uD835\uDE91\uD835\uDE8E \uD835\uDE77\uD835\uDE98\uD835\uDE9E\uD835\uDE9C\uD835\uDE8E\uD835\uDE7C\uD835\uDE8A\uD835\uDE97\uD835\uDE8A\uD835\uDE90\uD835\uDE8E\uD835\uDE9B \uD835\uDEA0\uD835\uDE8E\uD835\uDE95\uD835\uDE8C\uD835\uDE98\uD835\uDE96\uD835\uDE8E\uD835\uDE9C \uD835\uDEA2\uD835\uDE98\uD835\uDE9E!");
        System.out.println(Color.RESET);
        System.out.print("\uD835\uDDD9\uD835\uDDF6\uD835\uDDFF\uD835\uDE00\uD835\uDE01 \uD835\uDDFB\uD835\uDDEE\uD835\uDDFA\uD835\uDDF2: ");
        this.firstName = scanner.next();
        System.out.print("\uD835\uDE47\uD835\uDE56\uD835\uDE68\uD835\uDE69 \uD835\uDE63\uD835\uDE56\uD835\uDE62\uD835\uDE5A: ");
        this.lastName = scanner.next();
        System.out.print("\uD835\uDDE1\uD835\uDDF2\uD835\uDE04 \uD835\uDDFD\uD835\uDDEE\uD835\uDE00\uD835\uDE00\uD835\uDE04\uD835\uDDFC\uD835\uDDFF\uD835\uDDF1:");
        this.password = scanner.next();
        System.out.print("\uD835\uDE84\uD835\uDE9C\uD835\uDE8E\uD835\uDE9B \uD835\uDE9B\uD835\uDE8E\uD835\uDE90\uD835\uDE92\uD835\uDE9C\uD835\uDE9D\uD835\uDE8E\uD835\uDE9B\uD835\uDE8E\uD835\uDE8D \uD835\uDEA0\uD835\uDE92\uD835\uDE9D\uD835\uDE91 \uD835\uDE9C\uD835\uDE9E\uD835\uDE8C\uD835\uDE8C\uD835\uDE8E\uD835\uDE9C\uD835\uDE9C, \uD835\uDEA0\uD835\uDE8E\uD835\uDE95\uD835\uDE8C\uD835\uDE98\uD835\uDE96\uD835\uDE8E");
        System.out.print(" " + this.firstName + " 🗄");
    }

    public void logIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password: ");
        if (scanner.nextLine().equals(this.password)) {
            this.loggedIn = true;
        }
    }

    public void createNewMonth() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the full name of the month");
        System.out.print("Month 📆: ");
        String monthName = scanner.next().toUpperCase();
        monthsInUse.add(Month.newMonth(monthName, Month.numberOfDays(monthName)));
        System.out.println("Month created with success🗄");
    }


    //Repensar, pois deve instanciar uma nova expense por mês por causa do boolean paid
    public void autoFillAllMonths() {
        Scanner scanner = new Scanner(System.in);
        String monthName = scanner.next();
        Month month = (Month) monthsInUse.stream().filter(e -> e.getMonthName().equals(monthName)).findFirst().stream().toList();
        ArrayList<Expense> tempExpense = (ArrayList<Expense>) expenses.stream().filter(Expense::isMonthly).toList();
        ArrayList<IncomeSource> tempIncome = (ArrayList<IncomeSource>) incomeSources.stream().filter(IncomeSource::isMonthlyIncome).toList();

        // Em cada deve instanciar uma nova expense com o mesmo nome e valor mas paid false
        for (Expense expense : tempExpense) {
            month.getDays().get(expense.getDay()).addExpense(expense);
        }

        for (IncomeSource incomeSource : tempIncome) {
            month.getDays().get(incomeSource.getDay()).addIncome(incomeSource);
        }
    }

    public void createNewExpense() {
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
            expenses.add(expense);
            break;
        }
        System.out.println("Expense created with success!🗄");
    }

    public void createNewIncomeSource() {
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
        incomeSources.add(income);
        System.out.println("Income source added with success!🗄");
    }

    public void removeExpense() {
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
                expenseIndex = scanner.nextInt() -1;
                break;
            } catch (Exception e) {
                System.out.println("Unidentified value.Try again!");
            }
        }
        try {
            month.getDays().get(day).getExpensesOfDay().remove(expenseIndex);
        }catch (Exception e){
            System.out.println("No expense found.");
        }
    }

    public void removeIncome(){
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
                incomeIndex = scanner.nextInt() -1;
                break;
            } catch (Exception e) {
                System.out.println("Unidentified value.Try again!");
            }
        }
        try {
            month.getDays().get(day).getIncomeOfDay().remove(incomeIndex);
        }catch (Exception e){
            System.out.println("No income found.");
        }
    }

    //Print methods
    public Month printMonthExpenses() {
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
                System.out.println(Color.BLUE_BOLD +"------------------------" + Color.RESET);
            }
        }
        System.out.println();
        return month;
    }

    public Month printMonthIncome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("What month?🗓: ");
        Month month = null;
        try {
            month = findMonth(scanner.next().toUpperCase());
        }catch (Exception e){
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
                System.out.println(Color.BLUE_BOLD +"--------------------------------------------------" + Color.RESET);
            }
        }
        System.out.println();
        return month;
    }

    //Reusable methods
    public Month findMonth(String month) {
        for (int i = 0; i < this.monthsInUse.size(); i++) {
            if (month.equals(this.monthsInUse.get(i).getMonthName())) {
                return this.monthsInUse.get(i);
            }
        }
        System.out.println("Month needs to be created first!");
        return null;
    }


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

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }
}
