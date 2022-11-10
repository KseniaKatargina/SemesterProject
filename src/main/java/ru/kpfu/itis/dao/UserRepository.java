package ru.kpfu.itis.dao;

import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.User;

public interface UserRepository {
    void saveUser(User user) throws DBException;
    boolean findByEmail(String email) throws DBException;
    boolean isLogin(User user) throws DBException;
    User userByEmail(String email) throws DBException;
    void updateUser(User user) throws DBException;
}
