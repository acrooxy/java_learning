import java.util.Arrays;
import java.util.List;

public class Week2Activity3 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5);

        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }


        System.out.println("2nd way of doing it");
        int a = 0;
        while (a < 6) {
            System.out.println(a);
            a++;
        }
    }
}
