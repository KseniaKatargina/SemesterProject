package ru.kpfu.itis.validators;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    public static Optional<String> validateEmail(String email) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return Optional.of( "Некорректный email" );
        }
        if(email.length() > 40 || email.length() < 10){
            return Optional.of("Email должен содержать от 10 до 40 символов");
        }
        return Optional.empty();
    }
}
