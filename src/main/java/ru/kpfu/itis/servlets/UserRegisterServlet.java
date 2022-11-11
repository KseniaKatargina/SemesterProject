package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.UserRepositoryDBImpl;
import ru.kpfu.itis.exceptions.DBException;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.services.*;
import ru.kpfu.itis.validators.BirthdayValidator;
import ru.kpfu.itis.validators.EmailValidator;
import ru.kpfu.itis.validators.PasswordValidator;
import ru.kpfu.itis.validators.UsernameValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {
    private UserRepositoryDBImpl userRepository;
    private SecurityService securityService;
    private UserService userService;
    private FailedMessageService failedMessageService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userRepository = (UserRepositoryDBImpl) getServletContext().getAttribute("userDAO");
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
        userService = (UserService) getServletContext().getAttribute("userService");
        failedMessageService = (FailedMessageService) getServletContext().getAttribute("failedService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String birthday = request.getParameter("birthday");
        String rePassword = request.getParameter("rePassword");

        String path = String.valueOf(request.getContextPath());


        int years = GetYearsService.getYears(birthday);

        String hashPassword = HashService.hashPassword(password);

        User user = new User(username,email, hashPassword,birthday, years);

        Optional<String> errorEmailMessage = EmailValidator.validateEmail(email);
        Optional<String> errorPasswordMessage = PasswordValidator.validatePassword(password);
        Optional<String> errorBirthdayMessage = BirthdayValidator.validateBirthday(birthday);
        Optional<String > errorUsernameMessage = UsernameValidator.validateUsername(username);

        if(username.length() == 0 || email.length() == 0 || password.length() == 0 || birthday.length() == 0 || rePassword.length() == 0){
            failedMessageService.setMessage("Заполните все поля",request,response);
            response.sendRedirect("/FailedRegister");
        } else {
                if(securityService.isUserExist(userRepository, email)) {
                    failedMessageService.setMessage("Пользователь с таким логином существует",request,response);
                    response.sendRedirect(path + "/FailedRegister");
                }else if (!password.equals(rePassword)) {
                    failedMessageService.setMessage("Пароли не совпадают",request,response);
                    response.sendRedirect(path + "/FailedRegister");
                } else if (errorEmailMessage.isPresent()){
                    failedMessageService.setMessage(errorEmailMessage.get(),request,response);
                    response.sendRedirect(path + "/FailedRegister");
                } else if (errorPasswordMessage.isPresent()) {
                    failedMessageService.setMessage(errorPasswordMessage.get(),request,response);
                    response.sendRedirect(path + "/FailedRegister");
                } else if (errorBirthdayMessage.isPresent()){
                    failedMessageService.setMessage(errorBirthdayMessage.get(),request,response);
                    response.sendRedirect(path + "/FailedRegister");
                } else if (errorUsernameMessage.isPresent()){
                    failedMessageService.setMessage(errorUsernameMessage.get(),request,response);
                    response.sendRedirect(path +"/FailedRegister");
                }
                else {
                    try {
                        userRepository.saveUser(user);
                        userService.auth(user,request,response);
                        response.sendRedirect(path + "/mainPage");
                    } catch (DBException e) {
                        throw new ServletException(e);
                    }
                }
        }
    }
}
