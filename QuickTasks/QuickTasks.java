import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/** A tiny command-line task list with local persistence. Java 17+. */
public class QuickTasks {
    record Task(boolean done, String text) {}

    public static void main(String[] args) {
        Path file = Path.of(System.getProperty("quicktasks.file", "tasks.txt"));
        try {
            run(args, file);
        } catch (IllegalArgumentException | IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    static void run(String[] args, Path file) throws IOException {
        if (args.length == 0 || args[0].equals("help")) {
            System.out.println("QuickTasks: add <text> | list | done <id> | remove <id>");
            return;
        }
        List<Task> tasks = load(file);
        switch (args[0]) {
            case "list" -> {
                if (args.length != 1) throw new IllegalArgumentException("Use: list");
                if (tasks.isEmpty()) System.out.println("No tasks yet. Try: add Learn Java");
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.printf("%d. [%s] %s%n", i + 1, task.done() ? "x" : " ", task.text());
                }
                System.out.printf("Completed: %d/%d%n", tasks.stream().filter(Task::done).count(), tasks.size());
                return;
            }
            case "add" -> {
                String text = String.join(" ", Arrays.copyOfRange(args, 1, args.length)).trim();
                if (text.isEmpty()) throw new IllegalArgumentException("Task text cannot be empty.");
                if (text.contains("\n") || text.contains("\r"))
                    throw new IllegalArgumentException("Use a single-line task.");
                tasks.add(new Task(false, text));
            }
            case "done", "remove" -> {
                if (args.length != 2) throw new IllegalArgumentException("Provide exactly one task ID.");
                int index;
                try { index = Integer.parseInt(args[1]) - 1; }
                catch (NumberFormatException e) { throw new IllegalArgumentException("Task ID must be a number."); }
                if (index < 0 || index >= tasks.size()) throw new IllegalArgumentException("Task ID does not exist.");
                if (args[0].equals("remove")) tasks.remove(index);
                else tasks.set(index, new Task(true, tasks.get(index).text()));
            }
            default -> throw new IllegalArgumentException("Unknown command. Run: help");
        }
        save(file, tasks);
        System.out.println("Saved. Run list to see your tasks.");
    }

    static List<Task> load(Path file) throws IOException {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(file)) return tasks;
        int lineNumber = 0;
        for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
            lineNumber++;
            if (line.length() < 3 || line.charAt(1) != '\t' || (line.charAt(0) != '0' && line.charAt(0) != '1'))
                throw new IOException("Invalid data on line " + lineNumber + "; file left unchanged.");
            tasks.add(new Task(line.charAt(0) == '1', line.substring(2)));
        }
        return tasks;
    }

    static void save(Path file, List<Task> tasks) throws IOException {
        Path target = file.toAbsolutePath();
        Path temp = Files.createTempFile(target.getParent(), "quicktasks-", ".tmp");
        try {
            List<String> lines = tasks.stream().map(t -> (t.done() ? "1\t" : "0\t") + t.text()).toList();
            Files.write(temp, lines, StandardCharsets.UTF_8);
            try { Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE); }
            catch (AtomicMoveNotSupportedException e) { Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING); }
        } finally { Files.deleteIfExists(temp); }
    }
}

