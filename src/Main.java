import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            int count = 0;
            int k = 0;

            File file = new File("src/database.txt");
            Scanner database = new Scanner(file);
            Scanner sc = new Scanner(System.in);

            while (database.hasNextLine()) {
                database.nextLine();
                count++;
            }
            Random random = new Random();
            int line_pos = random.nextInt(count);
            System.out.println(line_pos);
            String ans = "";
            database = new Scanner(file);

            for (int i = 1; i < line_pos; i++) {
                ans = database.nextLine();
            }

            WordleGame game = new WordleGame(ans);

            while (!game.isOver()) {
                System.out.print("Guess the wordle (upper case letter only, please): ");
                String guess = sc.nextLine();
                database = new Scanner(file);
                try {
                    String g = game.guess(guess);
                    System.out.println(g);
                } catch (InvalidInputException e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
