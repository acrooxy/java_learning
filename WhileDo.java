import java.util.Scanner;

public class WhileDo {
    public static void main(String[] args) {
        System.out.println("Please input a number:");
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();

        int i = x;

        do {
            System.out.println(i);
            i++;
        } while (i <= 9);

    }
}
