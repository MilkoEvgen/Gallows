import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loop:
        while (true) {
            System.out.println("\u001B[32m" + "Начать новую игру? (1/0)" + "\u001B[0m");

            switch (scanner.nextLine()) {
                case ("1"):
                    GameProcess.newGame();
                    break;
                case ("0"):
                    System.out.println("Выход из игры...");
                    scanner.close();
                    break loop;
                default:
                    System.out.println("\u001B[31m" + "Неправильный ввод!" + "\u001B[0m");
                    break;
            }
        }
    }


}