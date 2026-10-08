public class Week3Activity1 {
    public static void main(String[] args) {
        class Car {
            String brand;
            String model;
            String colour;
            int year;

            void displayInfo() {
                System.out.println("Brand: " + brand);
                System.out.println("Model: " + model);
                System.out.println("Color: " + colour);
                System.out.println("Year: " + year);

            }
        }
        Car car1 = new Car();
        car1.brand = "Hyundai";
        car1.model = "Kona";
        car1.year = 2022;
        car1.colour = "Grey";
            car1.displayInfo();
    }
}
