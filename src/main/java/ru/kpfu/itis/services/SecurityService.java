package ru.kpfu.itis.services;

import ru.kpfu.itis.dao.UserRepositoryDBImpl;
import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.User;

public class SecurityService {
    public boolean isUserExist(UserRepositoryDBImpl userRepository, String email) {

        try {
            return  userRepository.findByEmail(email);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean validate(UserRepositoryDBImpl userRepository, User user) {
        try {
            return  userRepository.isLogin(user);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
    }
}
