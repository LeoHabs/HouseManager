import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.createNewMonth();
        user.createNewIncomeSource();
        user.createNewIncomeSource();
        user.createNewExpense();
        user.removeIncome();
        user.printMonthIncome();
        MyGraphics.printBalanceCalendar(user.getMonthsInUse().get(0));
    }
}