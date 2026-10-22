import java.util.ArrayList;
import java.util.Arrays;

public class AssingmentTask2 {
    public static void main(String[] args) {

        // Initialise ArrayList
        ArrayList<String> stack = new ArrayList<>(
                Arrays.asList("Google", "YouTube", "Facebook")
        );

        // Display Stack
        System.out.println("Stack: " + stack);

        // Push Operation
        stack.add("Instagram");
        System.out.println("After Push: " + stack);

        // Pop Operation
        stack.remove(stack.size() - 3);
        System.out.println("After Pop: " + stack);

        // Peek Operation
        System.out.println("Top Page: " + stack.get(stack.size() - 1));

        // Final Stack
        System.out.println("Final Stack: " + stack);
    }
}
