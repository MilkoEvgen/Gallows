import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Service {

    private static String[][] gallows = new String[7][9];

    public static String [] getRandomWord() {
        String randomWord = "";
        int lineCounter = 0;
        do {
            try {
                File file = Path.of("src", "Dictionary.txt").toFile();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    scanner.nextLine();
                    lineCounter++;
                }
                scanner.close();

                BufferedReader reader = new BufferedReader(new FileReader(file));
                Random random = new Random();
                int randomIndex = random.nextInt(lineCounter - 1);

                for (int i = 0; i < randomIndex; i++) {
                    reader.readLine();
                }
                randomWord = reader.readLine();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (randomWord.length() < 5);

        return randomWord.toUpperCase().split("");
    }

    public static List<String> showTwoRandomLetters(List<String> randomWord) {
        Random random = new Random();
        int number1 = random.nextInt(randomWord.size());
        int number2;

        do {
            number2 = random.nextInt(randomWord.size());
        } while (number2 == number1);
        List<String> letters = new ArrayList<>();

        for (int i = 0; i < randomWord.size(); i++) {
            if (i == number1 || i == number2) {
                letters.add(randomWord.get(i));
            } else {
                letters.add("_");
            }
        }
        return letters;
    }

    public static void initializeGallows() {
        gallows[0] = new String[]{"+", "-", "-", "-", "+", " ", " ", " ", " "};
        gallows[1] = new String[]{"|", " ", " ", " ", "|", " ", " ", " ", " "};
        gallows[2] = new String[]{"|", " ", " ", " ", " ", " ", " ", " ", " "};
        gallows[3] = new String[]{"|", " ", " ", " ", " ", " ", " ", " ", " "};
        gallows[4] = new String[]{"|", " ", " ", " ", " ", " ", " ", " ", " "};
        gallows[5] = new String[]{"|", " ", " ", " ", " ", " ", " ", " ", " "};
        gallows[6] = new String[]{"=", "=", "=", "=", "=", "=", "=", "=", "="};
    }

    public static void printGallows(List<String> notGuessedLetters) {
        switch (notGuessedLetters.size()) {
            case (1) -> gallows[2][4] = "O";
            case (2) -> gallows[3][3] = "/";
            case (3) -> gallows[3][4] = "|";
            case (4) -> gallows[3][5] = "\\";
            case (5) -> gallows[4][3] = "/";
            case (6) -> gallows[4][5] = "\\";
        }
        System.out.println();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(gallows[i][j]);
            }
            System.out.println();
        }
    }

    public static void printMistakes(List<String> mistakesList) {
        System.out.print("\nОшибки(" + mistakesList.size() + "): ");

        for (int k = 0; k < mistakesList.size(); k++) {
            if (k == 0) {
                System.out.print(mistakesList.get(k));
            } else System.out.print("," + mistakesList.get(k));
        }
    }

    public static String readLetter () throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String letter = reader.readLine().toUpperCase();
        if (!letter.matches("[А-Я]") || letter.length() > 1) {
            throw new Exception();
        }
        return letter;
    }

    public static void printYouLoose (List<String> notGuessedLetters, List<String> randomWord){
        Service.printGallows(notGuessedLetters);
        System.out.print("Слово: ");
        randomWord.forEach(System.out::print);
        System.out.println("\u001B[31m" + "\nВы проиграли\n" + "\u001B[0m");
    }

}
