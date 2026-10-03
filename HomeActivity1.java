import java.util.Scanner;

public class HomeActivity1 {
    public static void main(String[] args) {

        System.out.println("Welcome to Fibonacci number checker. Please remember max \'long\' type can hold number up to 9,223,372,036,854,775,807. ");
        int Iteration = 0;
        int iterationNo = 0;
        long num1 = 0;
        long num2 = 1;
        int outIter = 0;



        while (iterationNo < 10){
            Iteration++;
            iterationNo = iterationNo + 1;
            num1 = num1 + num2;
            num2 = num1 + num2;

            System.out.println("Iteration " + Iteration + " | Num1: " +  num1 + " | Num2: " +  num2);
            if (iterationNo ==10)  {
                System.out.println("Would you like to continue checking next 10 Fibo numbers? Y/N");
                Scanner UserChoice = new Scanner(System.in);
                if(UserChoice.next().equalsIgnoreCase("Y" )){
                    iterationNo = 0;
                    outIter++;
                } else {
                    System.out.println("Thank you, Bye");
                }
            }

        }
        System.out.println("Finished loops of the program Number: " + (outIter));

        }




    }

