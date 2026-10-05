import java.util.Scanner;

public class HomeActivity3ForLoop {
    public static void main(String[] args) {

        Scanner userName = new Scanner(System.in);
        System.out.println("Please enter your name");
        String name = userName.nextLine();

        int[] numbers = new int[5];
        String[] subjects = new String[5];
        for(int i = 0; i < 5; i++) {
            System.out.println("Please enter your subject " + (i + 1));
            subjects[i] = userName.nextLine();
        }

        Scanner grades = new Scanner(System.in);
        System.out.println("Please enter your grades");
        for (int i = 0; i < 5; i++) {
            System.out.println("Podaj " + i + "ocene");
            grades.next();
            numbers[i] = i + 1;

        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }

    }
}
