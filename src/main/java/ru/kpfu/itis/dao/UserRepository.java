package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.User;

public interface UserRepository {
    void saveUser(User user);
    boolean findByEmail(String email);
    boolean isLogin(User user);
    User userByEmail(String email);

}
