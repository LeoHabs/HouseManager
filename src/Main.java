import Enums.ExpenseCategories;
import UserRelated.User;
import UserRelated.UserManager;
import UserRelated.UserOperations;

public class Main {
    public static void main(String[] args) {
        UserManager.createNewUser();
        UserManager.loginProcedure();
        UserOperations.createAllYear();
        UserOperations.createNewExpense();
        UserOperations.createNewExpense();
        UserOperations.printExpensesByCategory();
    }
}