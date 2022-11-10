package ru.kpfu.itis.validators;

import java.util.Optional;

public class UsernameValidator {
    public static Optional<String> validateUsername(String username) {
        if(username.length() > 30 || username.length() < 3){
            return Optional.of("Email должен содержать от 3 до 30 символов");
        }
        return Optional.empty();
    }
}
