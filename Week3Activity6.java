public class Week3Activity6 {
    public static void main(String[] args) {

        int number1 = 10;
        int number2 = 0;

        try {
            int result = number1 / number2;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: You cannot divide by zero.");
        }
    }
}
