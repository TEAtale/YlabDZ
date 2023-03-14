package org.example.snilsValidator;

public class SnilsValidatorImpl implements SnilsValidator {
/*90114404441 control sum = 9*9 + 0*8 + 1*7 + 1*6 + 4*5 + 4*4 + 0*3 + 4*2 + 4*1 = 142 */
    @Override
    public boolean validate(String snils) {
        if (isDigit(snils) && snils.length() == 11) {
            int control = findControlSum(snils);
            int check = Integer.parseInt(snils.substring(snils.length() - 2));
            return check == control;
        }
        return false;
    }
    public boolean isDigit (String str) {
        try {
            Long.parseLong(str);
        }
        catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
    public int findControlSum (String str) {
            int controlSum = 0;
            char[] chars = str.toCharArray();
            for (int i = 0; i < 10; i++) {
                    controlSum = controlSum + Character.getNumericValue(chars[i])* (9-i);
            }
            if (controlSum == 100 || controlSum%101 == 100) {
                controlSum = 0;
            }
            else if (controlSum > 100){
                controlSum = controlSum%101;
            }
            return controlSum;
    }
}