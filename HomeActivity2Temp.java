import java.util.Scanner;

public class HomeActivity2Temp {
    public static void main(String[] args) {

        Scanner UserChoice = new Scanner(System.in); // declare Scanner
        System.out.println("Welcome to the HomeActivity - Temp");
        System.out.println("Please enter your choice between \n 1. Celsius to Fahrenheit \n 2. Fahrenheit to Celsius");
        int choice = UserChoice.nextInt();
        switch(choice){
            case 1:
                System.out.println("Celsius to Fahrenheit \n What temperature would u like to convert?");
                int celsiusTemp = UserChoice.nextInt();

                int FahrenheitOutcome = 0;
                FahrenheitOutcome = celsiusTemp * 9 / 5 +32;
                System.out.println("Celsius temperature: "+ celsiusTemp +" is equals to "+ FahrenheitOutcome);
                break;


            case 2:
                System.out.println("Fahrenheit to Celsius \n What temperature would u like to convert?");
                int fahrenheitTemp = UserChoice.nextInt();
                int CelsiusOutcome = 0;
                CelsiusOutcome = (fahrenheitTemp - 32) * 5 / 9;
                System.out.println("Fahrenheit temperature: " + fahrenheitTemp + " is equals to " + CelsiusOutcome + " Celsius");
                break;

            default:
                System.out.println("Invalid choice.");
        }

        //userChoice.next() // download from keyboard
    }
}
