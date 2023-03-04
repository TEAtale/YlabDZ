package org.example;

import java.util.Random;
import java.util.Scanner;

public class Guess {
    public static void main(String[] args) {
        int number = new Random().nextInt(99) + 1; // ����� ������������ ����� �� 1 �� 99
        int maxAttempts = 10; // ����� �������� ���������� �������
        System.out.println("� ������� �����. � ���� " + maxAttempts + " ������� �������.");
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int tries = 1;

            while (maxAttempts > 0) {
                if (n == number) {
                    System.out.println("�� ������ � " + tries + " �������");
                    break;
                } else if (n < number) {
                    maxAttempts--;
                    System.out.println("��� ����� ������! �������� " + maxAttempts + " �������");
                    if (maxAttempts == 0) {
                        System.out.println("�� �� ������!");
                        break;
                    }
                    tries++;
                    n = scanner.nextInt();
                } else {
                    maxAttempts--;
                    System.out.println("��� ����� ������! �������� " + maxAttempts + " �������");
                    if (maxAttempts == 0) {
                        System.out.println("�� �� ������!");
                        break;
                    }
                    tries++;
                    n = scanner.nextInt();
                }

            }
        }

    }

}

