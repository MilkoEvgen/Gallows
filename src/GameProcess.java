import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameProcess {

    public static void newGame() {
        List<String> randomWord = Arrays.asList(Service.getRandomWord());
        List<String> randomWordCopy = Service.showTwoRandomLetters(randomWord);
        List<String> guessedLetters = new ArrayList<>();
        List<String> notGuessedLetters = new ArrayList<>();
        Service.initializeGallows();

        while (notGuessedLetters.size() < 6) {
            Service.printGallows(notGuessedLetters);
            System.out.print("Слово: ");
            randomWordCopy.forEach(System.out::print);
            if (randomWord.equals(randomWordCopy)) {
                System.out.println("\u001B[32m" + "\nВы выиграли!\n" + "\u001B[0m");
                break;
            }
            Service.printMistakes(notGuessedLetters);
            System.out.print("\nБуква: ");
            String letter = "";
            try {
                letter = Service.readLetter();
            } catch (Exception e) {
                System.out.println("\u001B[31m" + "Некорректный ввод!" + "\u001B[0m");
                continue;
            }
            if (notGuessedLetters.contains(letter) || guessedLetters.contains(letter)) {
                System.out.println("Вы уже вводили эту букву");
            } else if (randomWord.contains(letter)) {
                guessedLetters.add(letter);
                for (int j = 0; j < randomWord.size(); j++) {
                    if (randomWord.get(j).equals(letter)) {
                        randomWordCopy.set(j, letter);
                    }
                }
            } else {
                notGuessedLetters.add(letter);
            }
        }
        if (!randomWord.equals(randomWordCopy)) {
            Service.printYouLoose(notGuessedLetters, randomWord);
        }
    }
}
