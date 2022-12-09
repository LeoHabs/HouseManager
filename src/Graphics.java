import java.util.ArrayList;

public class Graphics {
    private ArrayList<String> balanceBar = new ArrayList<>();

    public void createBar(int expenseTotal, int incomeTotal) {
        int total = expenseTotal + incomeTotal;
        double expensePerTen = Math.round(((double) expenseTotal / total) * 20);
        double incomePerTen = Math.round(((double) incomeTotal / total) * 20);

        for (int i = 0; i < expensePerTen; i++) {
            String singlebar = Color.RED_BOLD_BRIGHT + "◼" + Color.RESET;
            balanceBar.add(singlebar);
        }

        for (int i = 0; i < incomePerTen; i++) {
            String singlebar = Color.GREEN_BOLD_BRIGHT  + "◼" + Color.RESET;
            balanceBar.add(singlebar);
        }
    }

    public void printBar() {
        balanceBar.forEach(System.out::print);
    }

    public void printBalanceCalendar(Month month) {
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
            if (checkBalance(month.getDays().get(i))) {
                positive();
            } else {
                negative();
            }
            if(i<9){
                System.out.print(" ");
            }
            System.out.print(i+1);
            System.out.print(Color.RESET);
        }
        System.out.print(Color.BLUE_BOLD);
        System.out.print("|");
        System.out.print(Color.RESET);
    }

    public boolean checkBalance(Day day) {
        return day.getBalance() >= 0;
    }

    public void positive(){
        System.out.print(Color.GREEN_BACKGROUND);
    }

    public void negative(){
        System.out.print(Color.RED_BACKGROUND);
    }
}
