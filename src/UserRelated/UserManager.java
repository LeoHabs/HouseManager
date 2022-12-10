package UserRelated;

import Enums.Color;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
    private static User currentUser;
    private static ArrayList<User> allUsers = new ArrayList<>();


    public static void loginProcedure() {
        System.out.println();
        System.out.println(Color.BLUE_BOLD + "LOGIN" + Color.RESET);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        User user = null;
        user = findUser(scanner.next());
        if (user == null) {
            return;
        }
        if (user.logIn()) {
            currentUser = user;
            user.setLoggedIn(true);
            System.out.println("Welcome " + user.getFirstName());
        } else {
            System.out.println(Color.RED_UNDERLINED + "Username or password is wrong");
        }

    }

    //Redirecionará para o menu inicial
    public static void logoutProcedure(){
        System.out.println("Until next time!✋");
        currentUser.setLoggedIn(false);
        currentUser = null;
    }

    public static void createNewUser() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("First Name: ");
        String firstName = scanner.next();
        System.out.print("Last Name: ");
        String lastName = scanner.next();
        System.out.print("Username: ");
        String username = scanner.next();
        String password = null;
        while (true) {
            System.out.print("New password: ");
            password = scanner.next();
            System.out.print("Repeat password: ");
            String checkPass = scanner.next();
            if (password.equals(checkPass)) {
                System.out.println(Color.BLUE_UNDERLINED +"Password created with success" +Color.RESET);
                break;
            }
            System.out.println("Passwords do not match");
        }
        allUsers.add(new User(firstName, lastName, username, password));
    }

    public static User findUser(String userName) {
        for (int i = 0; i < allUsers.size(); i++) {
            if (userName.equals(allUsers.get(i).getUsername())) {
                return allUsers.get(i);
            }
        }
        System.out.println(Color.RED_UNDERLINED + "User not found 🧐" + Color.RESET);
        return null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

}
