import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        boolean again;
        do {
            playGame();
            System.out.println();
            System.out.print("Play again? (y/n): ");
            again = scanner.nextLine().trim().equalsIgnoreCase("y");
        } while (again);
        System.out.println("Thanks for playing. See you next time!");
    }

    private static void playGame() {
        int health = 20;
        int potions = 2;
        int gold = 0;
        String[] places = {"Whispering Forest", "Sunken Cave", "Dragon Fortress"};
        System.out.println("====================================");
        System.out.println("       QUEST FOR THE DRAGON TREASURE");
        System.out.println("====================================");
        System.out.println("You have 20 health points and 2 potions.");
        System.out.println("Cross three locations and claim the dragon treasure!");

        for (int room = 0; room < places.length && health > 0; room++) {
            System.out.println();
            System.out.println(">> Location " + (room + 1) + ": " + places[room]);
            System.out.println("Health: " + health + " | Potions: " + potions + " | Gold: " + gold);
            System.out.println("What will you do? [1] Fight  [2] Use a potion  [3] Run");
            int choice = readChoice(1, 3);

            if (choice == 2) {
                if (potions > 0) {
                    potions--;
                    int healed = Math.min(8, 20 - health);
                    health += healed;
                    System.out.println("You drink a potion and recover " + healed + " health.");
                    room--;
                    continue;
                } else {
                    System.out.println("You are out of potions! The monster strikes while you hesitate.");
                    health -= 3;
                    room--;
                    continue;
                }
            }

            if (choice == 3 && room < places.length - 1) {
                if (random.nextBoolean()) {
                    System.out.println("You sneak past and find a pouch with 3 gold.");
                    gold += 3;
                    continue;
                } else {
                    System.out.println("The monster blocks your path! You lose 3 health.");
                    health -= 3;
                    room--;
                    continue;
                }
            }

            String enemy = room == places.length - 1 ? "Dragon" : new String[]{"Goblin", "Wolf", "Bat"}[random.nextInt(3)];
            int enemyHealth = room == places.length - 1 ? 14 : 6 + random.nextInt(4);
            System.out.println("A " + enemy + " appears! (" + enemyHealth + " strength)");
            while (enemyHealth > 0 && health > 0) {
                System.out.println("[1] Attack  [2] Dodge");
                int action = readChoice(1, 2);
                if (action == 1) {
                    int damage = 3 + random.nextInt(5);
                    enemyHealth -= damage;
                    System.out.println("You deal " + damage + " damage!");
                } else {
                    System.out.println("You prepare to dodge...");
                }
                if (enemyHealth > 0) {
                    if (action == 2 && random.nextBoolean()) {
                        System.out.println("Great dodge! You take no damage.");
                    } else {
                        int damage = room == places.length - 1 ? 4 : 2 + random.nextInt(4);
                        health -= damage;
                        System.out.println("The " + enemy + " hits you for " + damage + " health.");
                    }
                }
            }
            if (health > 0) {
                int reward = room == places.length - 1 ? 20 : 5 + random.nextInt(6);
                gold += reward;
                System.out.println("You defeat the enemy! You collect " + reward + " gold.");
            }
        }

        System.out.println();
        if (health > 0) {
            System.out.println("VICTORY! The dragon treasure is yours!");
            System.out.println("Score: " + gold + " gold, with " + health + " health remaining.");
        } else {
            System.out.println("GAME OVER! Your quest has come to an end.");
            System.out.println("You managed to collect " + gold + " gold.");
        }
    }

    private static int readChoice(int min, int max) {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) return choice;
            } catch (NumberFormatException ignored) {
                // Handle invalid input the same way as a number outside the range.
            }
            System.out.println("Enter a number from " + min + " to " + max + ".");
        }
    }
}