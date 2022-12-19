package Enums;


public enum ExpenseCategories {
    Health("Health"),
    Home("Home"),
    Groceries("Groceries"),
    Pets("Pets"),
    Insurance("Insurance"),
    Transport("Transport"),
    Education("Education"),
    Leisure("Leisure"),
    Beauty("Beauty"),
    Shopping("Shopping");

    private String nameOfCategory;


    ExpenseCategories(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public static void printCategories() {
        System.out.print(Color.GREEN_BOLD);
        for (int i = 0; i <ExpenseCategories.values().length; i++) {
            if(i == 3 || i==6 || i== 9){
                System.out.println();
            }
            System.out.print("|"+(i+1) + "-"+ExpenseCategories.values()[i].nameOfCategory + "| ");
        }
        System.out.print(Color.RESET);
    }
}
