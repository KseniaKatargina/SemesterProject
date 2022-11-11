package ru.kpfu.itis.servlets;

import ru.kpfu.itis.dao.UserRepositoryDBImpl;
import ru.kpfu.itis.dao.WishlistRepositoryDBImpl;
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

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {
    private UserService userService;
    private SecurityService securityService;
    private UserRepositoryDBImpl userRepository;
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
        request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getUser(request,response);
        String email = user.getEmail();
        Long userID = user.getId();
        String password = user.getPassword();

        String path = String.valueOf(request.getContextPath());

        String newBirthday = request.getParameter("birthday");

        String oldPassword = request.getParameter("oldPassword");

        String newUsername = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        String reNewPassword = request.getParameter("rePassword");
        String newEmail = request.getParameter("email");

        int newYears = GetYearsService.getYears(newBirthday);

        Optional<String> errorEmailMessage = EmailValidator.validateEmail(email);
        Optional<String> errorPasswordMessage = PasswordValidator.validatePassword(password);
        Optional<String> errorBirthdayMessage = BirthdayValidator.validateBirthday(newBirthday);
        Optional<String> errorUsernameMessage = UsernameValidator.validateUsername(newUsername);


        if(newUsername.length() == 0 || newEmail.length() == 0 || newBirthday.length() == 0) {
            failedMessageService.setMessage("Поля имя, дата рождения и email должны быть заполнены",request,response);
            response.sendRedirect(path + "/failedEditProfile");

        } else if (!newEmail.equals(email)) {

            if (securityService.isUserExist(userRepository, newEmail)) {
                failedMessageService.setMessage("Пользователь с таким логином существует",request,response);
                response.sendRedirect(path + "/failedEditProfile");
            }
        } else if (errorEmailMessage.isPresent()){
            failedMessageService.setMessage(errorEmailMessage.get(),request,response);
            response.sendRedirect(path + "/failedEditProfile");
        } else if (errorBirthdayMessage.isPresent()) {
            failedMessageService.setMessage(errorBirthdayMessage.get(),request,response);
            response.sendRedirect(path + "/failedEditProfile");
        } else if (!(oldPassword.length() == 0) && !(newPassword.length()==0) && !(reNewPassword.length() == 0)){
            String oldHashPassword = HashService.hashPassword(request.getParameter("oldPassword"));
            if (!password.equals(oldHashPassword)) {
                failedMessageService.setMessage("Неверный старый пароль",request,response);
                response.sendRedirect(path + "/failedEditProfile");
            } else if (!newPassword.equals(reNewPassword)) {
                failedMessageService.setMessage("Пароли не совпадают",request,response);
                response.sendRedirect(path + "/failedEditProfile");
            } else if (errorPasswordMessage.isPresent()){
                failedMessageService.setMessage(errorPasswordMessage.get(),request,response);
                response.sendRedirect(path + "/failedEditProfile");
            } else if (errorUsernameMessage.isPresent()){
                failedMessageService.setMessage(errorUsernameMessage.get(),request,response);
                response.sendRedirect(path + "/failedEditProfile");
            }else {
                try {
                    String newHashPassword = HashService.hashPassword(newPassword);
                    User newUser = new User(userID, newUsername, newEmail, newHashPassword, newBirthday, newYears);
                    userRepository.updateUser(newUser);
                    userService.auth(user,request,response);
                    response.sendRedirect(path + "/profile");
                } catch (DBException e) {
                    throw new ServletException(e);
                }
            }
        } else{
            try {
                User newUser = new User(userID, newUsername, newEmail, password, newBirthday, newYears);
                userRepository.updateUser(newUser);
                userService.auth(newUser,request,response);
                response.sendRedirect(path + "/profile");
            } catch (DBException e) {
                throw new ServletException(e);
            }
        }
    }
}
