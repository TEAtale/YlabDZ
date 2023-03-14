package org.example.secondLess.complex;

public class ComplexNumberTest {
    public static void main(String[] args) {
        ComplexNumber firstComplexNumber = new MyComplexNumber(1.0, 1.5);
        ComplexNumber secondComplexNumber = new MyComplexNumber(2,3);
        ComplexNumber sum = firstComplexNumber.sum(new MyComplexNumber(0.5));
        System.out.println(sum);
        ComplexNumber sub = firstComplexNumber.subtract(secondComplexNumber);
        System.out.println(sub);
        System.out.println(sub.abs());
        ComplexNumber mult = firstComplexNumber.mult(secondComplexNumber);
        System.out.println(mult);
    }
}
