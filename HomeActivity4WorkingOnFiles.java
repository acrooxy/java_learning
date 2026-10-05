import java.io.File;
import java.io.IOException;

public class HomeActivity4WorkingOnFiles {
    public static void main(String[] args) {

        try {
            File testFile = new File("TestFile.txt");

            if (!testFile.exists()) {
                System.out.println("File does not exist");
                testFile.createNewFile();
                System.out.println("File created!");
            } else {
                System.out.println("File already exists!");
            }

        } catch (IOException e) {
            System.out.println("Error!");
        }
    }
}
