package org.example.firstLesson;

import java.util.Scanner;

public class Pell {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int a = 1;
            int b = 0;
            if (n < 3) {
                System.out.println(n);
            }
            else {
                for (int i = 2; i <= n; i++) {
                   int temp = 2*a + b;
                   b = a;
                   a = temp;
                }
                System.out.println(a);
            }

        }

    }
}
