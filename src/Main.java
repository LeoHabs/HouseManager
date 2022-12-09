import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.createNewMonth();
        user.createNewExpense();
        user.createNewExpense();
        user.createNewIncomeSource();
        user.printMonthExpenses();
        user.printMonthExpenses();

    }
}