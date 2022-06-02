import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordleGame {
    private String answer;
    private int tries;
    private boolean over;

    // The constants bellow are used to color strings
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    WordleGame() {
        this("ABCDE");
    }

    WordleGame(String answer) {
        this(answer, 5);
    }

    WordleGame(String answer, int tries) {
        this.answer = answer;
        this.tries = tries;
        over = false;
    }

    String guess(String guess) throws InvalidInputException {

//       try {
        if (guess.length() != answer.length()) {
            over = false;
            System.out.println("You have " + tries + " left");

            throw new InvalidInputException("Guess must be " + answer.length() + " letter long");
        }
        try {
            int x=0;
            File file = new File("src/database.txt");
            Scanner database = new Scanner(file);
            for(int i=0; i< 981; i++) {
                while (database.hasNextLine()) {
                    if(guess.equals(database.next())){
                        x++;
                    }
                }
            }
            if(x==0){
                this.over=false;
                System.out.println("You have " + tries + " left");
                return "NOT A VALID WORD";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//       }
//       catch(InvalidInputException e){
//           System.out.println("InvalidInputException");
//           return "Guess must be" + answer.length() + " characters long.";
//       }
        tries--;
        String[] result = new String[answer.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = ANSI_RED;
        }
        if (guess.equals(answer)) {
            this.over = true;
            System.out.println(ANSI_GREEN + "YOU WIN" + ANSI_RESET);
            return ANSI_GREEN + guess + ANSI_RESET;
        }
        // check for green
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                // its green!

                result[i] = ANSI_GREEN;
            }
        }
        // check for yellow
        for (int i = 0; i < guess.length(); i++) {
            for (int j = 0; j < answer.length(); j++) {
                if (i != j && guess.charAt(i) == answer.charAt(j)) {
                    result[i] = ANSI_YELLOW;
                }
            }
        }
        // return the guess with colors

        String g = "";
        for (int i = 0; i < guess.length(); i++) {

            g += result[i] + guess.charAt(i) + ANSI_RESET;
        }
        System.out.println("You have " + tries + " left");

        return g;
    }
    boolean isOver() {
        if (over || tries <= 0) {
            System.out.println("The answer is " + answer);
            return true;
        }
        return false;
    }


    }
