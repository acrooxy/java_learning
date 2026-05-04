// package JavaPrograms;

public class factorial
{
    // Recursive method to calculate factorial
    public static int factorial(int n)
    {
        // Base case: if n is 1, return 1
        if (n == 1)
        {
            return 1;
        }

        // Recursive case: n * factorial(n-1)
        return n * factorial(n - 1);
    }

    public static void main(String[] args)
    {
        int number = 5;
        int result = factorial(number);
        System.out.println("Factorial of " + number + " is: " + result); // Output: 120
    }
}

// Find recursive without recursive method
// convert farenhait to celcius
//greatest common divisor 
