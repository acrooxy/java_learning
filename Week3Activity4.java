public class Week3Activity4 {
    public static void main(String[] args) {

        class Person {
            String name;
            int age;

            void displayDetails() {
                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
            }
        }

        class Student extends Person {
            int studentID;

            void displayDetails() {
                super.displayDetails();
                System.out.println("Student ID: " + studentID);
            }
        }

        Student student1 = new Student();

        student1.name = "Damian";
        student1.age = 34;
        student1.studentID = 12345;

        student1.displayDetails();
    }
}
