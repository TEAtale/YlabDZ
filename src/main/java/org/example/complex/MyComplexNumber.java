package org.example.complex;

public class MyComplexNumber extends ComplexNumber {
    private final double re;
    private final double im;

    public MyComplexNumber(double re) {
        this.re = re;
        this.im = 0.0;
    }

    public MyComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public double abs() {
        return Math.sqrt(re * re + im * im);
    }

    @Override
    public ComplexNumber sum(ComplexNumber c) {
        double real = this.re + c.getRe();
        double imag = this.im + c.getIm();
        return new MyComplexNumber(real, imag);
    }

    @Override
    public ComplexNumber subtract(ComplexNumber c) {
        double real = this.re - c.getRe();
        double imag = this.im - c.getIm();
        return new MyComplexNumber(real, imag);
    }

    @Override
    public ComplexNumber mult(ComplexNumber c) {
        double real = this.re * c.getRe() - this.im * c.getIm();
        double imag = this.re * c.getIm() + this.im * c.getRe();
        return new MyComplexNumber(real, imag);
    }

    @Override
    public String toString() {
        if (im == 0) {
            return re + "";
        } else if (re == 0) {
            return im + "i";
        } else if (im < 0) {
            return re + " - " + (-im) + "i";
        }
        return re + " + " + im + "i";
    }
}
