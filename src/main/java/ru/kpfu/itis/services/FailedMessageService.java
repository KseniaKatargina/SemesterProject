package ru.kpfu.itis.services;

import ru.kpfu.itis.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FailedMessageService {

    public void setMessage(String message, HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("message" , message);
    }
}
