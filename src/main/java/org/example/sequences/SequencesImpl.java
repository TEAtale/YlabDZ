package org.example.sequences;

public class SequencesImpl implements Sequences {
    /*A. 2, 4, 6, 8, 10...
B. 1, 3, 5, 7, 9...
C. 1, 4, 9, 16, 25...
D. 1, 8, 27, 64, 125...
E. 1, -1, 1, -1, 1, -1...
F. 1, -2, 3, -4, 5, -6...
G. 1, -4, 9, -16, 25....
H. 1, 0, 2, 0, 3, 0, 4....
I. 1, 2, 6, 24, 120, 720...
J. 1, 1, 2, 3, 5, 8, 13, 21…
*/
        @Override
    public void a(int n) {
        int temp = 0;
        for (int i = 0; i < n; i++) {
            temp += 2;
            System.out.print(temp + " ");
        }
            System.out.println();
    }

    @Override
    public void b(int n) {
            int temp = 1;
            for (int i = 1; i < n; i++) {
                System.out.print(temp + " ");
                temp += 2;
            }
        System.out.println();
    }

    @Override
    public void c(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print((int)(Math.pow(i, 2)) + " ");
        }
        System.out.println();
    }

    @Override
    public void d(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print((int)(Math.pow(i, 3)) + " ");
        }
        System.out.println();
    }

    @Override
    public void e(int n) {
        int temp = 1;
        for (int i = 1; i <= n; i++) {
            System.out.print(temp + " ");
            temp *= -1;
        }
        System.out.println();
    }

    @Override
    public void f(int n) {
        int temp = 1;
        for (int i = 1; i <= n; i++) {
            System.out.print(temp + " ");
            if (temp > 0) {
                temp++;
            }
            else { temp--;}
            temp *= -1;
        }
        System.out.println();
    }

    @Override
    public void g(int n) {
        for (int i = 1; i <= n; i++) {
            if (i%2 == 0) {
                System.out.print(i* (-i) + " ");
            }
            else {
                System.out.print(i * i + " ");
            }
        }
        System.out.println();
    }

    @Override
    public void h(int n) {
        int temp = 1;
        for (int i = 0; i < n; i++) {
            if (i%2 == 0) {
                System.out.print(temp + " ");
                temp++;
            }
            else {
                System.out.print(temp-temp + " ");
            }
        }
        System.out.println();
    }

    @Override
    public void i(int n) {
        int temp = 1;
        for (int i = 1; i <= n; i++) {
            System.out.print(temp*i + " ");
            temp = temp*i;
        }
        System.out.println();
    }

    @Override
    public void j(int n) {
        int temp1 = 1;
        int temp2 = 0;
        int result = 1;
            for (int i = 0; i < n; i++) {
                System.out.print(result + " ");
                result = temp1 + temp2;
                temp2 = temp1;
                temp1 = result;
            }
            System.out.println();
    }
}
