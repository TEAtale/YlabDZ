package org.example.complex;

abstract class ComplexNumber {
    double re;
    double im;

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    double abs() {
        return 0;
    }

    ComplexNumber sum(ComplexNumber c) {
        return null;
    }

    ComplexNumber subtract(ComplexNumber c) {
        return null;
    }

    ComplexNumber mult(ComplexNumber c) {
        return null;
    }
}
