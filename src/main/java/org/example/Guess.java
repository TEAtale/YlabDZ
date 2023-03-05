package org.example;

import java.util.Random;
import java.util.Scanner;

public class Guess {
    public static void main(String[] args) {
        int number = new Random().nextInt(99) + 1; // здесь загадывается число от 1 до 99
        int maxAttempts = 9; // здесь задается количество попыток
        System.out.println("Я загадал число. У тебя " + (maxAttempts + 1) + " попыток угадать.");
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int tries = 1;

            while (maxAttempts >= 0) {
                if (n == number) {
                    System.out.println("Ты угадал с " + tries + " попытки");
                    break;
                } else if (n < number) {
                    System.out.println("Мое число больше! осталось " + maxAttempts + " попыток");

                } else {
                    System.out.println("Мое число меньше! осталось " + maxAttempts + " попыток");
                    }
                   if (maxAttempts == 0) {
                    System.out.println("Ты не угадал!");
                    break;
                }
                tries++;
                maxAttempts--;
                n = scanner.nextInt();
            }
        }

    }

}

