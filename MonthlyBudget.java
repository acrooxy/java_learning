import java.util.ArrayList;
import java.util.Scanner;

public class MonthlyBudget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> expenseNames = new ArrayList<>();
        ArrayList<Double> expenseAmounts = new ArrayList<>();

        System.out.print("Enter your monthly budget: ");
        double budget;

        try {
            budget = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
            scanner.close();
            return;
        }

        int choice = 0;

        while (choice != 4) {
            System.out.println("\nMonthly Budget");
            System.out.println("1. Add an expense");
            System.out.println("2. View expenses");
            System.out.println("3. Show remaining budget");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter the expense name: ");
                    String expenseName = scanner.nextLine();

                    System.out.print("Enter the expense amount: ");

                    try {
                        double expenseAmount = Double.parseDouble(scanner.nextLine());

                        if (expenseAmount > 0) {
                            expenseNames.add(expenseName);
                            expenseAmounts.add(expenseAmount);
                            System.out.println("Expense added.");
                        } else {
                            System.out.println("The amount must be more than 0.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid amount.");
                    }
                    break;

                case 2:
                    if (expenseNames.size() == 0) {
                        System.out.println("There are no expenses yet.");
                    } else {
                        for (int i = 0; i < expenseNames.size(); i++) {
                            System.out.println((i + 1) + ". " + expenseNames.get(i)
                                    + " - " + expenseAmounts.get(i));
                        }
                    }
                    break;

                case 3:
                    double totalSpent = 0;

                    for (double amount : expenseAmounts) {
                        totalSpent = totalSpent + amount;
                    }

                    double remainingBudget = budget - totalSpent;
                    System.out.println("Budget: " + budget);
                    System.out.println("Spent: " + totalSpent);
                    System.out.println("Remaining: " + remainingBudget);
                    break;

                case 4:
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println("Choose an option from 1 to 4.");
            }
        }

        scanner.close();
    }
}
