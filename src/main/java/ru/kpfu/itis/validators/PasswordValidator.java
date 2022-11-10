package ru.kpfu.itis.validators;

import java.util.Optional;
import java.util.regex.Pattern;

public class PasswordValidator {
    public static Optional<String> validatePassword(String password) {
        if (!Pattern.compile("[A-ZА-Я]").matcher(password).find()) {
            return Optional.of( "Пароль должен содержать заглавные буквы" );
        }
        if (!Pattern.compile("[a-zа-я]").matcher(password).find()) {
            return Optional.of(  "Пароль должен содержать строчные буквы" );
        }
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return Optional.of("Пароль должен содержать цифры" );
        }
        if(password.length() < 8 || password.length() > 30){
            return Optional.of("Пароль должен содержать от 8 до 30 символов");
        }
        return Optional.empty();
    }
}
