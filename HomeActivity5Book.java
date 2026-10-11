package src;

import java.io.IOException;

public class HomeActivity5Book {
    public static void main(String args[])  {
        class Book {
            String title;
            String author;
            String pages;

            void displayInfo() {
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Pages: " + pages);
            }
        }
        Book book = new Book();
        book.author = "John Doe";
        book.title = "Java Programming";
        book.pages = "Java Programming";

        book.displayInfo();

        Book book2 = new Book();
        book2.author = "Eric Matherson";
        book2.title = "Family Relationship";

        book2.displayInfo();

        class Phone{
            String number;
            String type;

            void displayInfo() {
                System.out.println("Number: " + number);
                System.out.println("Type: " + type);
            }
            void displayNumber() {
                if (number != null) {

                    System.out.println("Number: " + number);}
                else{
                    System.out.println("No number");
                }

            }
        }
        Phone phone = new Phone();
        phone.number = "123-456-7890";
        phone.type = "home";
        phone.displayNumber();
        Phone phone2 = new Phone();

        phone2.displayNumber();

    }
}
