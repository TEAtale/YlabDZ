package org.example.snilsValidator;

public class SnilsValidatorTest {
    public static void main(String[] args) {
        System.out.println(new SnilsValidatorImpl().validate("01468870570")); // из дз false
        System.out.println(new SnilsValidatorImpl().validate("90114404441")); // из дз true
        System.out.println(new SnilsValidatorImpl().validate("sdkflsd")); // просто буквы false
        System.out.println(new SnilsValidatorImpl().validate(null)); // null false
        System.out.println(new SnilsValidatorImpl().validate("0000000011")); // 10 цифр false
        System.out.println(new SnilsValidatorImpl().validate("00000000101")); // <100 true
        System.out.println(new SnilsValidatorImpl().validate("90002200100")); // ==100 true
        System.out.println(new SnilsValidatorImpl().validate("99080000000")); // %101 == 100 true
        System.out.println(new SnilsValidatorImpl().validate("86369813667")); // из генератора валидных СНИЛС true
    }
}
