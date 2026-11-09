package src;

import java.util.Random;
import java.util.Scanner;

public class HomeActivity8NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("Number Guessing Game");
        System.out.println("I am thinking of a number from 1 to 100.");

        while (!guessedCorrectly) {
            System.out.print("Enter your guess: ");
            String input = scanner.nextLine();

            try {
                int guess = Integer.parseInt(input);
                attempts++;

                if (guess < 1 || guess > 100) {
                    System.out.println("Choose a number from 1 to 100.");
                } else if (guess < secretNumber) {
                    System.out.println("Too low. Try again.");
                } else if (guess > secretNumber) {
                    System.out.println("Too high. Try again.");
                } else {
                    guessedCorrectly = true;
                    System.out.println("Correct! You guessed the number in "
                            + attempts + " attempts.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }

        scanner.close();
    }
}
