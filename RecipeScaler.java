import java.util.Scanner;

public class RecipeScaler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Recipe Scaler");
            System.out.print("How many servings does the original recipe make? ");
            int originalServings = Integer.parseInt(scanner.nextLine());

            System.out.print("How many servings do you need? ");
            int wantedServings = Integer.parseInt(scanner.nextLine());

            System.out.print("How many ingredients are in the recipe? ");
            int numberOfIngredients = Integer.parseInt(scanner.nextLine());

            if (originalServings <= 0 || wantedServings <= 0) {
                System.out.println("Servings must be more than 0.");
                return;
            }

            if (numberOfIngredients <= 0 || numberOfIngredients > 10) {
                System.out.println("Enter between 1 and 10 ingredients.");
                return;
            }

            String[] ingredientNames = new String[numberOfIngredients];
            double[] ingredientAmounts = new double[numberOfIngredients];

            for (int i = 0; i < numberOfIngredients; i++) {
                System.out.print("Enter ingredient " + (i + 1) + " name: ");
                ingredientNames[i] = scanner.nextLine();

                System.out.print("Enter its amount: ");
                ingredientAmounts[i] = Double.parseDouble(scanner.nextLine());

                if (ingredientAmounts[i] < 0) {
                    System.out.println("The amount cannot be negative.");
                    return;
                }
            }

            System.out.println("\nIngredients for " + wantedServings + " servings:");

            for (int i = 0; i < numberOfIngredients; i++) {
                double newAmount = ingredientAmounts[i] * wantedServings / originalServings;
                System.out.printf("%.2f - %s%n", newAmount, ingredientNames[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } finally {
            scanner.close();
        }
    }
}
