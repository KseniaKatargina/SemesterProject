package ru.kpfu.itis.validators;

import java.util.Optional;

public class BirthdayValidator {
    public static Optional<String> validateBirthday(String date) {
        if(Integer.parseInt(date.substring(0, 4)) < 1999){
            return Optional.of("Слишком большой год рождения");
        } else if (Integer.parseInt(date.substring(0, 4)) > 2010){
            return Optional.of("Вам недостаточно лет для пользования сайтом");
        }
        return Optional.empty();
    }
}
