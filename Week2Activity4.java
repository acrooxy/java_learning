import java.util.Scanner;

public class Week2Activity4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Choose a task number from 1 to 4:");
        System.out.println("1 - Searching algorithms");
        System.out.println("2 - Stack operations");
        System.out.println("3 - Binary tree operation");
        System.out.println("4 - Hash function");

        int taskNumber = input.nextInt();

        switch (taskNumber) {
            case 1:
                System.out.println("Searching algorithms");
                break;
            case 2:
                System.out.println("Stack operations");
                break;
            case 3:
                System.out.println("Binary tree operation");
                break;
            case 4:
                System.out.println("Hash function");
                break;
            default:
                System.out.println("Invalid task number. Please enter a number from 1 to 4.");
        }

        input.close();
    }
}
