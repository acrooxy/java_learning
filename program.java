public @interface program {
import java.math.BigInteger;
import java.util.Scanner;

public class FactorialProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input n: ");
        int n = scanner.nextInt();

        if (n < 0 || n > 595) {
            System.out.println("Invalid number");
        } else {
            BigInteger result = BigInteger.ONE;
            int i = 2;

            while (i <= n) {
                result = result.multiply(BigInteger.valueOf(i));
                i++;
            }

            System.out.println("Result = " + result);
        }

        scanner.close();
    }
}
}
