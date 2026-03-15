public class StudentGradingSystem
{
    // Method to calculate the total marks
    public static int calculateTotalMarks(int mathMarks, int englishMarks, int scienceMarks)
    {
        return mathMarks + englishMarks + scienceMarks;
    }

    // Method to calculate the average marks
    public static double calculateAverage(int totalMarks, int numberOfSubjects)
    {
        return (double) totalMarks / numberOfSubjects;   // Returns average marks
    }

    // Method to assign a grade based on average marks
    public static String assignGrade(double averageMarks)
    {
        if (averageMarks >= 90)
        {
            return "A";   // Excellent
        }
        else if (averageMarks >= 70)
        {
            return "B";   // Good
        }
        else if (averageMarks >= 50)
        {
            return "C";   // Average
        }
        else
        {
            return "F";   // Fail
        }
    }
}

// Method to display the result
public static void displayResult(int totalMarks, double averageMarks, String grade)
{
    System.out.println("Total Marks: " + totalMarks);
    System.out.println("Average Marks: " + averageMarks);
    System.out.println("Grade: " + grade);
}

// Main method where the student details and calculation are done
public static void main(String[] args)
{
//    int calculateTotalMarks =0;
//    int calculateAverage = 0;
//    int assignGrade =0;
    int mathMarks = 85;
    int englishMarks = 78;
    int scienceMarks = 92;
    int numberOfSubjects = 3;

    // Step 1: Calculate total marks
    int totalMarks = StudentGradingSystem.calculateTotalMarks(mathMarks, englishMarks, scienceMarks);

    // Step 2: Calculate average marks
    double averageMarks = StudentGradingSystem.calculateAverage(totalMarks, numberOfSubjects);

    // Step 3: Assign grade based on average marks
    String grade = StudentGradingSystem.assignGrade(averageMarks);

    // Step 4: Display the result
    displayResult(totalMarks, averageMarks, grade);
}