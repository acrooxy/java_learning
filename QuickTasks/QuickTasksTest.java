import java.io.IOException;
import java.nio.file.*;

/** Run with: javac QuickTasks.java QuickTasksTest.java && java QuickTasksTest */
public class QuickTasksTest {
    public static void main(String[] args) throws Exception {
        Path directory = Files.createTempDirectory("quicktasks-test-");
        Path file = directory.resolve("tasks.txt");
        try {
            check(QuickTasks.load(file).isEmpty(), "new list is empty");
            QuickTasks.run(new String[]{"add", "Learn Java"}, file);
            QuickTasks.run(new String[]{"add", "Zażółć gęślą jaźń"}, file);
            check(QuickTasks.load(file).get(1).text().equals("Zażółć gęślą jaźń"), "UTF-8 round trip");
            QuickTasks.run(new String[]{"done", "1"}, file);
            QuickTasks.run(new String[]{"done", "1"}, file);
            check(QuickTasks.load(file).get(0).done(), "completion persists and is repeatable");
            check(!QuickTasks.load(file).get(1).done(), "other task unchanged");
            String original = Files.readString(file);
            String[][] invalid = {{"done", "99"}, {"done", "0"}, {"done", "abc"},
                {"remove"}, {"remove", "1", "extra"}, {"add", " "}, {"add", "a\nb"}, {"unknown"}};
            for (String[] command : invalid) {
                try {
                    QuickTasks.run(command, file);
                    throw new AssertionError("Invalid command accepted");
                } catch (IllegalArgumentException expected) {
                    check(Files.readString(file).equals(original), "invalid command preserves file");
                }
            }
            QuickTasks.run(new String[]{"remove", "1"}, file);
            check(QuickTasks.load(file).size() == 1, "remove persists");
            QuickTasks.run(new String[]{"remove", "1"}, file);
            check(QuickTasks.load(file).isEmpty(), "empty list round trip");
            Files.writeString(file, "broken data");
            try {
                QuickTasks.run(new String[]{"add", "Do not overwrite"}, file);
                throw new AssertionError("Corrupt file accepted");
            } catch (IOException expected) {
                check(Files.readString(file).equals("broken data"), "corrupt file preserved");
            }
            System.out.println("All QuickTasks tests passed.");
        } finally {
            Files.deleteIfExists(file);
            Files.deleteIfExists(directory);
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}

