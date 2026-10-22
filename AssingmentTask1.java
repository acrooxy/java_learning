import java.util.Scanner;

public class AssingmentTask1 {
    public static void main(String[] args) {

        int[] studentIds = {105, 102, 108, 101, 104};

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student ID: ");
        int searchId = scanner.nextInt();

        boolean found = false;
        int comparisons = 0;

        for (int i = 0; i < studentIds.length; i++) {
            comparisons++;

            if (studentIds[i] == searchId) {
                System.out.println("Student found at index: " + i);
                found = true;
                break;
            }
        }

        if (found == false) {
            System.out.println("Student not found.");
        }

        System.out.println("Comparisons: " + comparisons);

        scanner.close();
    }
}
