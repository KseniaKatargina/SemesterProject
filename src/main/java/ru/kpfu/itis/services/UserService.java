package ru.kpfu.itis.services;

import ru.kpfu.itis.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserService {


    public void auth(User user, HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("user" , user);
    }

    public boolean isAuth(HttpServletRequest request, HttpServletResponse response){
        return (request.getSession().getAttribute("user") != null);
    }

    public User getUser(HttpServletRequest request, HttpServletResponse response) {
        return (User)(request.getSession().getAttribute("user"));
    }
    public void deleteUser(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
    }
}
