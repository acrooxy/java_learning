package src;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HomeActivity5 {
    public static void main(String args[]) {
        int energy = 100;
        int food = 3;
        int day = 1;
        boolean gameOver = false;
        int choice;
        ArrayList<String> actionList = new ArrayList<>(); // new array with actionList name
        actionList.addAll(Arrays.asList("Search food", "Collect wood", "Build Shelter", "Rest", "Explore")); //add

        for (String action : actionList) {
            System.out.println(action);
        }
        Scanner userChoice = new Scanner(System.in);
        while (!gameOver) {
            System.out.println("\n");
            System.out.println("Energy: " + energy + "\nFood: " + food + "\nDay: " + day);
            System.out.println("");

            System.out.println(userChoice + " Choice");
            while (true) {
                System.out.println("What number would you like to choose out of 1-5? ");
                try {
                    choice = Integer.parseInt((userChoice.nextLine()));

                    if (choice >=1 && choice <=5) {
                        break;
                    }
                    System.out.println("Choose a number from 1 to 5.");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a whole number.");
                }
            }
            System.out.println("You choosed number: " + choice);
            Collections.shuffle(actionList); // shuffle what's inside
            String selectedAction = actionList.get(choice - 1);
            System.out.println("Your action: " + selectedAction);


            gameOver = true;
        }


        System.out.println("Welcome to the Home Activity5!\n Island Survival V1.0");

        //Random randomAction = new Random();

        //for (int a =0; a<5; a++) {


        }

    }

