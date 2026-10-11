package src;

public class HomeActivity7ClassTest {
    public static void main(String args[]) {

        class People {
            String Name;
            String Surname;

            void getDetails(){
                System.out.println("Name: " + Name);
                System.out.println("Surname: " + Surname);
            }
        }
        People person1 = new People();
        person1.Name = "Damian";
        person1.Surname = "Tomala";
        person1.getDetails();

    }
}
