package org.example.thirdLess.passwordValidator;

public class PasswordValidator {

    public static void main(String[] args) throws WrongLoginException, WrongPasswordException {
        System.out.println(validate("Ev123genia_", "Ev123genia_", "Ev123genia"));
        System.out.println(validate("xfghghxdgmxdlfkzdlkhkjz9_gjubghuvguvgy", "Ev123genia_", "Ev123genia_"));
        System.out.println(validate("Ev123ge#-nia_","Ev123genia_", "Ev123genia_"));
        System.out.println(validate("Ev123genia_", "Ev1@=+23genia_", "Ev1@=+23genia_"));
        System.out.println(validate("Ev123genia_","Ev123gensgdgdfgdgdgdgdfgdgfdggfia_","Ev123genia_"));
        System.out.println(validate("Ev123genia_","Ev123genia_","Ev123genia_"));
        System.out.println(validate("_123", "123", "123"));
    }
    public static boolean validate(String login, String password, String confirmPassword) {
       boolean result = false;
        try {
           checkLog(login);
           checkPas(password);
           checkConf(password,confirmPassword);
           result = true;
       }
       catch (WrongLoginException | WrongPasswordException e) {
           System.out.println(e.getMessage());
       }
      return result;
    }
    public static void checkLog(String log) throws WrongLoginException {
        //"[a-zA-Z]+[0-9_]+\\w*" - если обязательно нужны буквы и цифры
        if (log == null || !log.matches("\\w+")) {
            throw new WrongLoginException("Логин содержит недопустимые символы");
        } else if (log.length() >= 20) {
            throw new WrongLoginException("Логин слишком длинный");
        }
    }
    public static void checkPas(String pas) throws WrongPasswordException {
        if (pas == null || !pas.matches("\\w+")) {
            throw new WrongPasswordException("Пароль содержит недопустимые символы");
        } else if (pas.length() >= 20) {
            throw new WrongPasswordException("Пароль слишком длинный");
        }
    }
    public static void checkConf(String p, String c) throws WrongPasswordException {
        if (!p.equals(c)){
            throw new WrongPasswordException("Пароль и подтверждение не совпадают");
        }
    }


}
