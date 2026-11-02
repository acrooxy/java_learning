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
        ArrayList<String> actionList = new ArrayList<>(); // new array with actionList name
        actionList.addAll(Arrays.asList("Search food", "Collect wood", "Build Shelter", "Rest", "Explore")); //add
        Collections.shuffle(actionList); // shuffle what's inside
        for (String action : actionList) {
            System.out.println(action);
        }
        Scanner userChoice = new Scanner(System.in);
        while (!gameOver) {
            System.out.println("\n");
            System.out.println("Energy: " + energy + "\nFood: " + food + "\nDay: " + day);
            userChoice.nextLine();


            gameOver = true;
        }


        System.out.println("Welcome to the Home Activity5!\n Island Survival V1.0");

        //Random randomAction = new Random();

        //for (int a =0; a<5; a++) {


        }

    }

