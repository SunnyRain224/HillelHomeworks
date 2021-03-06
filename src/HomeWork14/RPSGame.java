package HomeWork14;

import java.io.*;
import java.util.Scanner;

public class RPSGame {
    private static RPSEnum[] rpsPlayerEnum;
    private static int rpsPlayerIndex;
    private static int rpsPcIndex;
    private static RPSEnum[] rpsPcEnum;
    private static String playerName;
    private static int gameCount;
    private static int currentGameCount = 0;
    private static String result = "=========================================" + "\n";

    public RPSGame() {

    }

    public static void gameStart() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите своё имя : ");
        playerName = in.nextLine();
        System.out.println("Введите сколько раз вы хотите ссыграть : ");
        gameCount = in.nextInt();
        System.out.println("Да начнётся игра...");
        rpsPlayerEnum = new RPSEnum[gameCount];
        rpsPcEnum = new RPSEnum[gameCount];
        while (currentGameCount < gameCount) {
            condition();
        }
    }

    public static void condition() throws IOException {
        System.out.println("Напишите, что вы хотите поставить: ножницы, бумага, камень");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        switch (str.toLowerCase()) {
            case "камень":
                rpsPlayerEnum[currentGameCount] = RPSEnum.ROCK;
                rpsPlayerIndex = 0;
                break;
            case "ножницы":
                rpsPlayerEnum[currentGameCount] = RPSEnum.SCISSORS;
                rpsPlayerIndex = 1;
                break;
            case "бумага":
                rpsPlayerEnum[currentGameCount] = RPSEnum.PAPER;
                rpsPlayerIndex = 2;
                break;
            default:
                System.out.println("Вы ввели название с ошибкой...");
                return;
        }
        whoWins();
    }

    public static void whoWins() throws IOException {
        int[][] resultsMatrix = {
                {3, 1, 2},
                {2, 3, 1},
                {1, 2, 3}
        };

        String currGameRes = "";
        switch ((int) (Math.random() * 3)) {
            case 0:
                rpsPcEnum[currentGameCount] = RPSEnum.ROCK;
                rpsPcIndex = 0;
                break;
            case 1:
                rpsPcEnum[currentGameCount] = RPSEnum.SCISSORS;
                rpsPcIndex = 1;
                break;
            case 2:
                rpsPcEnum[currentGameCount] = RPSEnum.PAPER;
                rpsPcIndex = 2;
                break;
            default:
        }
        if (resultsMatrix[rpsPlayerIndex][rpsPcIndex] == 1) {
            currGameRes += "Игрок: " + playerName + " выиграл!" + "\n" +
                    "Выбор игрока :" + rpsPlayerEnum[currentGameCount] + "\n" +
                    "Выбор компьютера :" + rpsPcEnum[currentGameCount] + "\n";
        } else {
            if (resultsMatrix[rpsPlayerIndex][rpsPcIndex] == 2) {
                currGameRes += "Компьютер выиграл!" + "\n" +
                        "Выбор игрока :" + rpsPlayerEnum[currentGameCount] + "\n" +
                        "Выбор компьютера :" + rpsPcEnum[currentGameCount] + "\n";
            } else {
                System.out.println("У вас ничья, продолжайте выбор : ножницы, бумага, камень");
                condition();
                return;
            }
        }

        result += currGameRes;
        result += "=========================================" + "\n";
        currentGameCount++;
        System.out.println(currGameRes);
        if (currentGameCount == gameCount) {
            saveRes();
        }
        if (currentGameCount < gameCount) {
            if (!endGame())
                System.exit(1);
        }
    }

    public static boolean endGame() throws IOException {
        System.out.println("Хотите ли вы продолжить игру: да, нет ?");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        switch (str.toLowerCase()) {
            case "да":
                return true;
            case "нет":
                saveRes();
                return false;
            default:
                System.out.println("Вы написали неверно");
                endGame();
                return true;
        }
    }


    public static void saveRes() throws IOException {
        File file = new File("C:/Users/bully/IdeaProjects/HillelHomeworks/src/HomeWork14/savings.txt");
        if (file.exists()) {
            try (FileWriter save = new FileWriter(file, true)) {
                save.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FileWriter save = new FileWriter(file);
            save.write(result);
            save.close();
            System.out.println("dog");
        }
    }

    public static String getResult() {
        return result;
    }

}



