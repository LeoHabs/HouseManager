import UserRelated.User;
import UserRelated.UserManager;
import UserRelated.UserOperations;

public class Main {
    public static void main(String[] args) {
        UserManager.createNewUser();
        UserManager.createNewUser();
        UserManager.loginProcedure();
        UserOperations.createNewMonth();
        UserOperations.createNewMonth();
        UserOperations.createNewMonth();
        UserOperations.createNewExpense();
        UserOperations.autoFillAllMonthsExp();
        UserOperations.printMonthExpenses();
        UserOperations.printMonthExpenses();
        UserOperations.printMonthExpenses();
    }
}