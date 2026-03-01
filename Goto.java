import java.util.Scanner;

public class Goto{
    public static void main (String[] args) {
        System.out.println("Please input a number:");
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        boolean condition = true;
        for (int i = x; condition; i++) {
            if (i >9) {
                condition = false;
            }
            {
                System.out.println(i);
            }
        }

    }
}
